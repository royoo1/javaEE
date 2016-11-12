package dev.edu.javaee.spring.resource;

import java.io.IOException;
import java.io.InputStream;
//Resource资源接口
/**
接口：Resource
一个方法：
InputStream getInputStream()throws IOException; 返回输入流InputStream
**/
public interface Resource {
	//定义方法 getInputStream()得到输入流
	InputStream getInputStream()throws IOException;
}
