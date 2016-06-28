package com.tencent.qq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//����������ݿ�����
public class DBUtil {
private static String driverClass;
private static String user;
private static String password;
private static String url;

static{
	driverClass=PropertiesUtil.readPro("driverClass");
	user=PropertiesUtil.readPro("user");
	password=PropertiesUtil.readPro("password");
	url=PropertiesUtil.readPro("url");
	try {
		Class.forName(driverClass);//���������
	} catch (ClassNotFoundException e) {
		System.out.println("���ݿ����ʧ�ܣ�"+driverClass);
		e.printStackTrace();
	}

}
public static Connection getConn() throws SQLException{
	return DriverManager.getConnection(url,user,password);
}
public static void main(String[] args) {
	try {
		System.out.println(DBUtil.getConn());
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

	

}
