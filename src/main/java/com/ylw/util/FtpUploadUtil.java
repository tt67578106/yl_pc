package com.ylw.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FtpUploadUtil {
	private static Logger logger = LoggerFactory.getLogger(FtpUploadUtil.class);
	/**
	 * 上传到阿里云
	 * @param file
	 * @return
	 * @throws IOException
	 */
//	public static String upload(MultipartFile file,String alyPath) throws IOException{
//		InputStream content = file.getInputStream();
//		ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentLength(file.getSize());
//		String path =alyPath +DateConvertUtils.getNow().getTime()+getTypeStr(file.getOriginalFilename());
//		client.putObject(Constants.ALIYUN_BUCKETNAME, path , content, meta);
//		return Constants.ALIYUN_ROOT_PATH+path;
//	}
	/**
	 * 上传文件服务器
	 * @param savePath
	 * @param fileData
	 * @return
	 */
	public static String upload(String savePath,MultipartFile fileData){
		String fileName = RandomSeriNoUtils.genCodeByTime("") + "." + ImageUtil.getExtention(fileData.getOriginalFilename());
		try {
			if(!fileData.isEmpty()){
				uploadFile(Constants.FTP_URL, Constants.FTP_PORT, Constants.FTP_USERNAME, Constants.FTP_PASSWORD, "o/"+savePath, fileName, fileData.getInputStream());
				ImageUtil.createThumbByWidthnailAndWatermark(savePath,Constants.IMAGE_FILE_URL + "o/"+savePath + fileName);
				return savePath + fileName;//返回路径
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Constants.RETURN_STATUS_FAILURE;
	}
	/**
	 * 获得通过文件名文件类型，如abc。tsx 返回 。tsx
	 * @param filename
	 * @return
	 */
	public static String getTypeStr(String filename){
		int num = filename.lastIndexOf(".");
		if(num==-1){
			return "";
		}
		else{
			return filename.substring(num);
		}
	}
	/**
	 * ftp上传
	 * @param url ftp服务器hostname
	 * @param port ftp服务器端口
	 * @param username ftp服务器帐号
	 * @param password ftp服务器密码
	 * @param path ftp服务器保存目录
	 * @param filename 保存到ftp服务器文件名
	 * @param input 输入流
	 * @return 成功返回true,否则返回false
	 */
	public static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.setDefaultPort(port);
			ftp.setControlEncoding("UTF-8");
			ftp.connect(url, port);// 连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			isDirExist(ftp, path);
			ftp.changeWorkingDirectory(path);
			ftp.enterLocalPassiveMode();
			boolean ts = ftp.storeFile(filename, input);
			if(ts){
				input.close();
				ftp.logout();
			}
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return success;
	}
	
	/**
	 * 
	 * <p>删除ftp上的文件</p>
	 * @param srcFname
	 * @return true || false
	 */
	public static boolean removeFile(String srcFname){
		boolean flag = false;
		try {
			FTPClient ftp = new FTPClient();
			ftp.setDefaultPort(Constants.FTP_PORT);
			ftp.connect(Constants.FTP_URL, Constants.FTP_PORT);
			ftp.login(Constants.FTP_USERNAME, Constants.FTP_PASSWORD);// 登录
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if( ftp!=null ){
				try {
					flag = ftp.deleteFile(srcFname);
				} catch (IOException e) {
					ftp.logout();  
					ftp.disconnect();  
					e.printStackTrace();
				}
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 连接FTP服务器
		return flag;
	}
	
	/** 判断Ftp目录是否存在,如果不存在则创建目录 */
	public static void isDirExist(FTPClient ftpClient, String dir) {
		try {
			ftpClient.makeDirectory(dir); // 想不到什么好办法来判断目录是否存在，只能用异常了(比较笨).请知道的告诉我一声`
		} catch (IOException e1) {
			logger.info("穿件ftp目录异常"+dir+"目录已存在！");
		}
	}
}
