package com.course.cases;

import org.apache.http.client.HttpClient;
import org.testng.annotations.BeforeTest;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;

public class LoginTest {
	@BeforeTest(groups ="loginTrue",description="测试准备工作，获取httpClient对象")
	public void beforeTest(){
		//获取getUserInfo的url 放入TestConfig对象中,下面四个同理
		TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
		TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
		TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
		TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
		TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
		HttpClient.
		
		
	}
	
	
}
