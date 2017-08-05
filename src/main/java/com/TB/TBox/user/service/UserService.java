package com.TB.TBox.user.service;



import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.User;
import com.TB.TBox.user.mapper.UserMapper;
import com.TB.base.mybatisUtils.SessionFactory;



@Service
public class UserService implements UserMapper{
	Logger log = Logger.getLogger(UserService.class);
		SessionFactory sessionFactory = new SessionFactory();
		

		/**
		 * 注册账号
		 */
	public void addUser(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:" + user.getUid());
		} finally {
			session.close();
		}
		
	}


	/**
	 * 创建角色
	 */
	public void createRole(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.createRole(user);
			session.commit();
		} finally {
		session.close();
		}
		
	}
	
	
	
	
	/**
	 * 按id查询
	 */
	public User selectUserByID(int uid) {
		SqlSession session =sessionFactory.getSession();
			UserMapper userOperation = session.getMapper(UserMapper.class);
			User user = userOperation.selectUserByID(uid);
		return user;
	}
	
	/**
	 * 修改信息（修改密码，修改二级密码，修改角色信息）
	 */
	public void updateRole(User user) {
		SqlSession session =sessionFactory.getSession();
		try {
			UserMapper userOperation = session.getMapper(UserMapper.class);
			userOperation.updateRole(user);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 按账号查询
	 */
	public User selectUserByNumber(String number) {
		SqlSession session =sessionFactory.getSession();
		UserMapper userOperation = session.getMapper(UserMapper.class);
		User user = userOperation.selectUserByNumber(number);
		if(user==null){
			System.out.println("账号不存在");
			return null;
		}else{
			return user;	
		}
	
	}
	
	
	//测试方法
	@Test
	public void userTest() throws IOException{
	//User user = new User("1234567890", "123321", "12324345664", "山西省")	;
	UserService userService = new UserService();
	//userService.addUser(user);
//	 File file = new File("C:/Users/MrDu/Desktop/2323.jpg");
//	 byte[] b=null;
//	 try {
//		InputStream photoStream = new FileInputStream(file);
//		try {
//			 b= IOUtils.toByteArray(photoStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		User user = new User(1,"趣多多", "水瓶座", "A", "没有什么不同", "2017-7-1", b, "看书，听音乐", "程序员", "男", "213231", 1);
		User user = userService.selectUserByID(1);
		//System.out.println(user.toString());
		//userService.createRole(user);
//		log.info(user);
//		OutputStream out = new FileOutputStream("C:/Users/MrDu/Desktop/2324.jpg");
//		out.write(user.getUfacing());
		//user.setPhone("15735185214");
	//	System.out.println(user.toString());
		
	//User user = userService.selectUserByNumber("1334610525");
		user.setPassword("erererer");
		userService.updateRole(user);
		log.info(user.toJson());
		
	}
}
