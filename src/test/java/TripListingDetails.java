import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TripListingDetails {

  @Test
  public void verifyCountOfDays() {
    WebDriver driver = new ChromeDriver();

    driver.get("https://deens.com/");
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("a[href*='fun-getaway-with-friends-in-london']")).click();

//    driver.get("https://deens.com/book/trip/fun-getaway-with-friends-in-london-in-greater-london-county_5be9ecaa7a5b0d2bc5980e0b"); // doesn't work

    List<WebElement> allTrips = driver.findElements(By.cssSelector("[class*='Itinerary__DayTitle']"));

    for (int i = 0; i < allTrips.size(); i++) {
      System.out.println(allTrips.get(i).getText());
      System.out.println(allTrips.get(i));
    }
    System.out.println("allTrips"+ allTrips);

    //    Assertion
    Assert.assertEquals(allTrips.size(), 5);
    driver.quit();
  }
}
