package TestCases;

import org.testng.annotations.Test;

import PageObjects.login;
import TestBase.zighome;

public class loginerror extends zighome{
	static login use;
	
	@Test(priority = 5)
	public void user_reg() throws InterruptedException {
		use=new login(driver);
		use.signup();
		logger.info("sign through google failed because id is wrong");
		
	}

}
