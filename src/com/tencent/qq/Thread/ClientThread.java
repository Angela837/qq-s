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
		// 不停的听取客户端的需求
		while (true) {
			try {
				Object o = ObjectUtil.readObject(s);
				if (o instanceof User) {
					User u = (User) o;
					if (u.getAccount() != null) {
						// 登录
						u = ub.isLogin(u);
						
						ObjectUtil.writeObject(s, u);// 返回验证结果给客户端
					} else {
						RegistResult rr = ub.regist(u);// 注册
						ObjectUtil.writeObject(s, rr);// 返回验证结果给客户端

					}
				} else if (o instanceof Find) {
					// 查找
					Find find = (Find) o;
					List<User> ulist = null;
					switch (find.getType()) {
					case Find.ONE:
			            User u=ub.queryByAccount(find.getFaccount());
			              if(u!=null){//查出结果
			            	  ulist=new ArrayList<User>();
				              ulist.add(u);
			              }
			              break;
				     case Find.ALL:
				    	 ulist = ub.queryAll();
					    // ulist.addAll(ub.queryAll());//集合里所有都加入
						 break;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
