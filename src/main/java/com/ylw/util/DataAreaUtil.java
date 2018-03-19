package com.ylw.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;

import com.ylw.entity.code.CodeAreaCity;
import com.ylw.entity.code.CodeAreaDistrict;
import com.ylw.entity.code.CodeAreaProvince;
import com.ylw.entity.job.Labels;



@SuppressWarnings("unchecked")
public class DataAreaUtil {

	private static List<CodeAreaCity> citys;
	private static List<CodeAreaProvince> provinces;
	private static List<CodeAreaDistrict> districts;
	private static List<Labels> hotlabels;
	static{
		Cache cache = StoreCacheUtil.getCacheManager().getCache("staticCache");
		citys = (List<CodeAreaCity>)cache.get(Constants.CACHE_KEY_CITY).getObjectValue();
		provinces = (List<CodeAreaProvince>)cache.get(Constants.CACHE_KEY_PROVINCE).getObjectValue();
		districts = (List<CodeAreaDistrict>)cache.get(Constants.CACHE_KEY_DISTRICT).getObjectValue();
		hotlabels = (List<Labels>)cache.get(Constants.CACHE_KEY_HOME_HOT_LABELS).getObjectValue();
	}
	
	public static String getLabelsNameById(Integer labelId){
		for(Labels lab:hotlabels){
			if(lab.getId().equals(labelId)){
				return lab.getLabelName();
			}
		}
		return "";
	}
	/**
	 * 根据id得到市名称
	 * @param cityid
	 * @return
	 */
	public static String getCityNameById(Long cityid){
		for(CodeAreaCity city:citys){
			if(city.getId().equals(cityid)){
				return city.getCityName();
			}
		}
		return "";
	}
	/**
	 * 根据id得到市简称
	 * @param cityid
	 * @return
	 */
	public static String getCityAbbreviationById(Integer cityid){
		for(CodeAreaCity city:citys){
			if(city.getId().equals(cityid)){
				return city.getAbbreviation();
			}
		}
		return "";
	}
	/**
	 * 根据id得到省名称
	 * @param provinceid
	 * @return
	 */
	public static String getProvinceNameById(Integer provinceid){
		for(CodeAreaProvince province:provinces){
			if(province.getId().equals(provinceid)){
				return province.getProvinceName();
			}
		}
		return "";
	}
	/**
	 * 根据id得到省简称
	 * @param provinceId
	 * @return
	 */
	public static String getProvinceAbbreviationById(Integer provinceId){
		for(CodeAreaProvince province:provinces){
			if(province.getId().equals(provinceId)){
				return province.getAbbreviation();
			}
		}
		return "";
	}
	/**
	 * 根据id得到区名称
	 * @param districtid
	 * @return
	 */
	public static String getDistrictById(Integer districtid){
		for(CodeAreaDistrict district:districts){
			if(district.getId().equals(districtid)){
				return district.getDistrictName();
			}
		}
		return "";
	}
	/**
	 * 根据id得到区简称
	 * @param districtid
	 * @return
	 */
	public static String getDistrictAbbreviationById(Integer districtid){
		for(CodeAreaDistrict district:districts){
			if(district.getId().equals(districtid)){
				return district.getAbbreviation();
			}
		}
		return "";
	}
	public static List<CodeAreaProvince> getProvinces(){
		return provinces;
	}
	/**
	 * 通过省份的id来获得相应的城市
	 * @param provinceid
	 * @return
	 */
	public static List<CodeAreaCity> getCitysByProvinceId(Long provinceid){
		List<CodeAreaCity> rcity = new ArrayList<CodeAreaCity>();
		for(CodeAreaCity city:citys){
			if(city.getProvinceId().equals(provinceid)){
				rcity.add(city);
			}
		}
		return rcity;
	}

}
