package com.course.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseUtil {
	/**
	 * 
	 * 从databaseConfig.xml获取配置并连接数据库，返回一个sqlSession用来执行sql语句
	 * @return
	 * @throws IOException
	 */
	public static SqlSession getSqlSession() throws IOException {
		//获取配置的资源文件
		Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		//sqlSession就是能够执行配置文件中的sql语句
		SqlSession sqlSession = factory.openSession();
		return sqlSession;
		
	}
}
