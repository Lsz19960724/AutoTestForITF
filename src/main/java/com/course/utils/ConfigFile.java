package com.course.utils;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.course.model.InterfaceName;

public class ConfigFile {
	/**
	 * 工具类
	 *获取配置文件的url
	 */
	private static ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
	//将工具类设置成静态方法（不用new就可以调用），传入枚举类
	public static String getUrl(InterfaceName name) {
		String address = bundle.getString("test.url");
		String uri="";
		//拼接的最终测试地址
		String testUrl;
		if(name==InterfaceName.GETUSERLIST) {
			uri=bundle.getString("getUserList.uri");
		}
		if(name==InterfaceName.LOGIN) {
			uri=bundle.getString("login.uri");
		}
		if (name==InterfaceName.UPDATEUSERINFO) {
			uri=bundle.getString("updateUserInfo.uri");
		}
		if(name==InterfaceName.GETUSERINFO) {
			uri=bundle.getString("getuserInfo.uri");
		}
		if(name==InterfaceName.ADDUSERINFO) {
			uri=bundle.getString("addUserInfo.uri");
		}
		testUrl=address+uri;
		return testUrl;
		
	}
	
	
	
	
}
