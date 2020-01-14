package com.tester.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import lombok.Data;

@Data
public class TestConfig {
	public static String loginUrl;
	public static String updateUserInfoUrl;
	public static String getUserListUrl;
	public static String getUserInfoUrl;
	public static String addUserUrl;
	//����http�ͻ���
	public static DefaultHttpClient defaultHttpClient;
	//�����洢cookies��Ϣ�ı���
	public static CookieStore store;


}
