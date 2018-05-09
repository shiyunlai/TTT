//package org.tis.tools.abf.module.om.service.impl;
//
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.tis.tools.abf.module.om.dao.OmBusiorgMapper;
//import org.tis.tools.abf.module.om.entity.OmBusiorg;
//import org.tis.tools.abf.module.om.entity.OmOrg;
//import org.tis.tools.abf.module.om.service.IOmBusiorgService;
//import org.tis.tools.abf.module.om.service.IOmOrgService;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.tis.tools.common.utils.BasicUtil.surroundBracketsWithLFStr;
//import static org.tis.tools.common.utils.BasicUtil.wrap;
//
//public class OmBusiOrgServiceImpl extends ServiceImpl<OmBusiorgMapper,OmBusiorg> implements IOmBusiorgService {
//    /**
//     * 拷贝新增时，代码前缀
//     */
//    private static final String CODE_HEAD_COPYFROM = "Copyfrom-";
//    @Autowired
//    IOmBusiorgService omBusiorgService;
//
//    @Autowired
//    IOmOrgService omOrgService;
//
//    @Override
//    public void addOmBusiorg(OmBusiorg t) {
//        /**
//         * 机构码已存在
//         */
////        if (isExitByBusiorgCode(newBusiorgCode)) {
////            throw new BusiOrgManagementException(OMExceptionCodes.PARMS_NOT_ALLOW_EMPTY, BasicUtil.wrap("busiDomain"));
////        }
//        OmBusiorg omBusiorg = new OmBusiorg();
////        omBusiorg.setGuid(GUID.busiorg());
//        omBusiorg.setGuid(t.getGuid());
//        omBusiorg.setNodeType(t.getNodeType());
//        omBusiorg.setBusiDomain(t.getBusiDomain());
//        //收集入参
//        omBusiorg.setBusiorgCode(t.getBusiorgCode());
//        omBusiorg.setBusiDomain(t.getBusiDomain());
//        omBusiorg.setBusiorgName(t.getBusiorgName());
//        //查询对应机构GUID
//        OmOrg  om_org = omOrgService.queryOrg(t.getOrgCode());
//        wc.andEquals("ORG_CODE",orgCode);
//        List<OmOrg> orgList = omOrgService.query(wc);
//        if(orgList.size() != 1){
//            throw new OrgManagementException(OMExceptionCodes.ORGANIZATION_NOT_EXIST_BY_ORG_CODE);
//        }
//        String guidOrg = orgList.get(0).getGuid();
//        omBusiorg.setOrgCode(orgCode);
//        omBusiorg.setGuidOrg(guidOrg);
//        //判断是否是新增根机构
//        if (parentsBusiorgCode == null || parentsBusiorgCode == "") {
//            omBusiorg.setGuidParents(null);
//            omBusiorg.setBusiorgLevel(new BigDecimal("0"));
//            omBusiorg.setSeqno(omBusiorg.getGuid());
//            omBusiorg.setSortno(new BigDecimal("0"));
//            omBusiorg.setIsleaf(CommonConstants.YES);
//            omBusiorg.setSubCount(new BigDecimal("0"));
//            // 新增机构
//            try {
//                omBusiorgService.insert(omBusiorg);
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new BusiOrgManagementException(
//                        OMExceptionCodes.FAILURE_WHRN_CREAT_BUSIORG,
//                        wrap(e.getCause().getMessage()), "新增业务机构（实际机构）！{0}");
//            }
//        } else {
//            OmBusiorg parentOmBusiorg = queryBusiorg(parentsBusiorgCode);
//            omBusiorg.setGuidParents(parentOmBusiorg.getGuid());
//            omBusiorg.setBusiorgLevel(parentOmBusiorg.getBusiorgLevel().add(new BigDecimal("1")));
//            omBusiorg.setSeqno(parentOmBusiorg.getSeqno() + "." + omBusiorg.getGuid());
//            omBusiorg.setSortno(new BigDecimal("0"));
//            omBusiorg.setIsleaf(CommonConstants.YES);
//            omBusiorg.setSubCount(new BigDecimal("0"));
//            //更新父机构
//            parentOmBusiorg.setIsleaf(CommonConstants.NO);
//            parentOmBusiorg.setSubCount(parentOmBusiorg.getSubCount().add(new BigDecimal("1")));
//            // 新增机构
//            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//                @Override
//                public void doInTransactionWithoutResult(TransactionStatus status) {
//                    try {
//                        omBusiorgService.update(parentOmBusiorg);
//                        omBusiorgService.insert(omBusiorg);
//                    } catch (Exception e) {
//                        status.setRollbackOnly();
//                        e.printStackTrace();
//                        throw new BusiOrgManagementException(
//                                OMExceptionCodes.FAILURE_WHRN_CREAT_BUSIORG,
//                                wrap(e.getCause().getMessage()), "新增业务机构（实际机构）！{0}");
//                    }
//                }
//            });
//        }
//        return omBusiorg;
//    }
//
//    @Override
//    public void update(OmBusiorg t) {
//
//    }
//
//    @Override
//    public void updateForce(OmBusiorg t) {
//
//    }
//
//    @Override
//    public void delete(String guid) {
//
//    }
//
//    @Override
//    public OmBusiorg loadByGuid(String guid) {
//        return null;
//    }
//}
