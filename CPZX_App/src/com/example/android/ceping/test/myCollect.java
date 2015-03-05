package com.example.android.ceping.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.robotium.solo.Solo;

public class myCollect {
	RobotiumTest1 RT = new RobotiumTest1();
	//Config C = new Config();
	@Test
	//������⵽���Կ������Ծ�
	public void makePaper(Solo solo,String grade,String subject,int n) {
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYCOLLECT);
		RT.choose(solo,grade,subject);
		RT.addQuestions(solo, n);
		
	}
	//���������ȡ���ղ�,������ҳn���������������m�Ƿ�ȡ���ղ�
	public void cancleTestQuestions(Solo solo,String grade,String subject,int n,int m){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYCOLLECT);
		int a = RT.number2(solo,"myfavorites_activity_tv_page_total");
		int add=0;
		RT.choose(solo,grade,subject);
		for(int i=0;i<n;i++){
			if(solo.waitForText(Config.CANCLE_FAVOURITES)==true){			
				solo.clickOnText(Config.CANCLE_FAVOURITES);	
				if(m==1){
					solo.clickOnView(solo.getView("sureBT"));
					add--;
				}
				else{
					solo.clickOnView(solo.getView("cancel"));
				}
			}
			else{
				solo.scrollToSide(solo.RIGHT);
			}					
		}
		int b=  RT.number2(solo,"myfavorites_activity_tv_page_total");
		int c=a+add;		
		assertEquals(b,c);
		solo.goBack();
		solo.sleep(1000);
	}
	
	//�����Ƴ����Կ�
	public void outOfQuestions(Solo solo,String grade,String subject){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYCOLLECT);
		int a = RT.number(solo);
		int add=0;
		RT.choose(solo,grade,subject);	
		//solo.waitForText(Config.OUT_OF_TEST_LIBRARY);
		if(solo.waitForText(Config.OUT_OF_TEST_LIBRARY)==true){
			solo.clickOnText(Config.OUT_OF_TEST_LIBRARY);
			add--;
		}
		else if(solo.waitForText(Config.ADD_TEST_LIBRARY)==true){
			solo.clickOnText(Config.ADD_TEST_LIBRARY);
			solo.sleep(1000);
			solo.clickOnText(Config.OUT_OF_TEST_LIBRARY);
		}
		RT.checkMessage(solo, Config.ADD_TEST_LIBRARY);
		int b= RT.number(solo);
		int c=a+add;		
		assertEquals(b,c);
		solo.goBack();
	}
}
