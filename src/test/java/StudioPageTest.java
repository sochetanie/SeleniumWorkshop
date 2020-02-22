import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StudioPage;

public class StudioPageTest extends BaseTest {

  @Test(priority = 1)
  public void getFirstSearchResult() throws InterruptedException {
    HomePage homePage = new HomePage(driver);
    homePage.openHomepage();
    StudioPage studioPage = new StudioPage(driver);
    Assert.assertEquals(studioPage.findStudioTitle(), "Find WW Studios & Meetings Near You | WW USA");
    studioPage.clickSearchButton();
    Assert.assertTrue(studioPage.getFirstSearchStudioResult());
  }

  @Test(priority = 2)
  public void getLocationsTodayHours() throws InterruptedException {
    StudioPage studioPage = new StudioPage(driver);
    studioPage.openFirstStudio();
    String result = studioPage.getLocationsTodayHours();
    Assert.assertNotNull(result);
  }

  @Test(priority = 3)
  public void getScheduledByPerson() throws InterruptedException {
    StudioPage studioPage = new StudioPage(driver);
    studioPage.openFirstStudio();
    studioPage.scrollPage(500);
    String result = studioPage.printMeetings(studioPage.getTodayDay()).toString().replaceAll("[{}=]", " ").trim();
    Assert.assertNotNull(result);
  }

}
