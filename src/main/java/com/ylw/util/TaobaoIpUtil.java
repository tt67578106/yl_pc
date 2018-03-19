package com.ylw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class TaobaoIpUtil {
	/**
	 * 输入IP获得信息
	 * @param ip
	 * @return
	 */
	public static String ipInfo(String ip){
		String url = "http://ip.taobao.com/service/getIpInfo.php";
		String query = "ip="+ip;
		try {
			URL restURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setAllowUserInteraction(false);
			PrintStream ps = new PrintStream(conn.getOutputStream());
			ps.print(query);
			ps.close();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line, resultStr = "";
			while (null != (line = bReader.readLine()))
			{
				resultStr += line;
			}
			bReader.close();
			return resultStr;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Constants.RETURN_STATUS_FAILURE;
	}
}
