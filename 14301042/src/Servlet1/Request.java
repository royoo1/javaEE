package Servlet1;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
public class Request implements ServletRequest {
 
	 private InputStream input;
	 //定义资源，同时定义参数
	 private String uri;
	 private String parameter;
	 public Request(InputStream input) {
	 this.input = input;
	 }
	 public String getUri() {
	   return uri;
	 }
 /**
 * 
 * requestString形式如下：
 * GET /index.html HTTP/1.1
 * Host: localhost:8080
 * Connection: keep-alive
 * Cache-Control: max-age=0
 * ...
 * 该函数目的就是为了获取/index.html字符串
 */
	 private String parseUri(String requestString) {
		 int index1, index2;
		 index1 = requestString.indexOf(' ');
		 if (index1 != -1) {
		 index2 = requestString.indexOf(' ', index1 + 1);
		 if (index2 > index1)
			 return requestString.substring(index1 + 1, index2);
			 }
		 return null;
	 }
 //从InputStream中读取request信息，并从request中获取uri值
	 public void parse() {
	 // Read a set of characters from the socket
		 StringBuffer request = new StringBuffer(2048);
		 int i;
		 byte[] buffer = new byte[2048];
		 try {
		 i = input.read(buffer);
		 } catch (IOException e) {
		 e.printStackTrace();
		 i = -1;
		 }
		 for (int j = 0; j < i; j++) {
		 request.append((char) buffer[j]);
		 }
		 System.out.print(request.toString());
		 uri = parseUri(request.toString());
	 }
 
 /* implementation of the ServletRequest */
	 public Object getAttribute(String attribute) {
	      return null;
	 }
 
	 public Enumeration<String> getAttributeNames() {
	 return null;
	 }
	 
	 public String getRealPath(String path) {
	 return null;
	 }
	 
	 public RequestDispatcher getRequestDispatcher(String path) {
	 return null;
	 }
	 
	 public boolean isSecure() {
	 return false;
	 }
	 
	 public String getCharacterEncoding() {
	 return null;
	 }
	 
	 public int getContentLength() {
	 return 0;
	 }
	 
	 public String getContentType() {
	 return null;
	 }
	 
	 public ServletInputStream getInputStream() throws IOException {
	 return null;
	 }
	 
	 public Locale getLocale() {
	 return null;
	 }
	 
	 public Enumeration<Locale> getLocales() {
	    return null;
	 }
	 
	 public String getParameter(String name) {
	     return null;
	 }
	 
	 public Map<String, String[]> getParameterMap() {
	      return null;
	 }
	 
	 public Enumeration<String> getParameterNames() {
	      return null;
	 }
	 
	 public String[] getParameterValues(String parameter) {
	     return null;
	 }
	 
	 public String getProtocol() {
	     return null;
	 }
	 
	 public BufferedReader getReader() throws IOException {
	     return null;
	 }
	 
	 public String getRemoteAddr() {
	      return null;
	 }
	 
	 public String getRemoteHost() {
	      return null;
	 }
	 
	 public String getScheme() {
	 return null;
	 }
	 
	 public String getServerName() {
	 return null;
	 }
	 
	 public int getServerPort() {
	 return 0;
	 }
	 
	 public void removeAttribute(String attribute) {
	 }
	 
	 public void setAttribute(String key, Object value) {
	 }
	 
	 public void setCharacterEncoding(String encoding)
	 throws UnsupportedEncodingException {
	 }
	
	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public AsyncContext startAsync() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	}