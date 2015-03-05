package com.example.android.ceping.test;

import android.view.View;
import android.widget.EditText;
import static org.junit.Assert.assertEquals;
import com.robotium.solo.Solo;

public class accountSet {
	RobotiumTest1 RT = new RobotiumTest1();
	//Config C = new Config();
	//进入账号设置页面
	public void accountSet(Solo solo){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.ACCOUNT_SETTING);
		RT.checkMessage(solo, Config.NICKNAME);
	}
	
	//在账号设置页面退出应用
	public void loginout(Solo solo,int n){
		solo.waitForText(Config.LOGOFF, 1, 10000);
		solo.clickOnText(Config.LOGOFF);
		RT.checkMessage(solo,Config.LOGIN);
		for(int i=0;i<n;i++){
			solo.goBack();
			solo.sleep(100);
		}
	}
	
	//修改昵称
	public void nickname(Solo solo,String nickname,String checkMessage){
		accountSet(solo);
		solo.clickOnView(solo.getView("setting_nickname_rl")); 
		solo.clearEditText(0);
		solo.enterText(0, nickname);
		solo.clickOnView(solo.getView("modify_nickname_button"));
		RT.checkDynamicMessage(solo, checkMessage);
		if(nickname==""){
			solo.goBack();
		}
		else{
			EditText text = (EditText) solo.getView("setting_nickname_text");
			String nickname1 = text.getText().toString();
			assertEquals(nickname,nickname1);
		}	
		loginout(solo,2);
	}
	
	//个性签名
	public void signature(Solo solo,String signature,String checkMessage){
		accountSet(solo);
		solo.clickOnView(solo.getView("setting_signature_rl"));
		solo.clearEditText(0);
		solo.enterText(0, signature);
		solo.clickOnView(solo.getView("modify_signature_submit"));
		RT.checkMessage(solo, checkMessage);
		solo.sleep(2000);
		EditText text = (EditText) solo.getView("setting_signature_text");
		String signature1 = text.getText().toString();
		assertEquals(signature,signature1);
//		System.out.println(signature);
//		System.out.println(signature1);
		loginout(solo,2);
	}
	
	//修改密码
	public void changePassWord(Solo solo,String oldPassWord,String newPassWord,String surePassWord,String checkMessage){
		accountSet(solo);
		solo.clickOnView(solo.getView("setting_password_rl"));
		solo.enterText(0, oldPassWord);
		solo.enterText(1, newPassWord);
		solo.enterText(2, surePassWord);
		solo.clickOnView(solo.getView("modify_password_button"));
		RT.checkDynamicMessage(solo, checkMessage);
		if(checkMessage!=Config.Toast_CHANGE_PASSWORD){
			solo.goBack();
		}
		loginout(solo,0);
		//RT.checkLogin(solo,"teachertest02","jiaoyu");
		//loginout(solo,2);
	}
	
	//等级和水平
	public void level(Solo solo,String checkMessage){
		accountSet(solo);
		solo.clickOnView(solo.getView("setting_level_rl"));
		RT.checkMessage(solo, checkMessage);
		solo.goBack();
		loginout(solo,2);
	}
	
	//关于测评中心
	public void about(Solo solo,String title,String checkMessage,String opinion,String phone){
		accountSet(solo);
		solo.clickOnView(solo.getView("setting_about_rl"));
		//服务协议
		if(solo.getView("about_agreement_rl")==(View)solo.getText(title).getParent()){
			solo.clickOnView(solo.getView("about_agreement_rl"));
			RT.checkMessage(solo, checkMessage);
			solo.goBack();
		}
		//意见反馈，反馈成功提示：感谢您提供的反馈
		else if(solo.getView("about_feedback_rl")==(View)solo.getText(title).getParent()){
			solo.clickOnView(solo.getView("about_feedback_rl"));
			solo.enterText(0, opinion);
			solo.enterText(1, phone);
			solo.sleep(1000);
			if(opinion!="" || phone!=""){
				solo.clickOnView(solo.getView("feedback_btn_submit"));
				RT.checkMessage(solo, checkMessage);
				solo.sleep(1000);
			}
		}
		//版本更新
		else if(solo.getView("about_update_rl")==(View)solo.getText(title).getParent()){
			solo.clickOnView(solo.getView("about_update_rl"));
			RT.checkMessage(solo, checkMessage);
			solo.goBack();		
		}	
		solo.goBack();
		loginout(solo,2);
	}
}
