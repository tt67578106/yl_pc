package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company,java.lang.Integer>, JpaSpecificationExecutor<Company>{

	@Query("select company from Company company where company.city.id in (:cityIdList) and company.isdel=1 ")
	List<Company> findByCity(@Param("cityIdList")List<Integer> cityIdList,Pageable page);

	@Query("select company.id from Company company where company.city.id =:cityId and company.industryid=:industryid and company.isdel=1 and company.id != :companyId")
	Page<Integer> findByCityIdAndIndustryid(@Param("cityId")Integer cityId,@Param("industryid")Integer industryid,@Param("companyId")Integer companyId,Pageable page);

	@Query("select count(id) from Company company where company.updatetime >= :date and company.isdel=1 ")
	Long getCountByTime(@Param("date")Date date);

	List<Company> getByCreateby(Integer userid);
	
	@Modifying
	@Query(" update Company set validation=2 where id=:enterpriseId")
	int updateValidationById(@Param("enterpriseId")Integer enterpriseId);

	@Query("select company from Company company where company.city.id = :cityId and company.isdel = 1 "
			+ " and validation = 1 and  company.createtime < :now ORDER BY	company.createtime  DESC")
	Page<Company> getNextCompany(@Param("cityId")Integer cityId, @Param("now")Date createtime,Pageable buildPageRequest);

	@Query("select company from Company company where company.city.id = :cityId and company.isdel = 1 "
			+ " and validation = 1  ORDER BY company.createtime  DESC")
	Page<Company>  getLastCompany(@Param("cityId")Integer cityId, Pageable buildPageRequest);
	
	@Query("select company from Company company where company.id in :companyIds ")
	List<Company> findByIds(@Param("companyIds")List<Integer> companyIds);

	/**
	 * 根据id和是否删除对象查询
	 * @param id
	 * @param isdel
	 * @return
	 */
	@Query("select company from Company company where company.id = :id and company.isdel = :isdel ")
	Company getByIdAndIsdel(@Param("id")Integer id, @Param("isdel")Integer isdel);

	/**
	 * 根据是否在线和删除状态得到企业id列表
	 * @param validation
	 * @param isdel
	 * @param page
	 * @return
	 */
	@Query("select company.id from Company company where company.validation = :validation and company.isdel = :isdel ")
	Page<Integer> findIdByValidationAndIsdel(@Param("validation")Integer validation, @Param("isdel")Integer isdel, Pageable page);

	@Query("select  CONCAT(company.id,'_',branch.webPrefix) from Company company,Branch branch where company.city.id = branch.defaultCityId and branch.isPublish = 2 and company.validation = :validation and company.isdel = :isdel ")
	Slice<String> findIdAndWebPrefixByValidationAndIsdel(@Param("validation")Integer validation, @Param("isdel")Integer isdel, Pageable page);
}
