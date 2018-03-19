package com.ylw.repository;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ylw.entity.base.DataDictionary;

public interface DataDictionaryDao extends PagingAndSortingRepository<DataDictionary,java.lang.Integer>, JpaSpecificationExecutor<DataDictionary>{

	List<DataDictionary> findByStatus(int status);
	
	List<DataDictionary> findByStatusAndTypeid(int status,int typeid);

	/**
	 * 得到所有企业行业的code（sitemap使用）
	 * @return
	 */
	@Query("select code from DataDictionary where typeid = 14 and status = 1 order by code asc ")
	List<Integer> findCompanyIndustryids();
	
	@Query("select code from DataDictionary where name =:name and typeid = 18 and status = 1")
	String findCodeByNameAndTypeid(@Param("name")String name);
	/**
	 * 通过code查询民族name
	 * @param nation
	 * @return
	 */
	@Query("select name from DataDictionary where code =:nation and typeid = 22 and status = 1")
	String findNationByNameAndTypeid(@Param("nation")String nation);
	/**
	 * 通过code查询学历name
	 * @param valueOf
	 * @return
	 */
	@Query("select name from DataDictionary where code =:education and typeid = 18 and status = 1")
	String findEducationByNameAndTypeid(@Param("education")String education);
	/**
	 *通过code查询 职位name
	 * @param valueOf
	 * @return
	 */
	@Query("select name from DataDictionary where code =:intentionPosition and typeid = 20 and status = 1")
	String findIntentionPositionByNameAndTypeid(@Param("intentionPosition")String intentionPosition);
	/**
	 * 通过name查询 职位code
	 * @param nation
	 * @return
	 */
	@Query("select code from DataDictionary where name =:nation and typeid = 22 and status = 1")
	String findNationCodeByNameAndTypeid(@Param("nation")String nation);
	/**
	 * 查询职位code
	 * @param selfIntro
	 * @return
	 */
	@Query("select code from DataDictionary where name =:selfIntro and typeid = 20 and status = 1")
	String findSelfIntroByNameAndTypeid(@Param("selfIntro")String selfIntro);
	/**
	 * 通过薪资code查询薪资name
	 * @param bottledParameter
	 * @return
	 */
	@Query("select name from DataDictionary where code =:bottledParameter and typeid = 19 and status = 1")
	String findSalaryNameBycode(@Param("bottledParameter")String bottledParameter);
}
