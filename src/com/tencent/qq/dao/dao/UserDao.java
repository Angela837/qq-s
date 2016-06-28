package com.tencent.qq.dao.dao;

import java.util.List;

import com.tencent.qq.vo.User;

public interface UserDao {
	//��¼��֤
	public User isLogin(String account,String password);	
	//ע���û�
	public boolean addUser(User u);
	//�����һ���˺�
    public int getNextAccount();
    //������
    public List<User>queryFriends(String account);
    //�����˺Ų����û�
    public User queryByAccount(String account);
    //���������û�
    public List<User>queryAll();
}
