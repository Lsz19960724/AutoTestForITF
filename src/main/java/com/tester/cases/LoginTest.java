package com.tester.cases;

import java.awt.print.Paper;
import java.io.IOException;
import java.text.ParsePosition;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceName;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;

public class LoginTest {
	@BeforeTest(groups ="loginTrue",description="测试准备工作，获取httpClient对象")
	public void beforeTest(){
		//获取getUserInfo的url 放入TestConfig对象中,下面四个同理
		TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
		TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
		TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
		TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
		TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
		
		TestConfig.defaultHttpClient = new DefaultHttpClient();
		
	}
	/**
	 *  模拟用户登录成功（使用getResult方法模拟用户登录）	
	 *   查看响应结果与预期结果是否相同
	 * @throws Exception
	 */
	@Test(groups="loginTrue",description="用户成功登录接口")
	public void loginTrue() throws Exception {
		//从数据库工具类里实例化出用来执行sql语句的sqlsession（数据库工具类已经写好的）
		SqlSession session = DatabaseUtil.getSqlSession();
		//执行查询语句查询结果封装到LoginCase中
		LoginCase loginCase = session.selectOne("logincase",1);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		//下边的代码为写完接口的测试代码
		String result = getResult(loginCase);
		//处理结果，就是判断返回结果与预期结果是否相同
		Assert.assertEquals(loginCase.getExpected(), result);
		
	}
	@Test(description="用户登录接口失败")
	public void loginFalse() throws Exception {
		SqlSession session = DatabaseUtil.getSqlSession();
		LoginCase loginCase = session.selectOne("loginCase", 2);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		//下边的代码为写完接口的测试代码
		String result = getResult(loginCase);
		//处理结果，响应结果与预期结果是否相同
		Assert.assertEquals(loginCase.getExpected(), result);
		
	}
	public String getResult(LoginCase logincase) throws Exception {
		//下边的代码为写完接口的测试代码
		//将url传入httpPost中
		HttpPost httpPost = new HttpPost(TestConfig.loginUrl);
		//新建一个json对象用来存放参数
		JSONObject param = new JSONObject();
		//将参数（账号密码）放入json中
		param.put("userName", logincase.getUserName());
		param.put("passWord", logincase.getPassWord());
		//设置请求头信息
		httpPost.setHeader("content-type","application/json");
		//将参数添加信息添加到方法中
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		httpPost.setEntity(entity);
		//声明一个对象来进行响应结果的储存
		String result;
		//客户端执行httpPost请求并返回响应结果
		CloseableHttpResponse response = TestConfig.defaultHttpClient.execute(httpPost);
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		//从客户端得到cookies信息封装到TestConfig中
		TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
		return result;
	}
}
