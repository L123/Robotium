package com.example.android.ceping.test;

import android.view.View;

import com.robotium.solo.Solo;

public class myExamPaper {
	RobotiumTest1 RT = new RobotiumTest1();
	//Config C = new Config();
	//���Ծ�����ҳ��֮���ٷ�����ҳ
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
		//������ҳ
		solo.goBack();	
	}
	
	//���ݲ��Ե�Ҫ�󣬽���ɾ��Ȼ�󷵻���ҳ����n=1ʱִ��ɾ��
	public void deletePaper(Solo solo,String grade,String subject,String listMessage,int n){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYEXAMPAPER);
		RT.choose(solo,grade,subject);
		RT.found(solo, listMessage);
		solo.clickLongOnView((View) solo.getText(listMessage).getParent());
		if(n==1){
			solo.clickOnView(solo.getView("sureBT"));//ȷ��ɾ�����Լ�¼
			RT.checkMessage(solo, Config.Toast_DELETE_SUCCEED);
		}
		else{
			solo.clickOnView(solo.getView("cancel"));//ȡ��ɾ�����Լ�¼
		}
		solo.sleep(1000);
		solo.goBack();
	}
}