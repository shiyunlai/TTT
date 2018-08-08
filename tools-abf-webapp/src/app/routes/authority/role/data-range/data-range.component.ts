import { Component, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import {RoleModule} from '../../../../service/role/role.model';
import {UtilityService} from '../../../../service/utils.service';
import {NzModalService, NzNotificationService} from 'ng-zorro-antd';
import {ActivatedRoute, Router} from '@angular/router';
import {appConfig} from '../../../../service/common';
@Component({
  selector: 'app-data-range',
  templateUrl: './data-range.component.html',
})
export class DataRangeComponent implements OnInit {
    roleGuid: string;
    constructor(
        private http: _HttpClient,
        private router: Router,
        private activatedRoute: ActivatedRoute, // 注入路由，接收到参数
        private nznot: NzNotificationService,
        private utilityService: UtilityService,
    ) { }

    ngOnInit() {
        this.roleGuid = this.activatedRoute.snapshot.params.id; // 拿到父组件传过来的组织机构的guid来进行操作
        console.log(this.roleGuid);
        this.getData()
    }
     headerData = [  // 配置表头内容
        {value: '数据实体', key: 'dataEntity',  isclick: false},
        {value: '应用名称' , key: 'applyName', isclick: true},
        {value: '可查看数据范围' , key: 'viewRange', isclick: true},
        {value: '可管理数据范围', key: 'manageableRange',  isclick:  false},
        {value: '自定义数据范围' , key: 'customRange', isclick: false},
    ];

    headerDataAdd = [
        {value: '实体名称', key: 'dataEntity',  isclick: false},
        {value: '对应表名称' , key: 'tableName', isclick: true},
        {value: '应用名称' , key: 'applyName', isclick: true},
        {value: '实体描述信息', key: 'entityDescription',  isclick:  false},
      
    ]
    data=[];
    dataAdd=[];
    showAdd = false;
    showAddAdd = true;
    searchValue = '';
    dataAddModal = false; //新增模态框
    //搜索
    isNull = true;// 是否有数据
    total:number;
    totalAdd:number
    getData(){
          this.utilityService.getData(appConfig.ABFUrl + '/' + appConfig.API.dataRangeList)
            .subscribe(
                (val) => {
                    console.log(val)
                    this.data = val
                    this.total = 2
                
                });
    }
     onSearch(event: string): void {
            console.log(event);
        }
        // 新增数据范围
        addRange(){
          this.dataAddModal = true;
        }
        // 新增数据提交
        subAdddata(){
              
        }
}
