package com.tencent.qq.Thread;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.tencent.qq.biz.ServerBiz;
import com.tencent.qq.biz.UserBiz;
import com.tencent.qq.util.ObjectUtil;
import com.tencent.qq.vo.Find;
import com.tencent.qq.vo.RegistResult;
import com.tencent.qq.vo.User;

public class ClientThread extends Thread {
	private Socket s;
	private UserBiz ub;

	public ClientThread(Socket s) {
		this.s = s;
		ub = new UserBiz();
	}

	public void run() {
		// ��ͣ����ȡ�ͻ��˵�����
		while (true) {
			try {
				Object o = ObjectUtil.readObject(s);
				if (o instanceof User) {
					User u = (User) o;
					if (u.getAccount() != null) {
						// ��¼
						u = ub.isLogin(u);
						
						ObjectUtil.writeObject(s, u);// ������֤������ͻ���
					} else {
						RegistResult rr = ub.regist(u);// ע��
						ObjectUtil.writeObject(s, rr);// ������֤������ͻ���

					}
				} else if (o instanceof Find) {
					// ����
					Find find = (Find) o;
					List<User> ulist = null;
					switch (find.getType()) {
					case Find.ONE:
			            User u=ub.queryByAccount(find.getFaccount());
			              if(u!=null){//������
			            	  ulist=new ArrayList<User>();
				              ulist.add(u);
			              }
			              break;
				     case Find.ALL:
				    	 ulist = ub.queryAll();
					    // ulist.addAll(ub.queryAll());//���������ж�����
						 break;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
