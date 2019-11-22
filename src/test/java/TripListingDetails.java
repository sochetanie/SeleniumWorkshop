import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TripListingDetails extends BaseTest {

  @Test
  public void verifyCountOfDays() {

    driver.get("https://deens.com/");
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("a[href*='fun-getaway-with-friends-in-london']")).click();

    List<WebElement> allTrips = driver.findElements(By.cssSelector("[class*='Itinerary__DayTitle']"));

    for (int i = 0; i < allTrips.size(); i++) {
      System.out.println(allTrips.get(i).getText());
      System.out.println(allTrips.get(i));
    }
    //    Assertion
    Assert.assertEquals(allTrips.size(), 5);
  }
}
