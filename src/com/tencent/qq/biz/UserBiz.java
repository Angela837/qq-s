package com.tencent.qq.biz;

import java.util.List;

import com.tencent.qq.dao.dao.UserDao;
import com.tencent.qq.dao.impl.UserDaoImpl;
import com.tencent.qq.vo.RegistResult;
import com.tencent.qq.vo.User;

public class UserBiz {
	private UserDao udao;

	public UserBiz() {

		udao = new UserDaoImpl();
	}

	public User isLogin(User u) {
		u = udao.isLogin(u.getAccount(), u.getPassword());
		if(u!=null){
			u.setFriends(udao.queryFriends(u.getAccount()));;
		}
		return u;
	}
	public RegistResult regist(User u){
		RegistResult rr=new RegistResult();
		int i=udao.getNextAccount();//获得下一个账号数
		String account=String.format("%06d%n", i).trim();
		//信息加入数据库
		u.setAccount(account);
		boolean b=udao.addUser(u);
		if(b){
			rr.setMsg("您的登录账号为："+account);
			rr.setRr(true);
		}else{
			rr.setMsg("注册失败！");
			rr.setRr(false);
		}
		return rr;
		
	}
	public User queryByAccount(String account){
		return udao.queryByAccount(account);
		
	}
	public List<User> queryAll(){
		return udao.queryAll();
	}
	
}
