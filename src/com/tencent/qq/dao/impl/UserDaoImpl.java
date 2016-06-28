package com.tencent.qq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tencent.qq.dao.dao.UserDao;
import com.tencent.qq.util.DBUtil;
import com.tencent.qq.vo.User;

public class UserDaoImpl implements UserDao {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String sql;
	List<User> ulist;
//�رո���
	private void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User isLogin(String account, String password) {
		sql = "select * from QQ_USER where account=? and password=?";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {
			User u=new User();
			u.setAccount(rs.getString("account"));
			u.setAge(rs.getInt("age"));
			u.setEmail(rs.getString("email"));
			u.setImg(rs.getString("img"));
			u.setNickname(rs.getString("nickname"));
			u.setPassword(rs.getString("password"));
			
			return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return null;
	}

	@Override
	public boolean addUser(User u) {
		sql = "insert into QQ_USER values(?,?,?,?,?,?)";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getAccount());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getNickname());
			pst.setInt(4, u.getAge());
			pst.setString(5, u.getEmail());
			pst.setString(6, u.getImg());
			pst.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return false;
	}

	@Override
	public int getNextAccount() {
		// ��ѯ���ݿ����˺ŵ�����java_00001��Ȼ��+1,�ٸ��½����ݿ�,ʹ������
		sql = "select * from account";
		int id = -1;
		try {
			con = DBUtil.getConn();
			con.setAutoCommit(false);// ���ò�Ҫ�����ύ
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				// ��һ
				id = rs.getInt("id") + 1;
				// �������ݿ�
				sql = "update account set id=?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();// ִ�и��²���
			}
			con.commit();// �ֶ��ύ�޸�
		} catch (SQLException e) {
			try {
				con.rollback();// ���쳣 �ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);// ����Ϊ�Զ��ύ
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	@Override
	public List<User> queryFriends(String account) {
		List<User> ulist=new ArrayList<>();
		sql="select*from QQ_user where account in("
				+ "select fid from friends where userid=?)";
		try {
			con=DBUtil.getConn();
			pst=con.prepareStatement(sql);
			pst.setString(1, account);
			rs=pst.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getInt("age"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				ulist.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return ulist;
	}

	@Override
	public User queryByAccount(String account) {
		sql="select * from QQ_user where account=?";
		try {
			con=DBUtil.getConn();
			pst=con.prepareStatement(sql);
			pst.setString(1, account);
			rs=pst.executeQuery();
			if(rs.next()){
				User u=new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getInt("age"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
//
	@Override
	public List<User> queryAll() {
		List<User> ulist=new ArrayList<>();
		sql="select * from QQ_user";
		try {
			con=DBUtil.getConn();
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getInt("age"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				ulist.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return ulist;
	}

}
