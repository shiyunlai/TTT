#!/bin/bash

# 功能说明: 备份mysql数据库
#
# 使用说明:
# 脚本运行所需用户/密码/mysqlsocket请设置在.mysqldumpconf文件中，脚本首次执行会自动生成该文件。
# [client]
# user=xxxx
# password=xxxx
# socket=/tmp/mysql.sock
#
# USAGE: bash mysqldump.sh [-d 数据库(多数据库逗号隔开)] [-t 表名] [-s 备份保存路径] [-l 备份保留天数] [-z 打开压缩功能]
#
# 参数使用说明:
#
#    -d      非必传      指定需要备份的数据库(如果不传递数据库默认全备份,指定多数据库用','隔开,例如:-d db1,db2,db3...)
#    -t      非必传      指定备份指定数据库的指定表,多表使用','隔开
#    -s      非必传      指定备份存放路径(默认当前路径下backup)
#    -l      非必传      备份保留天数
#    -z      不用传递参数    打开压缩功能
#
# 使用案例:
#
# 0.查看使用说明
# bash mysqldump.sh -h
# 
# 1.指定备份多个数据库到指定路径
# bash mysqldump.sh -d mysql,xf,blog -s /tmp/rrr -l 30
#
# 2.压缩备份多个数据库到指定路径
# bash mysqldump.sh -d mysql,xf,blog -s /tmp/rrr -l 30 -z
#
# 3.全库压缩备份到指定路径
# bash mysqldump.sh -s /tmp/rrr -l 30 -z
#
# 4.压缩备份某数据库N张表数据到指定路径
# bash mysqldump.sh -d xf -t xf_company,xf_message,xf_userinfo -s /tmp/rrr -z
#


basedir=$(cd `dirname "$0"`;pwd)
logdir=$basedir/logs
logfile=$logdir/total.log
myfile=$basedir/.mysqldumpconf
bakdir=$basedir/backup
def_user=root
def_pwd=root123
mysql_scoket=`netstat -ln | grep mysql  | awk '{print $9}'`
state=0
#默认保留天数
savedays=15
#打开关闭压缩
zip_open=0
#获取本地ip地址
local_ip=$(ifconfig|egrep -o 'inet\s+?([0-9]{1,3}\.){3}[0-9]{1,3}'|awk -F ' ' '{print $2}'|grep -v 127.0.0.1|head -1)
if test -z "$local_ip";then
    local_ip=$HOSTNAME
fi

#统一接收外部参数函数
do_writelog() {
    case $1 in
        i|I)
        shift
        echo "$(date +%Y-%m-%d) $(date +%H:%M:%S)|[INFO]|$@" >>$logfile
        ;;
        e|E)
        shift
        echo "$(date +%Y-%m-%d) $(date +%H:%M:%S)|[ERROR]|$@" >>$logfile
        ;;
        *)
        echo "$(date +%Y-%m-%d) $(date +%H:%M:%S)|[DEBUG]|$@" >>$logfile
        esac
}

if test ! -f "$myfile";then
    echo -e "[client]\nuser=$def_user\npassword=$def_pwd\nsocket=$mysql_scoket" >$myfile
fi

#获取mysqldump命令
mysqldump_bin=`which mysqldump`
if [ $? -ne 0 ];then
    echo "mysqldump命令不存在,确认是否安装mysql客户端"
    exit 1
fi


#####################################################
#异地备份主机信息
open_close_ssh=0
ssh_host="x.x.x.x"
ssh_port=22
ssh_user="root"
ssh_dir="/home/backup"
#####################################################

do_makedir() {
    if test ! -d "${1}";then
        mkdir -p ${1}
    fi
}

usage(){ 
    echo -e ""
    echo -e "USAGE: bash $0 [-d 数据库(多数据库逗号隔开)] [-t 表名] [-s 备份保存路径] [-l 备份保留天数] [-z 打开压缩功能] }"
    echo -e "\n使用举例:"
    echo -e "0.查看使用说明"
    echo -e "  bash mysqldump.sh -h"
    echo -e "1.指定备份多个数据库到指定路径"
    echo -e "  bash mysqldump.sh -d mysql,xf,blog -s /tmp/rrr -l 30"
    echo -e "2.压缩备份多个数据库到指定路径"
    echo -e "  bash mysqldump.sh -d mysql,xf,blog -s /tmp/rrr -l 30 -z"
    echo -e "3.全库压缩备份到指定路径"
    echo -e "  bash mysqldump.sh -s /tmp/rrr -l 30 -z"
    echo -e "4.压缩备份某数据库N张表数据到指定路径"
    echo -e " bash mysqldump.sh -d xf -t xf_company,xf_message,xf_userinfo -s /tmp/rrr -z"
    echo -e ""
}


while getopts "d:t:s:l:zh" opts;do
    case $opts in
    d|D)
        db_list=$OPTARG
        ;;
    t|T)
        tb_list=$OPTARG
        ;;
    s|S)
        save_dir=$OPTARG
        ;;
    l|L)
        save_days=$OPTARG
        ;;
    z|Z)
        zip_open=1
        ;;
    h|H)
	usage
        exit 0
        ;;
    *)
	usage
        exit 0
        esac
done

if test -z "$save_dir";then
    save_dir=$bakdir
fi

if test -z "$save_days";then
    save_days=$savedays
else
    expr $save_days + 0 &>/dev/null
    if [ $? -ne 0 ];then
        save_days=$savedays
    fi
fi

do_makedir $logdir
do_makedir $save_dir

do_export_sql() {
    if test -z "$tb_list";then
        if test -n "$db_list";then
        OLD_IFS="$IFS" 
        IFS="," 
        arr=($db_list) 
        IFS="$OLD_IFS" 
        for i in ${arr[@]} 
        do 
			do_writelog i "数据库[${i}]开始备份"
            $mysqldump_bin --defaults-file=$myfile $i >$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql
            if [ $? -ne 0 ];then
                do_writelog e "数据库[$i]备份异常"
                if [ -e "${local_ip}_${i}_$(date +%Y%m%d).sql" ];then
                    rm -f ${local_ip}_${i}_$(date +%Y%m%d).sql &>/dev/null
                fi
            else
                do_writelog i "数据库[$i]备份完成,文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]"
				if test -e "$save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql";then
					rm -f $save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql
					do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql]清理完成"
				fi
				if test -e "$save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz";then
					rm -f $save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz
					do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz]清理完成"
				fi
				if [ $zip_open -eq 1 ];then
					do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]开始压缩"
					cd $save_dir;tar -zcf ${local_ip}_${i}_$(date +%Y%m%d).sql.tar.gz ${local_ip}_${i}_$(date +%Y%m%d).sql
					if [ $? -ne 0 ];then
						do_writelog e "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]压缩异常"
						state=0
					else
						do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]压缩成功"
						if test -e "$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql";then
							rm -f $save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql &>/dev/null
							do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]清理完成"
						fi
						state=1
					fi
				else
					state=0
				fi
				
				if [ $open_close_ssh -eq 1 ];then
					if [ $state -eq 0 ];then
						scp  $save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql]异地转移成功"
						fi
					else
						scp  $save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql.tar.gz -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql.tar.gz]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_${i}_$(date +%Y%m%d).sql.tar.gz]异地转移成功"
						fi
					fi
				fi
					
            fi
        done
        else
            $mysqldump_bin --defaults-file=$myfile --all-database >$save_dir/${local_ip}_all_$(date +%Y%m%d).sql
            if [ $? -ne 0 ];then
                do_writelog e "数据库全备份异常"
                if [ -e "$save_dir/${local_ip}_all_$(date +%Y%m%d).sql" ];then
                    rm -f $save_dir/${local_ip}_all_$(date +%Y%m%d).sql &>/dev/null
                fi
            else
                do_writelog i "数据库全备份完成"
				if test -e "$save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql";then
					rm -f $save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql
					do_writelog i "文件[$save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql]清理完成"
				fi
				if test -e "$save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz";then
					rm -f $save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz
					do_writelog i "文件[$save_dir/${local_ip}_all_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz]清理完成"
				fi
				if [ $zip_open -eq 1 ];then
				do_writelog i "开始压缩[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]"
				cd $save_dir;tar -zcf ${local_ip}_all_$(date +%Y%m%d).sql.tar.gz ${local_ip}_all_$(date +%Y%m%d).sql
				if [ $? -ne 0 ];then
						do_writelog e "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]压缩异常"
						state=0
					else
						do_writelog i "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]压缩成功"
						if test -e "$save_dir/${local_ip}_all_$(date +%Y%m%d).sql";then
							rm -f $save_dir/${local_ip}_all_$(date +%Y%m%d).sql &>/dev/null
							do_writelog i "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]清理完成"
						fi
						state=1
					fi
				else
					state=0
				fi
				
				if [ $open_close_ssh -eq 1 ];then
					if [ $state -eq 0 ];then
						scp  $save_dir/${local_ip}_all_$(date +%Y%m%d).sql -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql]异地转移成功"
						fi
					else
						scp  $save_dir/${local_ip}_all_$(date +%Y%m%d).sql.tar.gz -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql.tar.gz]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_all_$(date +%Y%m%d).sql.tar.gz]异地转移成功"
						fi
					fi
				fi
				
            fi
         fi
    else
        if test -z "$db_list";then
               echo "请指定数据库名称"
               exit 1
        fi
        OLD_IFS="$IFS" 
        IFS="," 
        arr=($tb_list) 
        IFS="$OLD_IFS" 
        for i in ${arr[@]} 
        do 
            $mysqldump_bin --defaults-file=$myfile $db_list $i >$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql
            if [ $? -ne 0 ];then
                do_writelog e "数据库表[$db_list][$i]备份异常"
                if [ -e "$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql" ];then
                    rm -f $save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql &>/dev/null
                fi
            else
                do_writelog i "数据库表[$db_list][$i]备份完成,文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]"
				if test -e "$save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql";then
					rm -f $save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql
					do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql]清理完成"
				fi
				if test -e "$save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz";then
					rm -f $save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz
					do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date -d -${save_days}day +%Y%m%d).sql.tar.gz]清理完成"
				fi
				
				if [ $zip_open -eq 1 ];then
				do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]开始压缩"
				cd $save_dir;tar -zcf ${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql.tar.gz ${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql
				if [ $? -ne 0 ];then
						do_writelog e "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]压缩异常"
						state=0
					else
						do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]压缩完成"
						if test -e "$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql";then
							rm -f $save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql &>/dev/null
							do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]清理完成"
						fi
						state=1
					fi
				else
					state=0
				fi
				
				if [ $open_close_ssh -eq 1 ];then
					if [ $state -eq 0 ];then
						scp  $save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql]异地转移成功"
						fi
					else
						scp  $save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql.tar.gz -P $ssh_port $ssh_user@$ssh_host:$ssh_dir
						if [ $? -ne 0 ];then
							do_writelog e "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql.tar.gz]异地转移失败"
						else
							do_writelog i "文件[$save_dir/${local_ip}_${db_list}_${i}_$(date +%Y%m%d).sql.tar.gz]异地转移成功"
						fi
					fi
				fi
            fi
        done
    fi
}

do_export_sql
