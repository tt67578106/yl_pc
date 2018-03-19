package com.ylw.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ylw.entity.base.Branch;
import com.ylw.entity.base.BranchCity;
import com.ylw.entity.code.CodeAreaCity;
import com.ylw.repository.BranchCityDao;
import com.ylw.repository.BranchDao;
import com.ylw.repository.CodeAreaCityDao;
import com.ylw.util.Constants;
/**
 * 分站业务
 * @author Nicolas
 *
 */
@Component
@Transactional
public class BranchService {
	private Logger logger = LoggerFactory.getLogger(BranchService.class);
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private BranchDao branchDao;
	
	private CodeAreaCityDao codeAreaCityDao;
	
	private BranchCityDao branchCityDao;
	/**
	* 需要从session中查询时的条件
	*/
	private Pattern urlPattern = Pattern.compile("^"+Constants.DOMAIN_NAME+"|^www."+Constants.DOMAIN_NAME);
	
	/**
	 * 查询是否存在
	 * @param id
	 * @return
	 */
	public Integer countById(Integer id){
		return branchDao.countById(id);
	}
	/**
	 * 查询所有发布状态的分站
	 * @return
	 */
	public List<Branch> findAllBranch(){
		return branchDao.findByIsPublishOrderByWebPrefixAsc(2);
	}
	
	public List<Branch> findBranchPage(Integer pageNumber,Integer pageSize){
		return branchDao.findByIsPublishOrderByWebPrefixAsc(2);
	}
	
	/**
	 * 得到分站所有的前缀（做sitemap用）
	 * @return
	 */
	public List<String> findAllBranchWebPrefix(){
		return branchDao.findAllBranchWebPrefix();
	}
	/**
	 * 根据本地城市id查询出分站列表
	 * @param cityId
	 * @return
	 */
	public List<Branch> findByCityId(Integer cityId){
		return branchDao.findByCityId(cityId);
	}
	
	/**
	 * 根据ip得到分站
	 * @param ip
	 * @return
	 */
	public Branch getBranchByIp(String ip){
		List<Branch> branchs = findBranchByRequest(ip);
		if(branchs!=null && branchs.size() >= 1){//`过ip找到了对应分站
			return  branchs.get(0);
		}else{//找不到，则默认上海站
			return  getById(1);
		}
	}
	
	/**
	 * 查询分站列表
	 * @param request
	 * @return
	 */
	public List<Branch> findBranchByRequest(String ip){
		Integer cityId = getLocalCity(ip);
		if(cityId!=null){
			List<Branch> branchList = findByCityId(cityId);
			if(branchList!=null&&branchList.size() == 1){
				return branchList;
			}else{
				for (Branch branch : branchList) {
					if(cityId!=null && cityId.equals(branch.getDefaultCityId())){
						branchList.clear();
						branchList.add(branch);
						return branchList;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据城市得到分站
	 * @param cityId
	 * @return
	 */
	public Branch getBranchByCityId(Integer cityId,HttpServletRequest request,String ip){
		if(cityId!=null){
			List<Branch> branchList = findByCityId(cityId);
			if(branchList!=null&&branchList.size() == 1){
				return branchList.get(0);
			}else{
				for (Branch branch : branchList) {
					if(cityId!=null && cityId.equals(branch.getDefaultCityId())){
						request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,branch.getId());
						request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,branch.getBranchName());
						request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_WEB_PREFIX,branch.getWebPrefix());
						return branch;
					}
				}
			}
		}
		return getBranchByRequest(request,ip);
	}
	
	public List<BranchCity> findBranchCityList(Integer branchId,Integer cityId){
		return branchCityDao.findByBranchIdAndCityIdNotOrderBySortingAsc(branchId, cityId);
	}
	/**
	 * 根据所选分站查询城市列表
	 * @param branchId
	 * @return
	 */
	public List<Integer> findBranchCityIdList(Integer branchId){
		List<Integer> cityList = null;
		Branch branch = branchDao.findOne(branchId);
		if(branch != null && branch.getBranchCityList()!=null){
			List<BranchCity> bcityList = branch.getBranchCityList();
			cityList = new ArrayList<Integer>();
			for (BranchCity branchCity : bcityList) {
				cityList.add(branchCity.getCityId());
			}
		}
		return cityList;
	}
	/**
	 * 通过二级域名访问
	 */
	public Branch getByWebPrefix(HttpServletRequest request){
		int webPrefixIndex = request.getServerName().indexOf(Constants.DOMAIN_NAME);
		if(webPrefixIndex != -1){
			String webPrefix = request.getServerName().substring(0, webPrefixIndex).replace(".", "");
			List<Branch>  branchs = branchDao.findByWebPrefix(webPrefix);
			if(branchs != null && branchs.size() > 0){
				return branchs.get(0);
			}
		}
		return null;
	}
	/**
	 * getById
	 * @param branchId
	 * @return
	 */
	public Branch getById(Integer branchId) {
		return branchDao.findOne(branchId);
	}
	/**
	 * 从request中获取分站Id
	 * @param request
	 * @return
	 */
	public Integer getBranchIdByRequest(HttpServletRequest request,String ip){
		return getBranchByRequest(request, ip).getId();
	}
	/**
	 * 通过分站前缀、缓存获取分站信息
	 * @param request
	 * @param ip
	 * @return
	 */
	public Branch getBranchByRequest(HttpServletRequest request,String ip){
		Branch branch = null;
		if(urlPattern.matcher(request.getServerName()).find()){//使用www或不带二级域名，从session中直接取
			Integer branchId = (Integer) request.getSession().getAttribute(Constants.SESSION_KEY_BRANCH_ID);
			if(branchId != null){
				branch = branchDao.findOne(branchId);
			}else{//通过ip查询，并存到session
				List<Branch> branchs = findBranchByRequest(ip);
				if(branchs!=null && branchs.size() >= 1){//`过ip找到了对应分站
					branch =  branchs.get(0);
				}else{//找不到，则默认上海站
					branch =  getById(1);
				}
				logger.info("BranchLog1: BranchId:" + branch.getId() +", BranchName:"+branch.getBranchName());
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,branch.getId());
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,branch.getBranchName());
				request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_WEB_PREFIX,branch.getWebPrefix());
			}
		}else{//通过二级域名访问
			int webPrefixIndex = request.getServerName().indexOf(Constants.DOMAIN_NAME);
			if(webPrefixIndex != -1){
				String webPrefix = request.getServerName().substring(0, webPrefixIndex).replace(".", "");
				List<Branch>  branchs = branchDao.findByWebPrefix(webPrefix);
				if(branchs != null && branchs.size() > 0){
					branch =  branchs.get(0);
				}else{//找不到，则默认上海站
					branch =  getById(1);
				}
			}else{
				//找不到，则默认上海站
				branch =  getById(1);
			}
		}
		logger.info("BranchLog2: BranchId:" + branch.getId() +", BranchName:"+branch.getBranchName());
		request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_ID,branch.getId());
		request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_NAME,branch.getBranchName());
		request.getSession().setAttribute(Constants.SESSION_KEY_BRANCH_WEB_PREFIX,branch.getWebPrefix());
		logger.info("BranchLog3: BranchId:" + branch.getId() +", BranchName:"+branch.getBranchName());
		return branch;
	}
	/**
	 * 通过请求获得本地的城市Id
	 * @param request
	 * @return
	 */
	private Integer getLocalCity(String ip){
		Integer cityId = null;
		String cityCode = null;
		String url = Constants.BAIDU_MAP_API_IP + "&ip="+ip;
		try {
			String result = null;
			try{
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("url", url);
				result = restTemplate.postForObject(Constants.SEND_YOULANW_API_URL+"baiduMap",map,String.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(StringUtils.isEmpty(result)){
				return null;
			}
			int start = result.indexOf("\"message\":\"");
			result = result.substring(start + 12, result.length()-2);
			Pattern p = Pattern.compile("failed");
			if(p.matcher(result).find()){
				logger.info("定位失败：\t" + result);
			}else if(result.indexOf("city_code")!=-1){
				cityCode = result.substring(result.indexOf("city_code")+11, result.indexOf("},\"address"));
			}else{
				logger.info("定位失败：\t" + result);
			}
			if(StringUtils.isNumeric(cityCode)){
				List<CodeAreaCity> codeAreaCity = codeAreaCityDao.findByBaiduCityId(Integer.parseInt(cityCode));
				cityId = (codeAreaCity!=null&&codeAreaCity.size()>0)?codeAreaCity.get(0).getId():null;
			}
		} catch (RestClientException e) {
			logger.error("百度接口调用失败"+e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("百度接口获取cityCode转换失败:cityCode:"+cityCode);
		}
		return cityId;
	}

	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	@Autowired
	public void setCodeAreaCityDao(CodeAreaCityDao codeAreaCityDao) {
		this.codeAreaCityDao = codeAreaCityDao;
	}
	@Autowired
	public void setBranchCityDao(BranchCityDao branchCityDao) {
		this.branchCityDao = branchCityDao;
	}
}
