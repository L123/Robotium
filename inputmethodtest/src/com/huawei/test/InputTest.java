package com.huawei.test;

import com.huawei.KeyboardActivity2;
import com.jayway.android.robotium.solo.Solo;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.Smoke;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class InputTest extends ActivityInstrumentationTestCase2<KeyboardActivity2>{

	private Solo solo;
	EditText et;
	/*
	 * 构造方法创建 robotium的实例 
	 */
	public InputTest() {
		super("com.huawei", KeyboardActivity2.class);

	}
	
	//测试之前执行的代码 
	public void setUp() throws Exception {
		//创建出测试的机器人 
		solo = new Solo(getInstrumentation(), getActivity());
		InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

		assertTrue(imm.isActive());

		
		et=(EditText) this.getActivity().findViewById(R.id.editText);
	}

	@Smoke
	public void testEntryBlackName() throws Exception {
		
		solo.clickOnEditText(0);
		solo.enterText(0, "lideguo ni hao a ");
		solo.clickLongOnView(et);
	}
	//测试完成后执行的代码 
	@Override
	public void tearDown() throws Exception {
		try {
			//Robotium will finish all the activities that have been opened
			solo.finalize(); 	
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	} 
	
	
}
