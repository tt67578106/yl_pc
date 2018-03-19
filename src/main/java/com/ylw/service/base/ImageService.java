package com.ylw.service.base;

import java.io.FileInputStream;
import java.io.InputStream;
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

import com.ylw.entity.base.CompanyScene;
import com.ylw.entity.base.Image;
import com.ylw.repository.CompanyDao;
import com.ylw.repository.CompanySceneDao;
import com.ylw.repository.ImageDao;
import com.ylw.util.Constants;
import com.ylw.util.DateConvertUtils;
import com.ylw.util.FtpUploadUtil;
import com.ylw.util.ImageUtil;
import com.ylw.util.RandomSeriNoUtils;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ImageService {

	private ImageDao imageDao;
	private CompanyDao companyDao;
	private CompanySceneDao companyscSceneDao;

	public Image getImage(java.lang.Integer id){
		return imageDao.findOne(id);
	}

	public void save(Image entity){
		imageDao.save(entity);
	}

	public void delete(java.lang.Integer id){
		imageDao.delete(id);
	}
	
	public Image uploadhead(MultipartFile file) {
		String imgpath = null;
		try{
			imgpath = FtpUploadUtil.upload(Constants.ALIYUN_RESUME_HEAD_SAVE_PATH,file);
			Image img = new Image();
			img.setCreatetime(DateConvertUtils.getNow());
			img.setImgpath(imgpath);
			img.setExtname(FtpUploadUtil.getTypeStr(imgpath));
			img.setComment("头像图片");
			imageDao.save(img);
			return imageDao.save(img);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将上传文件json字符串
	 * @param file
	 * @return
	 */
	public Integer uploadhead(String file) {
		String fileName = RandomSeriNoUtils.genCodeByTime("") +".jpg";
		String imgpath = Constants.ALIYUN_RESUME_HEAD_SAVE_PATH+fileName;
		String targetPath = "/tmp/files/"+fileName;
		try {
			ImageUtil.base64ToFile(file, targetPath);
			InputStream is = new FileInputStream(targetPath); 
			FtpUploadUtil.uploadFile(Constants.FTP_URL, Constants.FTP_PORT, Constants.FTP_USERNAME, Constants.FTP_PASSWORD, "o/"+Constants.ALIYUN_RESUME_HEAD_SAVE_PATH, fileName , is);
			Image img = new Image();
			img.setCreatetime(DateConvertUtils.getNow());
			img.setImgpath(imgpath);
			img.setExtname(FtpUploadUtil.getTypeStr(imgpath));
			img.setComment("头像图片");
			imageDao.save(img);
			return img.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		//imgpath = upload(Constants.ALIYUN_RESUME_HEAD_SAVE_PATH,file);//AliyunUploadUtil.upload(file);
	}
	/**
	 * 企业相册上传
	 * @param file
	 * @param comment
	 * @param alyPath
	 * @param category
	 * @param companyid
	 * @return
	 */
	public Image uploadImage(MultipartFile file,String comment,String alyPath,Integer category,Integer companyid) {
		String imgpath = null;
		try{
			imgpath = FtpUploadUtil.upload(alyPath,file);
			Image img = new Image();
			img.setCreatetime(DateConvertUtils.getNow());
			img.setImgpath(imgpath);
			img.setExtname(FtpUploadUtil.getTypeStr(imgpath));
			img.setComment(comment);
			imageDao.save(img);
			if(category != 0 && companyid != 0){
				img.setType(category);
				//保存相册信息
				CompanyScene cs = new CompanyScene();
				cs.setCategory(category);
				cs.setComment(comment);
				cs.setTitle(comment);
				cs.setComment("暂无描述");
				cs.setCompany(companyDao.findOne(companyid));
				cs.setImage(img);
				cs.setType(category);
				cs.setCreatetime(DateConvertUtils.getNow());
				cs.setIsshow(1);
				companyscSceneDao.save(cs);
			}
			return imageDao.save(img);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改相册描述
	 * @param id
	 * @param comment
	 */
	public void updateComment(Integer id, String comment) {
		CompanyScene companyScene = companyscSceneDao.findOne(id);
		Image image = companyScene.getImage();
		if(image != null){
			image.setComment(comment);
			imageDao.save(image);
		}
		companyScene.setImage(image);
		companyscSceneDao.save(companyScene);
	}
	public Page<Image> getUserPage(java.lang.Integer userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Image> spec = buildSpecification(userId.longValue(), searchParams);
		return imageDao.findAll(spec, pageRequest);
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
	private Specification<Image> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Image> spec = DynamicSpecifications.bySearchFilter(filters.values(), Image.class);
		return spec;
	}

	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	@Autowired
	public void setCompanyscSceneDao(CompanySceneDao companyscSceneDao) {
		this.companyscSceneDao = companyscSceneDao;
	}

	

}