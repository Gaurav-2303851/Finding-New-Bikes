package TestCases;


import org.testng.annotations.Test;
import PageObjects.Home_Page;
import TestBase.zighome;

public class navigate_to_upcoming_Bikes extends zighome {
	static Home_Page h_page;
	
	@Test(priority = 1)
	public void navigate() throws InterruptedException {
		h_page=new Home_Page(driver);
		h_page.New_Upcoming_Bikes();
		logger.info("hover over new bikes is done");
	}
	
	@Test(priority = 2)
	public void Slect_Honda_Manuf() {
		h_page.Manfu_Drop();
		logger.info("honda as a manfu is selecteed");
	}
	
	@Test(priority = 3)
	public void bike_under_4lakh() throws Exception {
		h_page.viewMoreBikes();
		logger.info("all new bikes are displayed");
		h_page.bikeModels();
		logger.info("all bikes under 4 lakh are shown");
		
	}
}
