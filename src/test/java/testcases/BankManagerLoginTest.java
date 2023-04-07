package testcases;

import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest {

	@Test
	public void loginAsBankManager() throws InterruptedException {
		click("BankManagerBTN_css");
		System.out.println("button clicked");
		
		click("AddCustomerBTN_css");
		type("ACFirstName_css", "Shree");
		type("ACLastName_css", "baghel");
		type("ACPostCode_css", "1234");
		//findElements("ACAddCustomer123_css");
		
		click("ACAddCustomer_css");
	}
	
}
