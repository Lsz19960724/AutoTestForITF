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
	@BeforeTest(groups ="loginTrue",description="����׼����������ȡhttpClient����")
	public void beforeTest(){
		//��ȡgetUserInfo��url ����TestConfig������,�����ĸ�ͬ��
		TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
		TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
		TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
		TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
		TestConfig.updateUserInfoUrl= ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
		
		TestConfig.defaultHttpClient = new DefaultHttpClient();
		
	}
	/**
	 *  ģ���û���¼�ɹ���ʹ��getResult����ģ���û���¼��	
	 *   �鿴��Ӧ�����Ԥ�ڽ���Ƿ���ͬ
	 * @throws Exception
	 */
	@Test(groups="loginTrue",description="�û��ɹ���¼�ӿ�")
	public void loginTrue() throws Exception {
		//�����ݿ⹤������ʵ����������ִ��sql����sqlsession�����ݿ⹤�����Ѿ�д�õģ�
		SqlSession session = DatabaseUtil.getSqlSession();
		//ִ�в�ѯ����ѯ�����װ��LoginCase��
		LoginCase loginCase = session.selectOne("logincase",1);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		//�±ߵĴ���Ϊд��ӿڵĲ��Դ���
		String result = getResult(loginCase);
		//�������������жϷ��ؽ����Ԥ�ڽ���Ƿ���ͬ
		Assert.assertEquals(loginCase.getExpected(), result);
		
	}
	@Test(description="�û���¼�ӿ�ʧ��")
	public void loginFalse() throws Exception {
		SqlSession session = DatabaseUtil.getSqlSession();
		LoginCase loginCase = session.selectOne("loginCase", 2);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		//�±ߵĴ���Ϊд��ӿڵĲ��Դ���
		String result = getResult(loginCase);
		//����������Ӧ�����Ԥ�ڽ���Ƿ���ͬ
		Assert.assertEquals(loginCase.getExpected(), result);
		
	}
	public String getResult(LoginCase logincase) throws Exception {
		//�±ߵĴ���Ϊд��ӿڵĲ��Դ���
		//��url����httpPost��
		HttpPost httpPost = new HttpPost(TestConfig.loginUrl);
		//�½�һ��json����������Ų���
		JSONObject param = new JSONObject();
		//���������˺����룩����json��
		param.put("userName", logincase.getUserName());
		param.put("passWord", logincase.getPassWord());
		//��������ͷ��Ϣ
		httpPost.setHeader("content-type","application/json");
		//�����������Ϣ��ӵ�������
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		httpPost.setEntity(entity);
		//����һ��������������Ӧ����Ĵ���
		String result;
		//�ͻ���ִ��httpPost���󲢷�����Ӧ���
		CloseableHttpResponse response = TestConfig.defaultHttpClient.execute(httpPost);
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		//�ӿͻ��˵õ�cookies��Ϣ��װ��TestConfig��
		TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();
		return result;
	}
}
