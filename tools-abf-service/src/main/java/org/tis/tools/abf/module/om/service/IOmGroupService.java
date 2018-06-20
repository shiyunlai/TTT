package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.tis.tools.abf.module.common.entity.vo.TreeDetail;
import org.tis.tools.abf.module.om.controller.request.OmPositionRequest;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.OmGroup;
import org.tis.tools.abf.module.om.entity.OmPosition;
import org.tis.tools.abf.module.om.entity.enums.OmGroupType;
import org.tis.tools.abf.module.om.entity.vo.OmPositionDetail;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

/**
 * omGroup的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmGroupService extends IService<OmGroup>  {

    /*
     * ==========================================
     *
     * 工作组管理相关的服务
     *
     * 生成工作组代码
     * 新增工作组
     * 拷贝工作组
     * 移动工作组
     * 修改工作组
     * 删除工作组
     *
     * ==========================================
     */

    /**
     * 根据groupCode查询该工作组
     *
     * @param groupCode
     * @return
     */
    OmGroup selectGroupByCode(String groupCode);

    /**
     * <pre>
     * 生成工作组代码
     *
     * </pre>
     *
     * @param groupType
     *            工作组类型（值来自业务菜单： DICT_OM_GROUPTYPE）
     * @return 工作组代码
     * @throws OrgManagementException
     *
     */
    String genGroupCode(String groupType) throws OrgManagementException;

    /**
     * <pre>
     * 新增工作组（指定最少必要数据）
     *
     * 说明：
     * 程序检查数据合法性；
     * 程序自动将“隶属机构代码”、“父工作组代码”转为对应的guid，建立数据层面的引用关联（避免修改XX代码时需要连带更新）。
     * 新增的工作组状态为‘正常’；
     *
     * </pre>
     *
     * @param groupType
     *            新工作组类型
     * @param groupName
     *            新工作组名称
     * @param orgCode
     *            隶属机构代码
     * @param parentGroupCode
     *            父工作组代码
     * @return 新增的工作组对象
     * @throws OrgManagementException
     */
    void createGroup(OmGroupType groupType, String groupName, String orgCode, String parentGroupCode )  throws OrgManagementException ;


    /**
     * 查询所有工作组.
     * @return
     * @throws OrgManagementException
     */
    Page<OmGroup> queryAllGroup(Page<OmGroup> page) throws OrgManagementException;

    /**
     * <pre>
     * 浅拷贝工作组
     *
     * 说明：
     * 只复制工作组表（OM_GROUP）记录
     *
     * </pre>
     *
     * @param fromGroupCode
     *            参考工作组代码
     * @param toOrgCode
     *            新工作组隶属机构
     * @param toParentGroupCode
     *            新工作组的父工作组
     * @return 拷贝新增的工作组对象
     * @throws OrgManagementException
     */
    OmGroup copyGroup(String fromGroupCode, String toOrgCode, String toParentGroupCode )  throws OrgManagementException ;

    /**
     * <pre>
     * 深度拷贝工作组
     *
     * 可指定深度复制内容包括：
     * 是否拷贝子工作组（直到叶节点）
     * 是否拷贝关联应用
     * 是否拷贝关联岗位
     * 是否拷贝员工关系
     *
     * </pre>
     *
     * @param fromGroupCode
     *            参考工作组代码
     * @param toOrgCode
     *            新工作组隶属机构
     * @param toParentGroupCode
     *            新工作组父工作组
     * @param copyChild
     *            是否拷贝子工作组</br>
     *            true - 拷贝 </br>
     *            false - 不拷贝
     * @param copyApp
     *            是否拷贝关联应用</br>
     *            true - 拷贝 </br>
     *            false - 不拷贝
     * @param copyPosition
     *            是否拷贝关联岗位</br>
     *            true - 拷贝 </br>
     *            false - 不拷贝
     * @param copyEmployee
     *            是否拷贝员工关系</br>
     *            true - 拷贝 </br>
     *            false - 不拷贝
     * @return 拷贝新增的工作组对象
     * @throws OrgManagementException
     */
    OmGroup copyGroupDeep(String fromGroupCode, String toOrgCode, String toParentGroupCode,
                          boolean copyChild, boolean copyApp, boolean copyPosition, boolean copyEmployee ) throws OrgManagementException ;

    /**
     * <pre>
     * 移动工作组
     *
     * 说明：
     * 下级工作组（一直到叶子工作组）都跟随移动；
     * 可移动工作组隶属机构关系；
     * 可移动工作组父子关系；
     *
     * </pre>
     *
     * @param groupCode
     *            被移动工作组代码
     * @param fromParentGroupCode
     *            原父工作组代码
     * @param toParentGroupCode
     *            新父工作组代码（如果隶属机构不同被移动工作组，则属于移动了隶属机构关系）
     * @return 移动后的工作组对象
     * @throws OrgManagementException
     */
    OmGroup moveGroup(String groupCode, String fromParentGroupCode, String toParentGroupCode) throws OrgManagementException  ;

    /**
     * <pre>
     * 修改工作组
     *
     * 说明：
     * 只修改传入对象（newOmGroup）有值的字段；
     * 应避免对（逻辑上）不可直接修改字段的更新，如：工作组状态不能直接通过修改而更新；
     * </pre>
     *
     * @param newOmGroup
     *            新工作组对象
     * @return 修改后的工作组对象
     * @throws OrgManagementException
     */
    void updateGroup(OmGroup newOmGroup) throws OrgManagementException  ;

    /**
     * <pre>
     * 删除工作组
     *
     * 说明：
     * 只能删除‘注销’状态下的工作组；
     * 同时删除子工作组；
     *
     * </pre>
     * @param groupCode 工作组代码
     * @throws OrgManagementException
     */
    void deleteGroup(String groupCode ) throws OrgManagementException  ;

    /*
     * ==========================================
     *
     * 工作组状态管理服务
     *
     * 注销工作组
     * 重新启用工作组
     *
     * ==========================================
     */

    /**
     * <pre>
     * 注销工作组
     *
     * 说明：
     * 一并注销所有子工作组（直到叶节点）
     * 注销状态下，除信息展示，不能对工作组做操作
     *
     * </pre>
     *
     * @param groupCode
     *            工作组代码
     * @throws OrgManagementException
     */
    void cancelGroup(String groupCode) throws OrgManagementException  ;

    /**
     * <pre>
     * 重新启用工作组
     *
     * </pre>
     *
     * @param groupCode
     *            工作组代码
     * @param reenableChild
     *            是否同时重新启用子工作组</br>
     *      *            true - 启用 </br>
     *            false - 不启用（默认）
     * @throws OrgManagementException
     */
    void reenableGroup(String groupCode, boolean reenableChild) throws OrgManagementException  ;

    /*
     * ==========================================
     *
     * 工作组相关信息的查询服务
     *
     * 查询工作组信息
     * 查询子工作组列表
     * 查询工作组关联应用（OM_APP_GROUP）列表
     * 查询工作组中的岗位列表
     * 查询工作组中的员工列表
     * 查询工作组权限（角色）集
     *
     * ==========================================
     */

    /**
     * <per>
     * 查询工作组概要信息
     * </per>
     *
     * @param groupCode 工作组代码
     * @return 工作组
     */
    //FIXME 此处返回 OmGroup 数据库DO对象，对于调用者来说并不友好，应该将其中的数据主键guid转化为业务代码 —— 其他＊RService中如是
    OmGroup queryGroupByCode(String groupCode) ;

    /**
     * <per>
     * 查询根工作组
     * </per>
     *
     * @return 根工作组列表
     */
    Page<OmGroup> selectRootGroup(Page<OmGroup> page);

    /**
     * <per>
     * 查询工作组下（第一层）子工作组
     * </per>
     *
     * @param parentsCode
     *            工作组父parentsCode
     * @return 子工作组列表
     */
    Page<OmGroup> selectChildGroup(String parentsCode, Page<OmGroup> page);



    /**
     * <per>
     * 查询属于该工作组的（完整的）岗位列表
     *
     * 说明：
     * 某个工作组有多个岗位，这些岗位可能是平级（List<OmPositionDetail>）;
     * 每个岗位也可能是存在层级关系（OmPositionDetail）；
     * 本查询功能组织后返回完整的岗位树信息；
     * </per>
     *
     * @param groupCode
     *            工作组代码
     * @return 岗位列表（返回完整的岗位树）
     */
    List<OmPositionDetail> selectPosition(String groupCode) ;

//	/**
//	 * <per>
//	 * 查询属于该工作组的（完整的）岗位列表
//	 *
//	 * 说明：
//	 * 返回岗位信息为平级列表展示,父子关系由字段展示
//	 * </per>
//	 *
//	 * @param groupCode
//	 *            工作组代码
//	 * @return 岗位列表
//	 */
//	List<OmPositionDetail> queryPosition(String groupCode) ;

    /**
     * <per>
     * 查询在该工作组的员工列表
     * </per>
     *
     * @param groupCode
     *            工作组代码
     * @return 员工列表
     */
    Page<OmEmployee> selectEmployee(String groupCode, Page<OmEmployee> page);

    /**
     * 查询不属于此工作组并且在指定机构下的人员信息
     * @param guidOrg
     * @param groupCode
     * @return
     */
    Page<OmEmployee> selectEmpNotInGroup(String guidOrg, String groupCode, Page<OmEmployee> page);

    /**
     * 查询当前工作组下的岗位
     * @param groupCode
     * @return
     */
    Page<OmPosition> selectPositionInGroup(String groupCode, Page<OmPosition> page);

    /**
     * 查询当前不在此工作组下的岗位,同时保证在同一机构下
     * @param groupCode
     * @return
     */
    Page<OmPosition> selectPositionNotInGroup(String groupCode, Page<OmPosition> page);

    /**
     * <pre>
     * 查询工作组权限（角色）集
     * </pre>
     *
     * @param groupCode
     *            工作组代码
     * @return 权限（角色）集
     */
    List<AcRole> selectRole(String groupCode);
    /**
     * 查询该工作组下所有子工作组
     * @param groupCode
     * @return
     */
    Page<OmGroup> selectAllchild(String groupCode, Page<OmGroup> page);

    /**
     * 添加岗位-工作组关系表数据
     */
    void insertGroupPosition(String groupCode, OmPositionRequest omPositionRequest);

    /**
     * 删除岗位-工作组关系表数据
     *
     * @param ogpGuidList
     */
    void deleteGroupPosition(List<String> ogpGuidList);

    /**
     * 根据岗位code获取guid
     *
     * @param positionCode
     * @return
     */
    String selectPositionGuidByCode(String positionCode);

    /**
     * 通过工作组名称检索工作组
     * @param groupName
     * @return 符合条件的工作组
     */
    Page<OmGroup> selectByGroupName(String groupName, Page<OmGroup> page);

    /**
     * <per>
     * 查询与工作组关联的应用列表
     * </per>
     *
     * @param groupCode
     *            工作组代码
     * @return 应用列表
     */
    Page<AcApp> selectApp(String groupCode,Page<AcApp> page );

    /**
     * 查询可为工作组添加的应用列表
     * @param groupCode
     * @return
     */
    List<AcApp> selectAppNotInGroup(String groupCode);

    /**
     * 新增一条工作组-应用关联信息
     * @param appGuidList
     * @param groupGuid
     */
    void addGroupApp(List<String> appGuidList, String groupGuid);

    /**
     * 删除一条工作组-应用关联信息
     *
     * @param groupCode
     */
    void deleteGroupApp(String groupCode);

    /**
     * 工作组树结构
     *
     * @param guid
     * @return
     */
    TreeDetail selectGroupTree(String guid);

}

