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
	 * ���췽������ robotium��ʵ�� 
	 */
	public InputTest() {
		super("com.huawei", KeyboardActivity2.class);

	}
	
	//����֮ǰִ�еĴ��� 
	public void setUp() throws Exception {
		//���������ԵĻ����� 
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
	//������ɺ�ִ�еĴ��� 
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
