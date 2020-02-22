import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

  @Test
  public void homePageTitle() {
    HomePage homePage = new HomePage(driver);
    homePage.openHomepage();
    String homepageTitle = homePage.getTitle();
    Assert.assertEquals(homepageTitle, "WW (Weight Watchers): Weight Loss & Wellness Help");
  }
}
