package com.tester.utils;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.tester.model.InterfaceName;

public class ConfigFile {
	/**
	 * ������
	 *��ȡ�����ļ���url
	 */
	private static ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
	//�����������óɾ�̬����������new�Ϳ��Ե��ã�������ö����
	public static String getUrl(InterfaceName name) {
		String address = bundle.getString("test.url");
		String uri="";
		//ƴ�ӵ����ղ��Ե�ַ
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