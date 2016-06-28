package com.tencent.qq.util;
//��ȡserver.properties�е������Ϣ

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
public static final String FILENAME="server.properties";
	private static Properties pro=new Properties();
	
	static{
		try {
			pro.load(Thread.currentThread()//��ǰ�߳�
					.getContextClassLoader()//��ļ�����
					.getResourceAsStream(FILENAME));//�����Դ
		} catch (IOException e) {
			System.out.println("��ȡ"+FILENAME+"�쳣��");
			e.printStackTrace();
		}
	}
	
	public static String readPro(String key)
	{
		return pro.getProperty(key);
	}	
}
