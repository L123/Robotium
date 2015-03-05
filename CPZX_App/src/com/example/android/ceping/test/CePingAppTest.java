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
		
	//正确的用户名和密码，成功登录
	public void test001_LoginRightUsernameAndPassword() {	
		String username="teachertest02";
		String passWord="jiaoyu";
		String toastMessage = "七天诊断";

		RT.login2(solo,username,passWord);
		RT.checkMessage(solo,toastMessage);
		RT.loginout(solo);
	}	
		
	//正确的用户名、错误的密码，登录提示：密码错误
	public void test002_LoginRightUsernameAndWrongPassword() {
		String username="teachertest02";
		String passWord="123";
		String toastMessage = "密码错误";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	

	//正确的用户名、长度小于3的密码，登录提示：密码错误
	public void test003_LoginRightUsernameAndShortPassword() {
		String username="teachertest02";
		String passWord="12";
		String toastMessage = "密码长度应大于3个字符";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//正确的用户名、密码为空，登录提示：密码不能为空
	public void test004_LoginRightUsernameAndNullPassword() {
		String username="teachertest02";
		String passWord="";
		String toastMessage = "密码不能为空";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//错误的用户名、长度小于3的密码，登录提示：密码长度应大于3个字符
	public void test005_LoginIncorrentUsernameAndShortPassword() {
		String username="123456789";
		String passWord="12";
		String toastMessage = "密码长度应大于3个字符";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//错误的用户名、密码为空，登录提示：密码不能为空
	public void test006_LoginIncorrentUsernameAndNullPassword() {
		String username="123456789";
		String passWord="";
		String toastMessage = "密码不能为空";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//错误的用户名、错误的不存在密码（格式正确），登录提示：账号不存在
	public void test007_LoginWrongUsernameAndIncorrentPassword() {
		String username="teachertest111";
		String passWord="jiaoyu1212";
		String toastMessage = "账号不存在";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	

	//错误的用户名、他人的密码，登录提示：账号不存在
	public void test008_LoginWrongUsernameAndOtherPassword() {
		String username="teachertest111";
		String passWord="jiaoyu";
		String toastMessage = "账号不存在";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
		
	//用户名和密码为空，登录提示：账号不能为空
	public void test009_LoginNullUsernameAndPassword() {
		String username="";
		String passWord="";
		String toastMessage = "账号不能为空";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}
			
	//用户名为空、密码错误，登录提示：账号不能为空
	public void test010_LoginNullUsernameAndIncorrentPassword() {
		String username="";
		String passWord="123";
		String toastMessage = "账号不能为空";

		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}

	//用户名为空、他人的密码，登录提示：账号不能为空
	public void test011_LoginNullUsernameAndOtherPassword() {
		String username="";
		String passWord="jiaoyu";
		String toastMessage = "账号不能为空";
		
		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}

	//用户名为空、长度小于3的密码，登录提示：账号不能为空
	public void test012_LoginNullUsernameAndShortPassword() {
		String username="";
		String passWord="1";
		String toastMessage = "账号不能为空";
		
		RT.login2(solo,username,passWord);
		RT.checkDynamicMessage(solo,toastMessage);
	}	
	
	/************************************************************************/
	
	/*
	 * 修改syncExercises后面的【课程】和【章节】即可
	 * 【年级】和【学科】非必填，根据需要设置
	 */

	//同步练习
	public void test013_syncExercises() {
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "二年级";
		String subject = "数学";
		String textBook = "二年级数学下册";
		String chapter = "数据收集整理";
		
		RT.checkLogin(solo,username,passWord);
		//从第2个参数开始，依次是：年级、学科、课程、章节（年级和学科非必填）
		RT.syncExercises(solo,grade,subject,textBook,chapter);
		RT.answer(solo);
		RT.loginout(solo);
	}	

	/************************************************************************/
	/*
	 * 修改specialExercises后的知识点（支持知识点下有效试题大于10），少于10道题不能执行
	 * 【年级】和【学科】非必填，根据需要设置
	 */	

	//专项练习
	public void test014_specialExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String knowledge_point = "集合与常用逻辑用语";
		
		RT.checkLogin(solo,username,passWord);
		//从第2个参数开始，依次是：年级、学科，知识点（年级和学科非必填）
		RT.specialExercises(solo, grade,subject,knowledge_point);
		RT.answer(solo);
		RT.loginout(solo);
	}

	/************************************************************************/
	/*
	 * 修改examExercises最后一个参数【试卷名称】，即可完成对应试卷的答题（不对子母题进行答题）
	 * 【年级】和【学科】非必填，根据需要设置
	 */	

	//考试训练
	public void test015_examExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "初中";
		String subject = "语文";
		String paperName = "2014浙江湖州中考语文试题";
		String key = "2014浙江湖州中考语文";  //非必填
		String checkMessage ="2014浙江湖州中考语文试题";
		
		RT.checkLogin(solo,username,passWord);
		//从第2个参数开始，依次是：年级、学科，试卷名称（年级和学科非必填）
		RT.examExercises(solo,grade, subject, paperName,key,checkMessage);
		RT.answer(solo);
		RT.loginout(solo);
	}
	

	/************************************************************************/
	//强化训练
	/*
	 * intensifyExercisesOne适用于进入一级知识点试题库
	 * intensifyExercisesTwoe适用于进入二级知识点试题库
	 * intensifyExercisesThree适用于进入三级知识点试题库
	 * 每个测试用例中只允许存放一个测试场景
	 */
 
	public void test016_intensifyExercises(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "生物";
		String knowledge_point1 = "分子与细胞";
		String knowledge_point2 = "细胞的分子组成";
		String knowledge_point3 = "细胞中的元素和化合物";
		int n = 5;//n代表添加试题的操作数
		
		RT.checkLogin(solo,username,passWord);
		//从第2个参数开始，依次是：年级、学科，1、2、3级知识点（年级和学科非必填）		
		//RT.intensifyExercisesOne(solo,grade,subject, knowledge_point1);
		//RT.intensifyExercisesTwo(solo,grade,subject, knowledge_point1, knowledge_point2);
		RT.intensifyExercisesThree(solo,grade,subject, knowledge_point1, knowledge_point2, knowledge_point3);
		RT.addQuestions(solo,n);
		RT.answer(solo);
		RT.loginout(solo);
	}
	
	/************************************************************************/
	/*
	 * openExamRecord、openExamination、deleteExamRecord
	 * 3个方法都有四个参数，从第2个参数开始，依次代表：【年级】、【学科】、【试卷名称】
	 */	

	//测试记录——打开测试结果
	public void test017001_openExamRecord(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "集合与常用逻辑用语-专项训练";
		
		RT.checkLogin(solo,username,passWord);
		RT2.openExamRecord(solo, grade, subject, paperName);
		RT.loginout(solo);
	}
	//测试记录——查看原题
	public void test017002_openExamination(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "集合与常用逻辑用语-专项训练";
		
		RT.checkLogin(solo,username,passWord);
		RT2.openExamination(solo, grade, subject, paperName);
	}
	//测试记录——删除测试记录
	public void test017003_deleteExamRecord(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "不等关系与不等式 等 自主练习";
		int n = 1;//n=1时执行删除
		
		RT.checkLogin(solo,username,passWord);
		//最后一个参数是1的时候执行删除，其他int参数则取消删除
		RT2.deleteExamRecord(solo,grade, subject, paperName,n);
		RT.loginout(solo);	
	}

	/************************************************************************/
	/*
	 * openPaper、deletePaper
	 * 2个方法都有四个参数，从第2个参数开始，依次代表：【年级】、【学科】、【试卷名称】
	 */	

	//我的组卷——打开试卷
	public void test018001_openPaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "细胞中的元素和化合物 自主练习";
		
		RT.checkLogin(solo,username,passWord);
		EP.openPaper(solo, grade, subject, paperName);
		RT.loginout(solo);	
	}
	//我的组卷——删除试卷
	public void test018002_deletePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		String paperName = "细胞中的元素和化合物 自主练习";
		int n = 1;//n=1时执行删除
		
		RT.checkLogin(solo,username,passWord);
		//最后一个参数是1的时候执行删除，其他int参数则取消删除
		EP.deletePaper(solo, grade, subject, paperName, n);
		RT.loginout(solo);	
	}

	/************************************************************************/

	//我的收藏——添加试题到测试库生成试卷
	public void test019001_makePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n代表添加试题的数量
		
		RT.checkLogin(solo,username,passWord);
		MC.makePaper(solo, grade, subject,n);
		RT.loginout2(solo);
	}
	//我的收藏——移出测试库
	public void test019002_outOfQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";
		String grade = "";
		String subject = "";
		
		RT.checkLogin(solo,username,passWord);
		MC.outOfQuestions(solo, grade, subject);
		RT.loginout(solo);	
	}
	//我的收藏——取消试题收藏
	public void test019003_cancleTestQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";
		String grade = "";
		String subject = "";
		int n = 1;//n代表取消试题收藏的数量
		int m = 1;//m=1执行取消试题收藏
		
		RT.checkLogin(solo,username,passWord);
		MC.cancleTestQuestions(solo, grade, subject, n, m);
		RT.loginout(solo);
	}

	/************************************************************************/

	//我的错题本——添加试题到测试库生成试卷
	public void test020001_makePaper(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n代表添加试题的次数
		
		RT.checkLogin(solo,username,passWord);
		WB.makePaper(solo, grade, subject, n);	
	}
	//我的错题本——移出测试库
	public void test020002_outOfQuestions(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		
		RT.checkLogin(solo,username,passWord);
		WB.outOfQuestions(solo, grade, subject);	
	}
	//我的错题本——移出错题本
	public void test020003_outOfWrongBook(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String grade = "";
		String subject = "";
		int n = 1;//n=1时执行确认移出
		
		RT.checkLogin(solo,username,passWord);
		WB.outOfWrongBook(solo, grade, subject, n);
	}

	/************************************************************************/

	//账号设置-昵称
	public void test021001_nickname(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String nickname = "狮子头";
		String checkMessage = "修改用户信息成功";
		
		RT.checkLogin(solo,username,passWord);	
		//成功修改提示：修改用户信息成功/不填提示：昵称不能为空
		AS.nickname(solo, nickname, checkMessage);
	}
	//账号设置-个性签名
	public void test021002_signature(){
		String username="teachertest02";
		String passWord="jiaoyu";		
		String signature = "签名";
		String checkMessage = "修改用户信息成功";
		
		RT.checkLogin(solo,username,passWord);	
		AS.signature(solo, signature, checkMessage);
	}
	//账号设置-修改密码
	public void test021003_changePassWord(){
		String username="teachertest02";
		String oldPassWord="jiaoyu";
		String newPassWord="jiaoyu";
		String surePassWord="jiaoyu";
		String checkMessage="修改用户密码成功";
		
		RT.checkLogin(solo,username,oldPassWord);
		AS.changePassWord(solo, oldPassWord, newPassWord, surePassWord, checkMessage);
		RT.login(solo,username,newPassWord);
		RT.loginout(solo);
	}
	//账号设置-学力和登记
	public void test021004_level(){
		String username = "teachertest02";
		String passWord = "jiaoyu";		
		String checkMessage = "什么是学力";
		
		RT.checkLogin(solo,username,passWord);
		AS.level(solo, checkMessage);	
	}

	//账号设置-关于测评中心
	public void test021005_about(){
		String username = "teachertest02";
		String passWord = "jiaoyu";		
		int n = 2; 		
		String opinion = "输入您的意见"; 
		String phone = "13344568874";
		
		RT.checkLogin(solo,username,passWord);
		switch(n){
		case 1: AS.about(solo, "服务协议","广东东田数码科技","","");
		break; 
		case 2: AS.about(solo, "意见反馈","感谢您提供的反馈",opinion,phone);
		break; 
		case 3: AS.about(solo, "版本更新","抢先下载","","");	
		break; 
		}
	}

}
