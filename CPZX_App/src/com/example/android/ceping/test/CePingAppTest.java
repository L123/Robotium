package com.example.android.ceping.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robotium.solo.Solo;


public class CePingAppTest extends ActivityInstrumentationTestCase2 {
	@SuppressWarnings("unchecked")

	public CePingAppTest() {
		super(packageName, launchActivityClass);
		// TODO Auto-generated constructor stub
	}
	/************************************************************************/
	public Solo solo;
	RobotiumTest1 RT = new RobotiumTest1();
	RobotiumTest2 RT2 = new RobotiumTest2();
	myExamPaper EP = new myExamPaper();
	myCollect MC = new myCollect();
	wrongBook WB = new wrongBook();
	accountSet AS = new accountSet();
	/************************************************************************/
	private static Class<?>launchActivityClass;
	private static String mainActivity = "com.dtedu.mobile.evation.SeparateActivity";
	private static String packageName = "com.dtedu.mobile.evation";
	static{
		try {
			launchActivityClass = Class.forName(mainActivity);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/************************************************************************/
	@Before
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(),getActivity());
	}

	@After
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	/************************************************************************/

	@Test
		
	//��ȷ���û��������룬�ɹ���¼
	public void test001_LoginRightUsernameAndPassword() {	
		String username="teachertest02";
		String passWord="jiaoyu";
		String toastMessage = "�������";

		RT.login2(solo,username,passWord);
		RT.checkMessage(solo,toastMessage);
		RT.loginout(solo);
	}	
		
	//��ȷ���û�������������룬��¼��ʾ���������
	public void test002_LoginRightUsernameAndWrongPassword() {
		String username="teachertest02";
		String passWord="123";
		String toastMessage = "�������";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	

	//��ȷ���û���������С��3�����룬��¼��ʾ���������
	public void test003_LoginRightUsernameAndShortPassword() {
		String username="teachertest02";
		String passWord="12";
		String toastMessage = "���볤��Ӧ����3���ַ�";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//��ȷ���û���������Ϊ�գ���¼��ʾ�����벻��Ϊ��
	public void test004_LoginRightUsernameAndNullPassword() {
		String username="teachertest02";
		String passWord="";
		String toastMessage = "���벻��Ϊ��";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//������û���������С��3�����룬��¼��ʾ�����볤��Ӧ����3���ַ�
	public void test005_LoginIncorrentUsernameAndShortPassword() {
		String username="123456789";
		String passWord="12";
		String toastMessage = "���볤��Ӧ����3���ַ�";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//������û���������Ϊ�գ���¼��ʾ�����벻��Ϊ��
	public void test006_LoginIncorrentUsernameAndNullPassword() {
		String username="123456789";
		String passWord="";
		String toastMessage = "���벻��Ϊ��";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//������û���������Ĳ��������루��ʽ��ȷ������¼��ʾ���˺Ų�����
	public void test007_LoginWrongUsernameAndIncorrentPassword() {
		String username="teachertest111";
		String passWord="jiaoyu1212";
		String toastMessage = "�˺Ų�����";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	

	//������û��������˵����룬��¼��ʾ���˺Ų�����
	public void test008_LoginWrongUsernameAndOtherPassword() {
		String username="teachertest111";
		String passWord="jiaoyu";
		String toastMessage = "�˺Ų�����";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//�û���������Ϊ�գ���¼��ʾ���˺Ų���Ϊ��
	public void test009_LoginNullUsernameAndPassword() {
		String username="";
		String passWord="";
		String toastMessage = "�˺Ų���Ϊ��";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//�û���Ϊ�ա�������󣬵�¼��ʾ���˺Ų���Ϊ��
	public void test010_LoginNullUsernameAndIncorrentPassword() {
		String username="";
		String passWord="123";
		String toastMessage = "�˺Ų���Ϊ��";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}

	//�û���Ϊ�ա����˵����룬��¼��ʾ���˺Ų���Ϊ��
	public void test011_LoginNullUsernameAndOtherPassword() {
		String username="";
		String passWord="jiaoyu";
		String toastMessage = "�˺Ų���Ϊ��";
		
		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}

	//�û���Ϊ�ա�����С��3�����룬��¼��ʾ���˺Ų���Ϊ��
	public void test012_LoginNullUsernameAndShortPassword() {
		String username="";
		String passWord="1";
		String toastMessage = "�˺Ų���Ϊ��";
		
		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	
	
	/************************************************************************/
	
	/*
	 * �޸�syncExercises����ġ��γ̡��͡��½ڡ�����
	 * ���꼶���͡�ѧ�ơ��Ǳ��������Ҫ����
	 */

	//ͬ����ϰ
	public void test013_syncExercises() {
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "���꼶";
		String subject = "��ѧ";
		String textBook = "���꼶��ѧ�²�";
		String chapter = "�����ռ�����";
		
		RT.checkLogin(solo,username,passWord);
		//�ӵ�2��������ʼ�������ǣ��꼶��ѧ�ơ��γ̡��½ڣ��꼶��ѧ�ƷǱ��
		RT.syncExercises(solo,grade,subject,textBook,chapter);
		RT.answer(solo);
		RT.loginout(solo);
	}	

	/************************************************************************/
	/*
	 * �޸�specialExercises���֪ʶ�㣨֧��֪ʶ������Ч�������10��������10���ⲻ��ִ��
	 * ���꼶���͡�ѧ�ơ��Ǳ��������Ҫ����
	 */	

	//ר����ϰ
	public void test014_specialExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String knowledge_point = "�����볣���߼�����";
		
		RT.checkLogin(solo,username,passWord);
		//�ӵ�2��������ʼ�������ǣ��꼶��ѧ�ƣ�֪ʶ�㣨�꼶��ѧ�ƷǱ��
		RT.specialExercises(solo, grade,subject,knowledge_point);
		RT.answer(solo);
		RT.loginout(solo);
	}

	/************************************************************************/
	/*
	 * �޸�examExercises���һ���������Ծ����ơ���������ɶ�Ӧ�Ծ�Ĵ��⣨������ĸ����д��⣩
	 * ���꼶���͡�ѧ�ơ��Ǳ��������Ҫ����
	 */	

	//����ѵ��
	public void test015_examExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "����";
		String subject = "����";
		String paperName = "2014�㽭�����п���������";
		String key = "2014�㽭�����п�����";  //�Ǳ���
		String checkMessage ="2014�㽭�����п���������";
		
		RT.checkLogin(solo,username,passWord);
		//�ӵ�2��������ʼ�������ǣ��꼶��ѧ�ƣ��Ծ����ƣ��꼶��ѧ�ƷǱ��
		RT.examExercises(solo,grade, subject, paperName,key,checkMessage);
		RT.answer(solo);
		RT.loginout(solo);
	}
	

	/************************************************************************/
	//ǿ��ѵ��
	/*
	 * intensifyExercisesOne�����ڽ���һ��֪ʶ�������
	 * intensifyExercisesTwoe�����ڽ������֪ʶ�������
	 * intensifyExercisesThree�����ڽ�������֪ʶ�������
	 * ÿ������������ֻ������һ�����Գ���
	 */
 
	public void test016_intensifyExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "����";
		String knowledge_point1 = "������ϸ��";
		String knowledge_point2 = "ϸ���ķ������";
		String knowledge_point3 = "ϸ���е�Ԫ�غͻ�����";
		int n = 5;//n�����������Ĳ�����
		
		RT.checkLogin(solo,username,passWord);
		//�ӵ�2��������ʼ�������ǣ��꼶��ѧ�ƣ�1��2��3��֪ʶ�㣨�꼶��ѧ�ƷǱ��		
		//RT.intensifyExercisesOne(solo,grade,subject, knowledge_point1);
		//RT.intensifyExercisesTwo(solo,grade,subject, knowledge_point1, knowledge_point2);
		RT.intensifyExercisesThree(solo,grade,subject, knowledge_point1, knowledge_point2, knowledge_point3);
		RT.addQuestions(solo,n);
		RT.answer(solo);
		RT.loginout(solo);
	}
	
	/************************************************************************/
	/*
	 * openExamRecord��openExamination��deleteExamRecord
	 * 3�����������ĸ��������ӵ�2��������ʼ�����δ������꼶������ѧ�ơ������Ծ����ơ�
	 */	

	//���Լ�¼�����򿪲��Խ��
	public void test017001_openExamRecord(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "�����볣���߼�����-ר��ѵ��";
		
		RT.checkLogin(solo,username,passWord);
		RT2.openExamRecord(solo, grade, subject, paperName);
		RT.loginout(solo);
	}
	//���Լ�¼�����鿴ԭ��
	public void test017002_openExamination(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "�����볣���߼�����-ר��ѵ��";
		
		RT.checkLogin(solo,username,passWord);
		RT2.openExamination(solo, grade, subject, paperName);
	}
	//���Լ�¼����ɾ�����Լ�¼
	public void test017003_deleteExamRecord(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "���ȹ�ϵ�벻��ʽ �� ������ϰ";
		int n = 1;//n=1ʱִ��ɾ��
		
		RT.checkLogin(solo,username,passWord);
		//���һ��������1��ʱ��ִ��ɾ��������int������ȡ��ɾ��
		RT2.deleteExamRecord(solo,grade, subject, paperName,n);
		RT.loginout(solo);	
	}

	/************************************************************************/
	/*
	 * openPaper��deletePaper
	 * 2�����������ĸ��������ӵ�2��������ʼ�����δ������꼶������ѧ�ơ������Ծ����ơ�
	 */	

	//�ҵ���������Ծ�
	public void test018001_openPaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "ϸ���е�Ԫ�غͻ����� ������ϰ";
		
		RT.checkLogin(solo,username,passWord);
		EP.openPaper(solo, grade, subject, paperName);
		RT.loginout(solo);	
	}
	//�ҵ������ɾ���Ծ�
	public void test018002_deletePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "ϸ���е�Ԫ�غͻ����� ������ϰ";
		int n = 1;//n=1ʱִ��ɾ��
		
		RT.checkLogin(solo,username,passWord);
		//���һ��������1��ʱ��ִ��ɾ��������int������ȡ��ɾ��
		EP.deletePaper(solo, grade, subject, paperName, n);
		RT.loginout(solo);	
	}

	/************************************************************************/

	//�ҵ��ղء���������⵽���Կ������Ծ�
	public void test019001_makePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n����������������
		
		RT.checkLogin(solo,username,passWord);
		MC.makePaper(solo, grade, subject,n);
		RT.loginout2(solo);
	}
	//�ҵ��ղء����Ƴ����Կ�
	public void test019002_outOfQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";
		String grade = "";
		String subject = "";
		
		RT.checkLogin(solo,username,passWord);
		MC.outOfQuestions(solo, grade, subject);
		RT.loginout(solo);	
	}
	//�ҵ��ղء���ȡ�������ղ�
	public void test019003_cancleTestQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";
		String grade = "";
		String subject = "";
		int n = 1;//n����ȡ�������ղص�����
		int m = 1;//m=1ִ��ȡ�������ղ�
		
		RT.checkLogin(solo,username,passWord);
		MC.cancleTestQuestions(solo, grade, subject, n, m);
		RT.loginout(solo);
	}

	/************************************************************************/

	//�ҵĴ��Ȿ����������⵽���Կ������Ծ�
	public void test020001_makePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n�����������Ĵ���
		
		RT.checkLogin(solo,username,passWord);
		WB.makePaper(solo, grade, subject, n);	
	}
	//�ҵĴ��Ȿ�����Ƴ����Կ�
	public void test020002_outOfQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		
		RT.checkLogin(solo,username,passWord);
		WB.outOfQuestions(solo, grade, subject);	
	}
	//�ҵĴ��Ȿ�����Ƴ����Ȿ
	public void test020003_outOfWrongBook(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n=1ʱִ��ȷ���Ƴ�
		
		RT.checkLogin(solo,username,passWord);
		WB.outOfWrongBook(solo, grade, subject, n);
	}

	/************************************************************************/

	//�˺�����-�ǳ�
	public void test021001_nickname(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String nickname = "ʨ��ͷ";
		String checkMessage = "�޸��û���Ϣ�ɹ�";
		
		RT.checkLogin(solo,username,passWord);	
		//�ɹ��޸���ʾ���޸��û���Ϣ�ɹ�/������ʾ���ǳƲ���Ϊ��
		AS.nickname(solo, nickname, checkMessage);
	}
	//�˺�����-����ǩ��
	public void test021002_signature(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String signature = "ǩ��";
		String checkMessage = "�޸��û���Ϣ�ɹ�";
		
		RT.checkLogin(solo,username,passWord);	
		AS.signature(solo, signature, checkMessage);
	}
	//�˺�����-�޸�����
	public void test021003_changePassWord(){
		String username="teachertest02";
		String oldPassWord="jiaoyu";
		String newPassWord="jiaoyu";
		String surePassWord="jiaoyu";
		String checkMessage="�޸��û�����ɹ�";
		
		RT.checkLogin(solo,username,oldPassWord);
		AS.changePassWord(solo, oldPassWord, newPassWord, surePassWord, checkMessage);
		RT.login(solo,username,newPassWord);
		RT.loginout(solo);
	}
	//�˺�����-ѧ���͵Ǽ�
	public void test021004_level(){
		String username = "teachertest02";
		String passWord = "jiaoyu";		
		String checkMessage = "ʲô��ѧ��";
		
		RT.checkLogin(solo,username,passWord);
		AS.level(solo, checkMessage);	
	}

	//�˺�����-���ڲ�������
	public void test021005_about(){
		String username = "teachertest02";
		String passWord = "jiaoyu";		
		int n = 2; 		
		String opinion = "�����������"; 
		String phone = "13344568874";
		
		RT.checkLogin(solo,username,passWord);
		switch(n){
		case 1: AS.about(solo, "����Э��","�㶫��������Ƽ�","","");
		break; 
		case 2: AS.about(solo, "�������","��л���ṩ�ķ���",opinion,phone);
		break; 
		case 3: AS.about(solo, "�汾����","��������","","");	
		break; 
		}
	}

}
