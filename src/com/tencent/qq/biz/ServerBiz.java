package com.tencent.qq.biz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.tencent.qq.Thread.ClientThread;
import com.tencent.qq.util.ObjectUtil;
import com.tencent.qq.util.PropertiesUtil;
import com.tencent.qq.vo.User;

public class ServerBiz {
	ServerSocket ss = null;
	 int port;

	// 启动服务器
	public void startServer() throws IOException {
		System.out.println("服务器已启动！");
		
		String sport=PropertiesUtil.readPro("port");
		if(sport!=null)
		{//配置了端口
			port=Integer.parseInt(sport);
		}else{//沒有配置端口
			port=8888;
		}
		ss=new ServerSocket(port);
	   while(true)
	   {
			Socket s=ss.accept();//等待客户端的连接
			System.out.println("客户端已连接！");
			//	启动一个线程，独立处理这个客户的所有事情
			new ClientThread(s).start();
	   }

	}

	// 关闭服务器
	public void closeServer() {
		try {
			ss.close();
			System.out.println("服务器已关闭！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
