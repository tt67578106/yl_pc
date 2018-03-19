package com.ylw.service.wanlixing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.ylw.entity.vo.ImageVo;
import com.ylw.entity.vo.WanlixingProjectVo;
import com.ylw.entity.wanlixing.ActWanlixingProject;
import com.ylw.entity.wanlixing.ActWanlixingProjectImage;
import com.ylw.entity.wanlixing.ActWanlixingRegistration;
import com.ylw.repository.ActWanlixingProjectDao;
import com.ylw.repository.ActWanlixingProjectImageDao;
import com.ylw.repository.ActWanlixingRegistrationDao;
import com.ylw.util.HTMLInputFilter;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.TextUtil;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ActWanlixingProjectService {

	private ActWanlixingProjectDao actWanlixingProjectDao;
	@PersistenceContext
	private EntityManager em;
	
	private ActWanlixingRegistrationDao actWanlixingRegistrationDao;
	
	private ActWanlixingProjectImageDao actWanlixingProjectImageDao;
	
	public ActWanlixingProject getActWanlixingProject(java.lang.Integer id){
		return actWanlixingProjectDao.findOne(id);
	}

	public void save(ActWanlixingProject entity){
		actWanlixingProjectDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		actWanlixingProjectDao.delete(id);
	}

	public ActWanlixingProject getActWanlixingProjectByUserId(Integer userId) {
		List<ActWanlixingProject> apList = actWanlixingProjectDao.findByCeoUserId(userId);
		if(apList.size()>0){
			return apList.get(0);
		}
		return null;
	}
	public List<WanlixingProjectVo> getUserPage(Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType,Integer selectionProcess){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ActWanlixingProject> spec = buildSpecification(searchParams,selectionProcess);
		Page<ActWanlixingProject> projects = actWanlixingProjectDao.findAll(spec, pageRequest);
		List<WanlixingProjectVo> projectVos = new ArrayList<WanlixingProjectVo>();
		for(ActWanlixingProject pro:projects.getContent()){
			projectVos.add(buildProjectVo(pro));
		}
		return projectVos;
	}

	public Page<WanlixingProjectVo> getHqlPage(int pageNumber, int pageSize,Map<String, Object> searchParams) {
		String selectionProcess =null;
		String search_LIKE_project =null;
		if(searchParams!=null){
			selectionProcess = searchParams.get("EQ_selectionProcess")==null?null:searchParams.get("EQ_selectionProcess").toString();
			search_LIKE_project = searchParams.get("LIKE_project")==null?null:searchParams.get("LIKE_project").toString();
		}
		StringBuffer hql = new StringBuffer("select awp from ActWanlixingProject awp where awp.isValidate = 2 ");
		if(search_LIKE_project!=null && !search_LIKE_project.trim().equals("")){
			hql.append(" and ( awp.ceoName like :search_LIKE_project or  awp.school like :search_LIKE_project "
					+ " or awp.teamName like :search_LIKE_project or awp.title like :search_LIKE_project)");
		}
		if(selectionProcess!=null && TextUtil.isNumeric1(selectionProcess)&& (selectionProcess.equals("1") 
				|| selectionProcess.equals("2")  || selectionProcess.equals("3") ) ){
			hql.append(" and awp.selectionProcess IN (:selectionList) order by awp.votingCount desc ");
		}else {
			hql.append(" order by awp.validateTime desc ");
		}
		Query query = em.createQuery(hql.toString());
		Query countQuery = em.createQuery(hql.toString().replace("select awp from", "select count(1) from"));
		if(selectionProcess!=null && TextUtil.isNumeric1(selectionProcess)&& (selectionProcess.equals("1") 
				|| selectionProcess.equals("2")  || selectionProcess.equals("3") ) ){
			List<Integer> ids = new ArrayList<Integer>();
			if(selectionProcess.equals("3")){
				ids.add(3);
			}
			if(selectionProcess.equals("2")){
				ids.add(2);
				ids.add(3);
			}
			if(selectionProcess.equals("1")){
				ids.add(1);
				ids.add(2);
				ids.add(3);
			}
			query.setParameter("selectionList",ids);
			countQuery.setParameter("selectionList",ids);
		}
		if(search_LIKE_project!=null && !search_LIKE_project.trim().equals("")){
			query.setParameter("search_LIKE_project", "%"+search_LIKE_project+"%");
			countQuery.setParameter("search_LIKE_project", "%"+search_LIKE_project+"%");
		}
		Long totalSize = (Long) countQuery.getSingleResult();
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, "validateTime");
		query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		query.setMaxResults(pageRequest.getPageSize());
		List<ActWanlixingProject> projectList = query.getResultList();
		List<WanlixingProjectVo>  wvList = new ArrayList<WanlixingProjectVo>();
		for(ActWanlixingProject awp:projectList){
			wvList.add(buildProjectVo(awp));
		}
		Page<WanlixingProjectVo> page = new PageImpl<WanlixingProjectVo>(wvList, pageRequest, totalSize);
		return page;
	}
	
	/**
	 * 封装前台项目显示VO
	 * @param pro
	 * @return
	 */
	private WanlixingProjectVo buildProjectVo(ActWanlixingProject pro) {
		ImageVo image = getImageByProjectAndType(pro.getId(),4);
		WanlixingProjectVo wpv = new WanlixingProjectVo(pro.getId(),
				pro.getCeoName(), pro.getSchool(), pro.getVotingCount()==null?0:pro.getVotingCount(), pro.getTitle(),
				pro.getSubTitle(),pro.getTeamName(),pro.getIntroduction(),
				image==null?null:image.getImgPath(), pro.getPdfFile());
		return wpv;
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("validateTime".equals(sortType)) {
			sort = new Sort(Direction.ASC, "validateTime");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ActWanlixingProject> buildSpecification(Map<String, Object> searchParams,Integer selectionProcess) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		if(selectionProcess!=null && selectionProcess!=0){
			filters.put("selectionProcess", new SearchFilter("selectionProcess", Operator.EQ, selectionProcess));
		}
		filters.put("isValidate", new SearchFilter("isValidate", Operator.EQ, 2));
		Specification<ActWanlixingProject> spec = DynamicSpecifications.bySearchFilter(filters.values(), ActWanlixingProject.class);
		return spec;
	}

	public void updateProject(Integer userId, ActWanlixingProject project, String rstudentCardScan, String ridCardScanRprintScan, String rprintScan,
			String leaderImage,String leaderTeam) {
		if(project.getIsValidate()!=null && project.getIsValidate()==3){
			ActWanlixingRegistration registration = getActWanlixingRegistrationByUserId(userId);
			if(registration!=null){
				registration.setPrintScan(getImagePath(rprintScan));
				registration.setIdCardScan(getImagePath(ridCardScanRprintScan));
				registration.setStudentCardScan(getImagePath(rstudentCardScan));
				actWanlixingRegistrationDao.save(registration);
				project.setIsValidate(1);
			}
		}
		project.setPptFile(getImagePath(project.getPptFile()));
		if(project.getCeoUserId()==null){
			project.setCeoUserId(userId);
		}
		save(project);
		actWanlixingProjectImageDao.deleteByProjectId(project.getId());
		if(StringUtils.isNotBlank(leaderImage)){
				ActWanlixingProjectImage image = new ActWanlixingProjectImage();
				image.setProjectId(project.getId());
				image.setImagePath(getImagePath(leaderImage));
				image.setImageType(4);
				image.setCreateTime(new Date());
				actWanlixingProjectImageDao.save(image);
		}
		if(leaderTeam!=null){
				for(String str:leaderTeam.split(",")){
					if(str.split("#").length>0){
						String path = str.split("#")[0];
							ActWanlixingProjectImage imageFor = new ActWanlixingProjectImage();
							imageFor.setProjectId(project.getId());
							imageFor.setImagePath(getImagePath(path));
							if(str.split("#").length>1){
								imageFor.setIntroduction(HTMLInputFilter.clean(str.split("#")[1]));
							}
							if(str.split("#").length>2 && TextUtil.isNumeric1(str.split("#")[2])){
								imageFor.setSorting(Integer.parseInt(str.split("#")[2]));
							}
							imageFor.setImageType(1);
							imageFor.setCreateTime(new Date());
							actWanlixingProjectImageDao.save(imageFor);
					}
			}
	}
}
	public ImageVo buildRegistrationUrl(String url) {
		ImageVo wp = new ImageVo();
		if(StringUtils.isNotBlank(url)){
			String key = new Random().nextInt()+"";
			StoreCacheUtil.putCache("updateImagePath",key,url);
			wp = new ImageVo(url,key);
		}
		return wp;
	}
	public ActWanlixingRegistration getActWanlixingRegistrationByUserId(Integer userId) {
		List<ActWanlixingRegistration> apList = actWanlixingRegistrationDao.findByUserId(userId);
		if(apList.size()>0){
			return apList.get(0);
		}
		return null;
	}
	/**
	 * 得到单张图片地址
	 * @param id
	 * @param type
	 * @return
	 */
	public ImageVo getImageByProjectAndType(Integer id, int type) {
		List<ActWanlixingProjectImage> images = actWanlixingProjectImageDao.findByprojectIdAndImageTypeOrderBySortingAsc(id,type);
		if(images.size()>0){
			return buildRegistrationUrl(images.get(0).getImagePath());
		}
		return new ImageVo();
	}
	/**
	 * 根据缓存key取图片地址
	 * @param key
	 * @return
	 */
	public String getImagePath(String key){
		return StoreCacheUtil.getCacheValue("updateImagePath", key)==null?null:StoreCacheUtil.getCacheValue("updateImagePath", key).toString();
	}
	public ActWanlixingProject cleanXSSProject(ActWanlixingProject project) {
		project.setSchool(project.getSchool());
		project.setIntroduction(project.getIntroduction());
		project.setVideo(project.getVideo());
		project.setTeamName(project.getTeamName());
		project.setTitle(project.getTitle());
		return project;
	}
	@Autowired
	public void setActWanlixingProjectDao(ActWanlixingProjectDao actWanlixingProjectDao) {
		this.actWanlixingProjectDao = actWanlixingProjectDao;
	}
	@Autowired
	public void setActWanlixingRegistrationDao(
			ActWanlixingRegistrationDao actWanlixingRegistrationDao) {
		this.actWanlixingRegistrationDao = actWanlixingRegistrationDao;
	}
	@Autowired
	public void setActWanlixingProjectImageDao(
			ActWanlixingProjectImageDao actWanlixingProjectImageDao) {
		this.actWanlixingProjectImageDao = actWanlixingProjectImageDao;
	}
}