package com.internousdev.yellow.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yellow.dao.UserInfoDAO;
import com.internousdev.yellow.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware
{
	//	Receive
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String loginId;
	private String password;

	private List<String> familyNameErrorMsgList;
	private List<String> firstNameErrorMsgList;
	private List<String> familyNameKanaErrorMsgList;
	private List<String> firstNameKanaErrorMsgList;
	private List<String> emailErrorMsgList;
	private List<String> loginIdErrorMsgList;
	private List<String> passwordErrorMsgList;

	//	Session
	private Map<String,Object> session;

	public String execute()
	{
		//	商品カテゴリがないならセッションタイムアウト
		if(!session.containsKey("mCategoryDtoList"))
		{
			return "sessionTimeOut";
		}

		//	入力値チェック
		InputChecker inputChecker = new InputChecker();

		familyNameErrorMsgList = inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false, false, false, false);
		firstNameErrorMsgList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false, false, false);
		familyNameKanaErrorMsgList = inputChecker.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		firstNameKanaErrorMsgList = inputChecker.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false, false, false, false, false);
		emailErrorMsgList = inputChecker.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false, false, false);
		loginIdErrorMsgList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMsgList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		//	エラーメッセージがある時エラーページに遷移
		if(!familyNameErrorMsgList.isEmpty()
	    || !firstNameErrorMsgList.isEmpty()
	    || !familyNameKanaErrorMsgList.isEmpty()
	    || !firstNameKanaErrorMsgList.isEmpty()
	    || !emailErrorMsgList.isEmpty()
	    || !loginIdErrorMsgList.isEmpty()
	    || !passwordErrorMsgList.isEmpty())
		{
			return ERROR;
		}
		//	ユーザーを作成
		UserInfoDAO UserInfoDao = new UserInfoDAO();
		int count = UserInfoDao.createUser(familyName, firstName, familyNameKana, firstNameKana, sex, email, loginId, password);

		if(count > 0)
		{
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
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
}
