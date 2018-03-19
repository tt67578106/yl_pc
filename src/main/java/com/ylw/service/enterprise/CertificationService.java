package com.ylw.service.enterprise;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ylw.entity.enterprise.Certification;
import com.ylw.entity.enterprise.CertificationWorkOrder;
import com.ylw.entity.sys.SysUser;
import com.ylw.repository.CertificationDao;
import com.ylw.repository.CertificationWorkOrderDao;
import com.ylw.util.FtpUploadUtil;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.RandomSeriNoUtils;
import com.ylw.util.StoreCacheUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class CertificationService {
	
	private CertificationDao certificationDao;
	
	private CertificationWorkOrderDao certificationWorkOrderDao;
	
	@Autowired
	private CertificationWorkOrderService certificationWorkOrderService;
	@Autowired
	public void setCertificationWorkOrderDao(CertificationWorkOrderDao certificationWorkOrderDao) {
		this.certificationWorkOrderDao = certificationWorkOrderDao;
	}

	public Certification getSiteCertification(java.lang.Integer id){
		return certificationDao.findOne(id);
	}
	
	public void save(Certification entity){
		certificationDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		certificationDao.delete(id);
	}
	/**
	 * 根据企业id得到验证信息
	 * @param companyId
	 * @return
	 */
	public Certification getByCompanyId(java.lang.Integer companyId){
		return certificationDao.getByCompanyId(companyId);
	}

	public Page<Certification> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Certification> spec = buildSpecification(userId.longValue(), searchParams);
		return certificationDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Certification> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Certification> spec = DynamicSpecifications.bySearchFilter(filters.values(), Certification.class);
		return spec;
	}

	@Autowired
	public void setSiteCertificationDao(CertificationDao certificationDao) {
		this.certificationDao = certificationDao;
	}
	/**
	 * 保存申请认证信息并生成认证表单
	 * @param companyid
	 * @param certification
	 */
	public void saveCertification(SysUser user,Integer companyid, Certification certification) {
		//审核信息
		Integer mark=0;
		Certification dbcertification =null;
		if (getByCompanyId(companyid) == null) {
			dbcertification = new Certification();
			dbcertification.setCreateTime(DateConvertUtils.getNow());
			mark+=1;
		} else {
			dbcertification = getByCompanyId(companyid);
		}
		dbcertification.setCompanyId(companyid);
		if (!certification.getEnterpriseFullName().equals(dbcertification.getEnterpriseFullName())) {
			dbcertification.setEnterpriseFullName(HTMLInputFilter.clean(certification.getEnterpriseFullName()));
			mark+=1;
		}
		String applicationLeterImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "applicationLeterImg"+user.getId()+companyid);
		if ( applicationLeterImg!= null&&!applicationLeterImg.equals("")&&!applicationLeterImg.equals(dbcertification.getApplicationLeterImg())) {//申请公函
			dbcertification.setApplicationLeterImg(applicationLeterImg);
			mark+=1;
		}
		if (!certification.getBusinessLicenseNum().equals(dbcertification.getBusinessLicenseNum())) {
			dbcertification.setBusinessLicenseNum(HTMLInputFilter.clean(certification.getBusinessLicenseNum()));
			mark+=1;
		}
		String businessLicenseImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "businessLicenseImg"+user.getId()+companyid);
		if (businessLicenseImg != null&&!businessLicenseImg.equals("")&&!businessLicenseImg.equals(dbcertification.getBusinessLicenseImg())) {//营业执照
			dbcertification.setBusinessLicenseImg(businessLicenseImg);
			mark+=1;
		}
		if (!certification.getOrganizationCode().equals(dbcertification.getOrganizationCode())) {
			dbcertification.setOrganizationCode(HTMLInputFilter.clean(certification.getOrganizationCode()));
			mark+=1;
		}
		String organizationCodeImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "organizationCodeImg"+user.getId()+companyid);
		if (organizationCodeImg != null&&!organizationCodeImg.equals("")&&!organizationCodeImg.equals(dbcertification.getOrganizationCodeImg())) {//组织机构代码证
			dbcertification.setOrganizationCodeImg(organizationCodeImg);
			mark+=1;
		}
		if (!certification.getEnterpriseBank().equals(dbcertification.getEnterpriseBank())) {
			dbcertification.setEnterpriseBank(HTMLInputFilter.clean(certification.getEnterpriseBank()));
			mark+=1;
		}
		if (!certification.getEnterpriseBankBranch().equals(dbcertification.getEnterpriseBankBranch())) {
			dbcertification.setEnterpriseBankBranch(HTMLInputFilter.clean(certification.getEnterpriseBankBranch()));
			mark+=1;
		}
		if (!certification.getEnterpriseBankAccount().equals(dbcertification.getEnterpriseBankAccount())) {
			dbcertification.setEnterpriseBankAccount(HTMLInputFilter.clean(certification.getEnterpriseBankAccount()));
			mark+=1;
		}
		String openingPermitImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "openingPermitImg"+user.getId()+companyid);
		if (openingPermitImg != null&&!openingPermitImg.equals("")&&!openingPermitImg.equals(dbcertification.getOpeningPermitImg())) {//开户许可证
			dbcertification.setOpeningPermitImg(openingPermitImg);
			mark+=1;
		}
		if (!certification.getLegalRepresentative().equals(dbcertification.getLegalRepresentative())) {
			dbcertification.setLegalRepresentative(HTMLInputFilter.clean(certification.getLegalRepresentative()));
			mark+=1;
		}
		if (!certification.getLegalIdCard().equals(dbcertification.getLegalIdCard())) {
			dbcertification.setLegalIdCard(HTMLInputFilter.clean(certification.getLegalIdCard()));
			mark+=1;
		}
		String legalIdCardFrontImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "legalIdCardFrontImg"+user.getId()+companyid);
		if (legalIdCardFrontImg != null&&!legalIdCardFrontImg.equals("")&&!legalIdCardFrontImg.equals(dbcertification.getLegalIdCardFrontImg())) {//法人身份证正面
			dbcertification.setLegalIdCardFrontImg(legalIdCardFrontImg);
			mark+=1;
		}
		String legalIdCardBackImg= (String) StoreCacheUtil.getCacheValue("certificationImagePath", "legalIdCardBackImg"+user.getId()+companyid);
		if (legalIdCardBackImg != null&&!legalIdCardBackImg.equals("")&&!legalIdCardBackImg.equals(dbcertification.getLegalIdCardBackImg())) {//法人身份证反面
			dbcertification.setLegalIdCardBackImg(legalIdCardBackImg);
			mark+=1;
		}
		if (!certification.getApplicantName().equals(dbcertification.getApplicantName())) {
			dbcertification.setApplicantName(HTMLInputFilter.clean(certification.getApplicantName()));
			mark+=1;
		}
		if (!certification.getApplicantMobile().equals(dbcertification.getApplicantMobile())) {
			dbcertification.setApplicantMobile(HTMLInputFilter.clean(certification.getApplicantMobile()));
			mark+=1;
		}
		if(mark>0)
		{
			dbcertification.setStatus(0);//未认证
			certificationDao.save(dbcertification);
			
			//认证公单信息
			CertificationWorkOrder dbCertificationWorkOrder = certificationWorkOrderService.findByCertificationIdAndStatus(dbcertification.getId(), 0);
			if (dbCertificationWorkOrder == null) {
				CertificationWorkOrder certificationWorkOrder = new CertificationWorkOrder();
				certificationWorkOrder.setCode(RandomSeriNoUtils.genCodeByTime("WO"));
				certificationWorkOrder.setCertificationId(dbcertification.getId());
				certificationWorkOrder.setCompanyId(companyid);
				certificationWorkOrder.setCreateTime(DateConvertUtils.getNow());
				certificationWorkOrder.setStatus(0);
				certificationWorkOrder.setCreateUserId(user.getId());
				certificationWorkOrder.setTitle(HTMLInputFilter.clean(user.getLoginName()+"用户申请认证"+dbcertification.getEnterpriseFullName()+"的企业信息"));
				certificationWorkOrderDao.save(certificationWorkOrder);
			}
		}
	}
	/**
	 * 将申请认证的图片路径放在ehcache中
	 * @param key
	 * @param usreid
	 * @param companyid
	 * @param file
	 * @param alyPath
	 * @return
	 */
	public String uploadImage(String key, Integer usreid, Integer companyid, MultipartFile file, String alyPath) {
		String imagePathCache = FtpUploadUtil.upload(alyPath, file);
		if (!imagePathCache.equals("")) {
			key = key + usreid + companyid;
			StoreCacheUtil.putCache("certificationImagePath", key, imagePathCache);
			return imagePathCache;
		}
		return null;
	}
	/**
	 * 重新申请认证
	 * @param certificationId
	 * @return
	 */
	public int refCertify(Integer certificationId) {
		int result=0;
		result=certificationDao.updateStatusById(certificationId);
		return result;
	}
}