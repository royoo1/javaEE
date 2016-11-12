package dev.edu.javaee.spring.resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
//本地文件资源实现了资源
/**
类名：LocalFileResource 实现了 Resource 接口
一个属性：
private final String fileName 私有的固定的String fileName文件名
两个方法：
1.LocalFileResource(String name) 构造方法 初始化文件路径
2.实现了接口方法 InputStream getInputStream() 新建 new FileInputStream(fileName) 并返回输入流

作用：存储配置文件的路径 并且调用getInputStream()时返回 FileInputStream(fileName) 文件输入流
**/
public class LocalFileResource implements Resource{
	//私有变量不变的 String fileName文件名
	private final String fileName;

    public LocalFileResource(String name) {
		//把参数赋值到私有变量
        this.fileName = name;
    }

	//重写
    @Override
    public InputStream getInputStream() throws IOException{
		//返回输入流 新建的输入流 文件名是参数
    	return new FileInputStream(fileName);
    }
}
