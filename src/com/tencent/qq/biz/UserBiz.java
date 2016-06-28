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
		int i=udao.getNextAccount();//�����һ���˺���
		String account=String.format("%06d%n", i).trim();
		//��Ϣ�������ݿ�
		u.setAccount(account);
		boolean b=udao.addUser(u);
		if(b){
			rr.setMsg("���ĵ�¼�˺�Ϊ��"+account);
			rr.setRr(true);
		}else{
			rr.setMsg("ע��ʧ�ܣ�");
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
