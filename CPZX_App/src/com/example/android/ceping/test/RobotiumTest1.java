package com.example.android.ceping.test;

import static org.junit.Assert.assertEquals;
import android.os.SystemClock;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robotium.solo.Solo;
public class RobotiumTest1 {
	//Config C = new Config();
	
	//��¼
	public void login(Solo solo,String username,String password){
		checkMessage(solo,Config.LOGIN);	
		solo.clearEditText(0);
		solo.clearEditText(1);
		solo.enterText(0, username);
		solo.enterText(1, password);
		//��� ��ť  
		solo.clickOnView(solo.getView("login_bt"));  //�����¼��ť
	}	
	
	//����û��Ѿ���¼�����˳���¼���µ�¼
	public void login2(Solo solo,String username,String password){
		if(solo.searchText(Config.LOGIN)==false){
			solo.waitForText(Config.SEVER_DAYS_DIAGNOSIS, 1, 10000);
			loginout(solo);
		}
		login(solo,username,password);
	}
	
	//�ж�Ӧ���Ƿ��¼������û�е�¼�͵���login�����������е�¼����
	public void checkLogin(Solo solo,String username,String password){
		if(solo.searchText(Config.LOGIN)==false){
			solo.waitForText(Config.SEVER_DAYS_DIAGNOSIS, 1, 10000);
			//loginout();
		}
		else{	
			login(solo,username,password);
			solo.waitForText(Config.SEVER_DAYS_DIAGNOSIS, 1, 10000);
		}
			checkMessage(solo,Config.SEVER_DAYS_DIAGNOSIS);
		}
	
	//����ҳ�˳���¼
	public void loginout(Solo solo){
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.ACCOUNT_SETTING);
		solo.waitForText(Config.LOGOFF, 1, 10000);
		solo.clickOnText(Config.LOGOFF);
		checkMessage(solo,Config.LOGIN);
	}
	
	//����ҳ���˳���¼(�����ڲ�������)
	public void loginout2(Solo solo){		
		solo.goBack();					
		solo.clickOnView(solo.getView("sureBT"));
		solo.goBack();	
		solo.clickOnView(solo.getView("home_top_iv_left"));
		solo.clickOnText(Config.ACCOUNT_SETTING);
		solo.waitForText(Config.LOGOFF, 1, 10000);
		solo.clickOnText(Config.LOGOFF);
		checkMessage(solo,Config.LOGIN);
	}
		
	//��鶯̬ҳ����Ϣ
	public void checkDynamicMessage(Solo solo,String message){
		//solo.waitForText(message,1,10000);
		boolean actual = solo.searchText(message);
		boolean expected = true;
		assertEquals("û���ҵ���"+message+"�������Ϣ",expected,actual);		
	}
	
	//��龲̬ҳ����Ϣ
	public void checkMessage(Solo solo,String message){
		solo.waitForText(message,1,10000);
		boolean actual = solo.searchText(message);
		boolean expected = true;
		assertEquals("û���ҵ���"+message+"�������Ϣ",expected,actual);
	}
			
	//�꼶��ѧ��ѡ��
	public void choose(Solo solo,String grade,String subject){
		
		if(!grade.equals("") || !subject.equals("")){
			solo.clickOnView(solo.getView("home_top_iv_right"));//���ѡ��ͼ��{
			if(grade!=null){
				solo.clickOnText(grade);
			}
			if(subject!=null){
				solo.clickOnText(subject);
			}
			solo.clickOnView(solo.getView("filter_sureBT"));
			solo.sleep(3000);
		}
	}
		
	//����ͬ��ѵ���½�ҳ�棬���Ծ����α����Ʋ�����listMessage���½ڣ�chapter
	public void syncExercises(Solo solo,String grade,String subject,String listMessage,String chapter){
		solo.clickOnText(Config.SYNCEXERCISES);
		choose(solo,grade,subject);
		solo.waitForText(listMessage);
		//ͬ����ϰ�γ��б��е�listMessage		
		assertEquals(true,found(solo, listMessage));	
		solo.clickOnView((View) solo.getText(listMessage).getParent().getParent());
		TextView TV = solo.getText(chapter);
		RelativeLayout R = (RelativeLayout) TV.getParent();
		View button = (View) R.getChildAt(2);//.findViewWithTag("com.dtedu.mobile.evation.bean.Catalog@b5234688");
		solo.clickOnView(button);
		solo.sleep(3000);
		checkMessage(solo,Config.SCANTRON);
	}
	
	//����ר��ѵ��֪ʶ��ҳ�棬���֪ʶ�������Ծ���֪ʶ�����Ʋ�����listMessage
	public void specialExercises(Solo solo,String grade,String subject,String listMessage){
		solo.clickOnText(Config.SPERXIALEXERCISES);
		choose(solo,grade,subject);
		solo.waitForText(listMessage);
		solo.clickOnView((View)solo.getText(listMessage).getParent().getParent());
		solo.sleep(5000);
		checkMessage(solo,Config.SCANTRON);
	}
		
	//���뿼��ѵ��������б��е��Ծ����Ծ����Ʋ�����listMessage
	public void examExercises(Solo solo,String grade,String subject,String listMessage,String key,String checkMessage){
		solo.clickOnText(Config.EXAMEXERCISES);
		solo.sleep(1000);
		//solo.scrollToSide(solo.UP);
		choose(solo,grade,subject);
		keySearch(solo,key,checkMessage);
		//boolean a = found(solo,listMessage);
		assertEquals("û���ҵ��Ծ���"+listMessage,true,found(solo,listMessage));	
//		do{
//			solo.waitForText(listMessage,1,10000);
//			if(solo.searchText(Config.SLCMOL)==true){
//				solo.clickLongOnText(Config.SLCMOL);
//			}
//		}while(solo.searchText(listMessage)==false);	
		solo.sleep(2000);
		solo.clickOnView((View)solo.getText(listMessage).getParent());
		solo.sleep(3000);
		checkMessage(solo, Config.SCANTRON);

	}
		
	//����ǿ��ѵ��������б���֪ʶ���Ӧ��ͼ�꣬һ��֪ʶ�������listMessage
	public void intensifyExercisesOne(Solo solo,String grade,String subject,String listMessage){
		solo.clickOnText(Config.INTENSIFYEXERCISES);
		choose(solo,grade,subject);
		solo.waitForText(listMessage);
		TextView TV = solo.getText(listMessage);
		RelativeLayout R = (RelativeLayout) TV.getParent();
		View button = (View) R.getChildAt(2);
		solo.clickOnView(button);
		solo.sleep(3000);
		checkMessage(solo, Config.TEST_LIBRARY);
		//checkMessage(solo,C.SCANTRON);
	}
	
	//����ǿ��ѵ����֪ʶ�����1,2����listMessageOne��listMessageTwo
	public void intensifyExercisesTwo(Solo solo,String grade,String subject,String listMessageOne,String listMessageTwo){
		solo.clickOnText(Config.INTENSIFYEXERCISES);
		choose(solo,grade,subject);
		solo.waitForText(listMessageOne);
		solo.clickOnView(solo.getText(listMessageOne));
		solo.waitForText(listMessageTwo);
		TextView TV = solo.getText(listMessageTwo);
		RelativeLayout R = (RelativeLayout) TV.getParent();
		View button = (View) R.getChildAt(2);
		solo.clickOnView(button);
		solo.sleep(3000);
		checkMessage(solo, Config.TEST_LIBRARY);
		//checkMessage(solo,C.SCANTRON);
	}
	
	//����ǿ��ѵ����֪ʶ�����1,2,3����listMessageOne��listMessageTwo,listMessageThree
	public void intensifyExercisesThree(Solo solo,String grade,String subject,String listMessageOne,String listMessageTwo,String listMessageThree){
		solo.clickOnText(Config.INTENSIFYEXERCISES);
		choose(solo,grade,subject);
		solo.waitForText(listMessageOne);
		solo.clickOnView((View) solo.getText(listMessageOne).getParent());
		solo.waitForText(listMessageTwo);
		solo.clickOnView((View) solo.getText(listMessageTwo).getParent());
		solo.waitForText(listMessageThree);
		TextView TV = solo.getText(listMessageThree);
		RelativeLayout R = (RelativeLayout) TV.getParent();
		View button = (View) R.getChildAt(2);
		solo.clickOnView(button);
		solo.sleep(3000);
		checkMessage(solo, Config.TEST_LIBRARY);
		//checkMessage(solo,C.SCANTRON);
	}
	
	//��������ղ�����,������ҳ
	public void collectTestQuestions(Solo solo,int n){
		for(int i=0;i<n;i++){
			if(solo.waitForText(Config.ADD_FAVOURITES)==false){
				solo.scrollToSide(solo.RIGHT);
			}
			else{				
				solo.clickOnText(Config.ADD_FAVOURITES);	
				checkMessage(solo,Config.CANCLE_FAVOURITES);
				if(i<n-1){   //���һ���������Ӻ���Ҫ��ҳ
					solo.scrollToSide(solo.RIGHT);
					solo.sleep(1000);
				}					
			}			
		}
		solo.goBack();
		solo.sleep(1000);
	}
	
	//���������������ȡ���ղ�,������ҳ
	public void cancleTestQuestions(Solo solo,int n){
		for(int i=0;i<n;i++){
			if(solo.waitForText(Config.CANCLE_FAVOURITES)==false){
				solo.scrollToSide(solo.RIGHT);
			}
			else{				
				solo.clickOnText(Config.CANCLE_FAVOURITES);	
				checkMessage(solo,Config.ADD_FAVOURITES);
				if(i<n-1){ 
					//���һ���������Ӻ���Ҫ��ҳ
					solo.scrollToSide(solo.RIGHT);
					solo.sleep(1000);
				}					
			}			
		}
		solo.goBack();
		solo.sleep(1000);
	}
	
	//��ȡ���Կ���������
	public int number(Solo solo){
		solo.sleep(3000);
		TextView view=(TextView) solo.getView("autoexerciselist_lib_num");
		String a=(view.getText().toString());
		int N= Integer.parseInt(a, 10);
		return N;
	}
	//����Կ��������Ⲣ�����Ծ�
	public void addQuestions(Solo solo,int n){
		int a = number(solo);
		int add=0;
		for(int i=0;i<n;i++){
			if(solo.waitForText(Config.ADD_TEST_LIBRARY)==false){
				solo.scrollToSide(solo.RIGHT);
			}
			else{
				int m=1;
				do{						
					m++;
					
					solo.clickOnText(Config.ADD_TEST_LIBRARY);	
					if(solo.waitForText(Config.Toast_ADD_TEST_LIBRARY)==true){
						m=0;
						solo.scrollToSide(solo.RIGHT);
						//solo.sleep(500);
						//solo.clickOnText(C.ADD_TEST_LIBRARY);	
					}
				}while(m==0);
					
				if(i<n-1){   //���һ���������Ӻ���Ҫ��ҳ
					solo.scrollToSide(solo.RIGHT);
					//solo.sleep(1000);
				}	
				add++;
			}			
		}
		//��ȡ���Կ�������
		int b= number(solo);
		int c=a+add;		
		assertEquals(b,c);
		solo.clickOnView(solo.getView("autoexerciselist_right_lib_rl"));//������Կ�
		solo.sleep(3000);			
		solo.clickOnView(solo.getView("layout_exerciselib_right_lib_rl"));//�����ʼ����������ҳ��
		solo.sleep(3000);
	}
		
	//����
	public void answer(Solo solo){
		do{
			if(solo.getView("button_A").isShown()==true){
				solo.clickOnButton(Config.CHOICE_QUESTION);
			}
			else if(solo.getView("exam_next_title_btn").isShown()==true){
				solo.enterText(0, Config.FILLS_QUESTION);
				solo.clickOnView(solo.getView("exam_next_title_btn"));
			}				
			else if(solo.searchText(Config.SEND)==false){				
				solo.sleep(3000);
				//solo.clickOnView(solo.getView("handle_imageview"));
				solo.takeScreenshot();
				solo.scrollToSide(solo.RIGHT);
//				do{
//					if(solo.getView("button_child_A").isShown()==true){
//						solo.clickOnButton(C.CHOICE_QUESTION);
//					}						
//					else if(solo.getView("exam_child_next_title_btn").isShown()==true){
//						solo.enterText(0, "����");
//						solo.clickOnView(solo.getView("exam_child_next_title_btn"));
//					}						
//				}while(solo.getView("button_child_A").isShown()==true || solo.getView("exam_child_next_title_btn").isShown()==true);					
			}
		}while(solo.searchText(Config.SEND)==false);			
		solo.sleep(1000);			
		if(solo.getView("button_A").isShown()==true){
			solo.clickOnButton(Config.CHOICE_QUESTION);
			solo.clickOnText(Config.HANDING);								
		}
		else if(solo.searchText(Config.SEND)==true){
			solo.enterText(0, Config.FILLS_QUESTION);
			solo.clickOnView(solo.getView("exam_next_title_btn"));
		}
		solo.clickOnView(solo.getView("sureBT"));
		solo.sleep(2000);
		solo.goBack();
		solo.goBack();
	}	
	
	//�����б��е���Ϣ��listMessage���б��е��ı���Ϣ
	public boolean found(Solo solo,String listMessage){
		final long endTime = SystemClock.uptimeMillis() + Config.TIMEOUT;
		while(SystemClock.uptimeMillis() < endTime){
			if(solo.waitForText(listMessage,1,10000)){
				return true;
			}		
			else if(solo.searchText(Config.SLCMOL,true)==true){
				solo.clickOnText(Config.SLCMOL);
			}		
		}
		return false;			
	}
	//��ȡģ���������� 
		public int number2(Solo solo,String id){
			solo.sleep(3000);
			TextView view=(TextView) solo.getView(id);
			String a=(view.getText().toString()).replace("/", "");
			int N= Integer.parseInt(a, 10);
			return N;
		}
		
		public void answer2(Solo solo){
			do{
				if(solo.getView("button_A").isShown()==true){
					solo.clickOnButton(Config.CHOICE_QUESTION);
				}
				else if(solo.getView("exam_next_title_btn").isShown()==true){
					solo.enterText(0, Config.FILLS_QUESTION);
					solo.clickOnView(solo.getView("exam_next_title_btn"));
				}				
				else if(solo.searchText(Config.SEND)==false){				
					solo.sleep(3000);
					//solo.clickOnView(solo.getView("handle_imageview"));
					solo.takeScreenshot();
					solo.setSlidingDrawer(0, solo.OPENED);
					int n=0;					
					do{
						if(solo.getView("button_child_A").isShown()==true){
							solo.clickOnButton(Config.CHOICE_QUESTION);
							//n++;
						}						
						else if(solo.getView("exam_child_next_title_btn").isShown()==true){
							solo.enterText(n, "����");
							solo.sleep(1000);
							solo.clickOnView(solo.getView("exam_child_next_title_btn"));
							n++;
							
							solo.sleep(2000);
						}						
					}while(solo.getView("handle_imageview") != null);					
				}
			}while(solo.searchText(Config.SEND)==false);			
			solo.sleep(1000);			
			if(solo.getView("button_A").isShown()==true){
				solo.clickOnButton(Config.CHOICE_QUESTION);
				solo.clickOnText(Config.HANDING);								
			}
			else if(solo.searchText(Config.SEND)==true){
				solo.enterText(0, Config.FILLS_QUESTION);
				solo.clickOnView(solo.getView("exam_next_title_btn"));
			}
			solo.clickOnView(solo.getView("sureBT"));
			solo.sleep(2000);
			solo.goBack();
			solo.goBack();
		}
	
	//����	
	public void takeScreenshot(Solo solo){
		solo.takeScreenshot();
	}
	
	//�ؼ�������
	public void keySearch(Solo solo,String key,String checkMessage){
		if(!key.equals("")){					
			solo.clickOnView(solo.getView("home_top_iv_search"));
			solo.sleep(500);
			solo.enterText(0, key);
			solo.clickOnView(solo.getView("activity_analog_framgment_search_bt"));
			solo.sleep(2000);
			if(!checkMessage.equals("")){
				checkMessage(solo,checkMessage);
			}
		}
	}
	
}