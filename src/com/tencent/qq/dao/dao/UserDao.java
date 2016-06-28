package com.tencent.qq.dao.dao;

import java.util.List;

import com.tencent.qq.vo.User;

public interface UserDao {
	//登录验证
	public User isLogin(String account,String password);	
	//注册用户
	public boolean addUser(User u);
	//获得下一个账号
    public int getNextAccount();
    //好友数
    public List<User>queryFriends(String account);
    //根据账号查找用户
    public User queryByAccount(String account);
    //查找所有用户
    public List<User>queryAll();
}
