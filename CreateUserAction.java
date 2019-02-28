
package com.internousdev.yellow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware
{
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private String defaultSexValue = MALE;

	//	Receive + Send
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String loginId;
	private String password;
	private List<String> sexList;
	private List<String> errorMsgList;

	//	Session
	private Map<String,Object>session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	性別が選択されていないのならば、デフォルトの性別を選択するように変更
		if(sex == null)
		{
			sex = defaultSexValue;
		}
		else if(sex.equals("0"))
		{
			sex = MALE;
		}
		else
		{
			sex = FEMALE;
		}

		//	パスワードは空に
		password = "";

		//	性別リスト作成
		sexList = new ArrayList<String>();
		sexList.add(MALE);
		sexList.add(FEMALE);

		return SUCCESS;
	}
	public String getFamilyName()
	{
		return familyName;
	}
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getFamilyNameKana()
	{
		return familyNameKana;
	}
	public void setFamilyNameKana(String familyNameKana)
	{
		this.familyNameKana = familyNameKana;
	}
	public String getFirstNameKana()
	{
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana)
	{
		this.firstNameKana = firstNameKana;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public List<String> getSexList()
	{
		return sexList;
	}
	public void setSexList(List<String> sexList)
	{
		this.sexList = sexList;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getLoginId()
	{
		return loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}
	public List<String> getErrorMsgList()
	{
		return errorMsgList;
	}
	public void setErrorMsgList(List<String> errorMsgList)
	{
		this.errorMsgList = errorMsgList;
	}
}
