package com.ylw.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.ehcache.Cache;

import com.ylw.entity.base.DataDictionary;

@SuppressWarnings("unchecked")
public class DataDictionaryUtil {
	private static List<DataDictionary> dics;
	static{
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		dics = (List<DataDictionary>)cache.get(Constants.CACHE_KEY_DIC_SALARY).getObjectValue();
	}
	/**
	 * 获得教育信息
	 * @return
	 */
	public static List<DataDictionary> getEducations(){
		return getDatas(18);
	}
	/**
	 * 获得薪资信息
	 * @return
	 */
	public static List<DataDictionary> getSalarys(){
		return getDatas(19);
	}
	/**
	 * 获得职位信息
	 * @return
	 */
	public static List<DataDictionary> getPosts(){
		return getDatas(20);
	}
	/**
	 * 获取民族信息
	 * @return
	 */
	public static List<DataDictionary> getNations(){
		List<DataDictionary> list = getDatas(22);
		Collections.reverse(list);
		return list;
	}
	/**
	 * 获得行业信息
	 * @return
	 */
	public static List<DataDictionary> getIndustrys(){
		return getDatas(14);
	}
	/**
	 * 获取教育信息
	 * @param eduid
	 * @return
	 */
	public static String getEdu(Long eduid){
		List<DataDictionary> list = getDatas(18);
		for(DataDictionary edu:list){
			if(edu.getCode().equals(eduid.toString())){
				return edu.getName();
			}
		}
		return null;
	}
	/**
	 * 获取职位信息
	 * @param postid
	 * @return
	 */
	public static String getPost(Long postid){
		List<DataDictionary> list = getDatas(20);
		for(DataDictionary post:list){
			if(post.getCode().equals(postid.toString())){
				return post.getName();
			}
		}
		return "";
	}
	/**
	 * 获取行业信息
	 * @param industryid
	 * @return
	 */
	public static String getIndustry(Long industryid){
		List<DataDictionary> list = getDatas(14);
		for(DataDictionary ind:list){
			if(ind.getCode().equals(industryid.toString())){
				return ind.getName();
			}
		}
		return "";
	}
	/**
	 * 获取民族信息
	 * @param nationid
	 * @return
	 */
	public static String getNation(Long nationid){
		List<DataDictionary> list = getDatas(22);
		for(DataDictionary nat:list){
			if(nat.getCode().equals(nationid.toString())){
				return nat.getName();
			}
		}
		return "";
	}
	/**
	 * 从数据字典中获取人员信息
	 * @param staffscaleid
	 * @return
	 */
	public static String getStaffscale(Long staffscaleid){
		List<DataDictionary> list = getDatas(9);
		for(DataDictionary nat:list){
			if(nat.getCode().equals(staffscaleid.toString())){
				return nat.getName();
			}
		}
		return "";
	}
	/**
	 * 
	 * @param typeid
	 * @return
	 */
	public static List<DataDictionary> getDatas(Integer typeid){
		List<DataDictionary> datas = new ArrayList<DataDictionary>();
		for(DataDictionary dic:dics){
			if(dic.getTypeid()==typeid){
				datas.add(dic);
			}
		}
		return datas;
	}
}
