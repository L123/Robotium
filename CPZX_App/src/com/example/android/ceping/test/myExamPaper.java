package com.example.android.ceping.test;

import android.view.View;

import com.robotium.solo.Solo;

public class myExamPaper {
	RobotiumTest1 RT = new RobotiumTest1();
	//Config C = new Config();
	//打开试卷或结果页面之后再返回首页
	public void openPaper(Solo solo,String grade,String subject,String listMessage){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYEXAMPAPER);
		RT.choose(solo,grade,subject);
		solo.sleep(1000);
		RT.found(solo, listMessage);
		solo.clickOnView((View)solo.getText(listMessage).getParent());
		solo.sleep(3000);
		if(solo.getText(Config.HANDING).isShown()==true){
			solo.goBack();
			solo.clickOnView(solo.getView("sureBT"));
		}
		else if(solo.getText(Config.SCANTRON).isShown()==true){
			solo.goBack();
			
		}
		//返回首页
		solo.goBack();	
	}
	
	//根据测试的要求，进行删除然后返回首页，当n=1时执行删除
	public void deletePaper(Solo solo,String grade,String subject,String listMessage,int n){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYEXAMPAPER);
		RT.choose(solo,grade,subject);
		RT.found(solo, listMessage);
		solo.clickLongOnView((View) solo.getText(listMessage).getParent());
		if(n==1){
			solo.clickOnView(solo.getView("sureBT"));//确认删除测试记录
			RT.checkMessage(solo, Config.Toast_DELETE_SUCCEED);
		}
		else{
			solo.clickOnView(solo.getView("cancel"));//取消删除测试记录
		}
		solo.sleep(1000);
		solo.goBack();
	}
}
