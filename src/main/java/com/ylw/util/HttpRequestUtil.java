
package com.ylw.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.*;

/**
 * HttpRequestParser工具类
 * @author don
 * @date Oct 25, 2017/10/25
 */
public class HttpRequestUtil {
    /**
     * 分析url字符串,采用utf-8解码
     * @param urlString
     * @return
     */
    public static Request parse(String urlString) {
        return parse(urlString, "utf-8");
    }

    /**
     * 分析url字符串,指定字符集进行解码
     * @param urlString
     * @param enc
     * @return
     */
    public static Request parse(String urlString, String enc) {
        if (urlString == null || urlString.length() == 0) {
            return new Request();
        }
        int questIndex = urlString.indexOf('?');
        if (questIndex == -1) {
            return new Request(urlString);
        }
        String url = urlString.substring(0, questIndex);
        String queryString = urlString.substring(questIndex + 1, urlString.length());
        return new Request(url, getParamsMap(queryString, enc));
    }

    private static Map<String, String[]> getParamsMap(String queryString, String enc) {
        Map<String, String[]> paramsMap = new HashMap<String, String[]>();
        if (queryString != null && queryString.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0;
            String subStr, param, value;
            String[] paramPair, values, newValues;
            do {
                ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = queryString.substring(lastAmpersandIndex);
                }
                paramPair = subStr.split("=");
                param = paramPair[0];
                value = paramPair.length == 1 ? "" : paramPair[1];
                try {
                    value = URLDecoder.decode(value, enc);
                } catch (UnsupportedEncodingException ignored) {
                }
                if (paramsMap.containsKey(param)) {
                    values = paramsMap.get(param);
                    int len = values.length;
                    newValues = new String[len + 1];
                    System.arraycopy(values, 0, newValues, 0, len);
                    newValues[len] = value;
                } else {
                    newValues = new String[] { value };
                }
                paramsMap.put(param, newValues);
            } while (ampersandIndex > 0);
        }
        return paramsMap;
    }

    /**
     * 请求对象
     * @author don
     * @date Oct 25, 2017/10/25
     */
    public static class Request implements HttpServletRequest {
        private String requestURI;
        private Map<String, String[]> parameterMap;

        Request() {
            this("");
        }

        Request(String requestURI) {
            this.requestURI = requestURI;
            parameterMap = new HashMap<String, String[]>();
        }

        Request(String requestURI, Map<String, String[]> parameterMap) {
            this.requestURI = requestURI;
            this.parameterMap = parameterMap;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }



        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        /**
         * 获得指定名称的参数
         * @param name
         * @return
         */
        public String getParameter(String name) {
            String[] values = parameterMap.get(name);
            if (values != null && values.length > 0) {
                return values[0];
            }
            return null;
        }

        /**
         * 获得所有的参数名称
         * @return
         */
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(parameterMap.keySet());
        }

        /**
         * 获得指定名称的参数值(多个)
         * @param name
         * @return
         */
        public String[] getParameterValues(String name) {
            return parameterMap.get(name);
        }

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        /**
         * 获得请求的url地址
         * @return
         */
        public String getRequestURI() {
            return requestURI;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }


        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }


        /**
         * 获得 参数-值Map
         * @return
         */
        public Map<String, String[]> getParameterMap() {
            return parameterMap;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }


        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append("{");
            buf.append("\n  url = ").append(this.requestURI);
            buf.append("\n  paramsMap = {");
            if (this.parameterMap.size() > 0) {
                for (Map.Entry<String, String[]> e : this.parameterMap.entrySet()) {
                    buf.append(e.getKey()).append("=").append(Arrays.toString(e.getValue())).append(",");
                }
                buf.deleteCharAt(buf.length() - 1);
            }
            buf.append("}\n}");
            return buf.toString();
        }
    }
}