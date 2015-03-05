package com.example.android.ceping.test;

import static org.junit.Assert.assertEquals;

import com.robotium.solo.Solo;

public class wrongBook {
	RobotiumTest1 RT = new RobotiumTest1();
	//Config C = new Config();
	//模块入口
	public void wrongBook(Solo solo){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.MYWRONGBOOK);
	}
	
	//添加试题到测试库生成试卷
	public void makePaper(Solo solo,String grade,String subject,int n) {
		wrongBook(solo);
		RT.choose(solo,grade,subject);
		RT.addQuestions(solo, n);
		RT.loginout2(solo);
	}
	
	//试题移出测试库
	public void outOfQuestions(Solo solo,String grade,String subject){
		wrongBook(solo);
		int a = RT.number(solo);
		int add=0;
		RT.choose(solo,grade,subject);
		if(solo.searchText(Config.MESSAGE_WRONGBOOK)){
			solo.sleep(500);
		}
		else{
			if(solo.waitForText(Config.OUT_OF_TEST_LIBRARY)==true){
				solo.clickOnText(Config.OUT_OF_TEST_LIBRARY);
				add--;
			}
			else if(solo.waitForText(Config.ADD_TEST_LIBRARY)==true){
				solo.clickOnText(Config.ADD_TEST_LIBRARY);
				solo.sleep(500);
				solo.clickOnText(Config.OUT_OF_TEST_LIBRARY);
			}
			RT.checkMessage(solo, Config.ADD_TEST_LIBRARY);
		}
		int b= RT.number(solo);
		int c=a+add;		
		assertEquals(b,c);
		solo.goBack();
		RT.loginout(solo);	
	}
		
	//移出错题本，n=1时确认移出错题本
	public void outOfWrongBook(Solo solo,String grade,String subject,int n){
		wrongBook(solo);
		int a = RT.number2(solo,"myfavorites_activity_tv_page_total");
		int add=0;
		RT.choose(solo,grade,subject);
		solo.waitForText(Config.OUT_OF_WRONGBOOK);
		solo.clickOnText(Config.OUT_OF_WRONGBOOK);	
		if(n==1){
			solo.clickOnView(solo.getView("sureBT"));
			add--;
		}
		else{
			solo.clickOnView(solo.getView("cancel"));
		}
		int b=  RT.number2(solo,"myfavorites_activity_tv_page_total");
		int c=a+add;		
		assertEquals(b,c);
		solo.goBack();
		RT.loginout(solo);
	}
}
