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

	// ����������
	public void startServer() throws IOException {
		System.out.println("��������������");
		
		String sport=PropertiesUtil.readPro("port");
		if(sport!=null)
		{//�����˶˿�
			port=Integer.parseInt(sport);
		}else{//�]�����ö˿�
			port=8888;
		}
		ss=new ServerSocket(port);
	   while(true)
	   {
			Socket s=ss.accept();//�ȴ��ͻ��˵�����
			System.out.println("�ͻ��������ӣ�");
			//	����һ���̣߳�������������ͻ�����������
			new ClientThread(s).start();
	   }

	}

	// �رշ�����
	public void closeServer() {
		try {
			ss.close();
			System.out.println("�������ѹرգ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
