package com.ylw.service.wanlixing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.ylw.entity.code.CodeAreaProvince;
import com.ylw.entity.vo.WanlixingPeriodVo;
import com.ylw.entity.wanlixing.ActWanlixingPeriod;
import com.ylw.repository.ActWanlixingPeriodDao;
import com.ylw.repository.CodeAreaProvinceDao;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ActWanlixingPeriodService {

	private ActWanlixingPeriodDao actWanlixingPeriodDao;
	
	private CodeAreaProvinceDao codeAreaProvinceDao;
	
	public ActWanlixingPeriod getActWanlixingPeriod(java.lang.Integer id){
		return actWanlixingPeriodDao.findOne(id);
	}

	public void save(ActWanlixingPeriod entity){
		actWanlixingPeriodDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		actWanlixingPeriodDao.delete(id);
	}

	/**
	 * 根据省查询活动列表
	 * @param provinceId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<ActWanlixingPeriod> getPeriodPage(java.lang.Integer provinceId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ActWanlixingPeriod> spec = buildSpecification(provinceId, searchParams);
		 Page<ActWanlixingPeriod> page = actWanlixingPeriodDao.findAll(spec, pageRequest);
		 return  page;
	}

	/**
	 * 有活动的省 以及对应场次
	 * @return
	 */
	public List<WanlixingPeriodVo>  findPrivinceList(){
		List<Integer> awpList = actWanlixingPeriodDao.findByisPublish();
		if(awpList.size()<=0){
			awpList.add(-1);
		}
		List<CodeAreaProvince> codeList = codeAreaProvinceDao.findByIdInAndStatus(awpList, 1,buildPageRequest(1,15,"auto")).getContent();
		List<WanlixingPeriodVo> wpList = new ArrayList<WanlixingPeriodVo>();
		for(CodeAreaProvince codeAreaProvince:codeList){
			wpList.add(buildWanlixingPeriodVo(codeAreaProvince));
		}
		return wpList;
	}
	

	private WanlixingPeriodVo buildWanlixingPeriodVo(CodeAreaProvince codeAreaProvince) {
		return new WanlixingPeriodVo(codeAreaProvince.getAbbreviation(),codeAreaProvince.getId(),codeAreaProvince.getTypes(),
				findCountByProvince(codeAreaProvince.getId()));
	}

	/**
	 * 根据省查询活动数量
	 * @param provinceId
	 * @return
	 */
	public Integer findCountByProvince(Integer provinceId) {
		return actWanlixingPeriodDao.findByisPublishAndProvinceId(1, provinceId).size();
	}
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.ASC, "id");
		} else if ("startDate".equals(sortType)) {
			sort = new Sort(Direction.DESC, "startDate");
		} else if ("createTime".equals(sortType)) {
			sort = new Sort(Direction.DESC, "createTime");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ActWanlixingPeriod> buildSpecification(Integer provinceId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("isPublish", new SearchFilter("isPublish", Operator.EQ, 1));
		filters.put("provinceId", new SearchFilter("provinceId", Operator.EQ, provinceId));
		Specification<ActWanlixingPeriod> spec = DynamicSpecifications.bySearchFilter(filters.values(), ActWanlixingPeriod.class);
		return spec;
	}

	@Autowired
	public void setActWanlixingPeriodDao(ActWanlixingPeriodDao actWanlixingPeriodDao) {
		this.actWanlixingPeriodDao = actWanlixingPeriodDao;
	}

	@Autowired
	public void setCodeAreaProvinceDao(CodeAreaProvinceDao codeAreaProvinceDao) {
		this.codeAreaProvinceDao = codeAreaProvinceDao;
	}
}