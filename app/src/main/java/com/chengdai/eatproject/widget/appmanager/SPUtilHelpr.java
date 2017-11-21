package com.chengdai.eatproject.widget.appmanager;


import android.text.TextUtils;

import com.chengdai.eatproject.base.BaseApplication;
import com.chengdai.eatproject.uitls.SPUtils;

/**
 * SPUtils 工具辅助类
 */

public class SPUtilHelpr {

	private static final String USERTOKEN="user_toke";
	private static final String USERID="user_id";
	private static final String ISSETPAYPWD="is_set_pay_pwd";//是否设置过支付密码
	private static final String USERPHONECODE="user_phone";//用户手机

	/**
	 * 设置用户token
	 * @param s
	 */
	public static void saveUserToken(String s)
	{
		SPUtils.put(BaseApplication.getInstance(),USERTOKEN,s);
	}

/**
	 * 设置用户token
	 * @param
	 */
	public static String getUserToken()
	{
     	return  SPUtils.getString(BaseApplication.getInstance(),USERTOKEN,"");
	}

	/**
	 * 设置用户token
	 * @param s
	 */
	public static void saveUserPhone(String s)
	{
		SPUtils.put(BaseApplication.getInstance(),USERPHONECODE,s);
	}

/**
	 * 设置用户token
	 * @param
	 */
	public static String getUserphone()
	{
     	return  SPUtils.getString(BaseApplication.getInstance(),USERPHONECODE,"");
	}


	/**
	 * 是否设置过支付密码
	 * @param s
	 */
	public static void setIssetpaypwd(boolean s)
	{
		SPUtils.put(BaseApplication.getInstance(),ISSETPAYPWD,s);
	}

/**
	 * 是否设置过支付密码
 * @param
 */
	public static Boolean isSetpaypwd()
	{
     	return  SPUtils.getBoolean(BaseApplication.getInstance(),ISSETPAYPWD,true);
	}

/*
	*/
/**
	 * 设置用户token
	 * @param
	 *//*

	public static void changeIsFirstAddressState(boolean isfirst)
	{
		SPUtils.put(BaseApplication.getInstance(),ISFIRSTADDADDRESS,isfirst);
	}

*/
/**
	 * 设置用户token
	 * @param
	 *//*

	public static boolean isFirstAddressState()
	{
     	return  SPUtils.getBoolean(BaseApplication.getInstance(),ISFIRSTADDADDRESS,true);
	}
*/

	/**
	 * 设置用户token
	 * @param s
	 */
	public static void saveUserId(String s)
	{
		SPUtils.put(BaseApplication.getInstance(),USERID,s);
	}


	/**
	 * 设置用户token
	 * @param
	 */
	public static String getUserId()
	{
	  return SPUtils.getString(BaseApplication.getInstance(),USERID,"");

	}

	/**
	 * 保存选择定位信息
	 * @param s
	 */
	public static void saveRestLocationInfo(String s)
	{
		SPUtils.put(BaseApplication.getInstance(),"LOCATIONINFRESET",s);
	}


	/**
	 * 判断用户是否登录
	 * @return
	 */
	public static boolean isLoginNoStart(){
		if(TextUtils.isEmpty(getUserToken())){

			return false;
		}

		return true;
	}

	/**
	 * 退出登录清除数据
	 */
	public static void logOutClear(){
		saveUserToken("");
		saveUserPhone("");
		saveUserId("");
		setIssetpaypwd(false);
	}

}
