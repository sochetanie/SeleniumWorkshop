import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ListFindElements extends BaseTest {

  @Test
  public void verifyEachElementInTheList() throws InterruptedException {

    driver.get("https://deens.com/");
    WebElement elem = driver.findElement(By.cssSelector("a[href*='fun-getaway-with-friends-in-london']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
    Thread.sleep(500);
    elem.click();

    List<WebElement> allActivities = driver.findElements(By.cssSelector("[class*='Itinerary__ServiceTitle'] a"));
    Assert.assertTrue(allActivities.size() > 0);

    for (int i = 0; i < allActivities.size(); i++) {
      allActivities = driver.findElements(By.cssSelector("[class*='Itinerary__ServiceTitle'] a"));
      Thread.sleep(6000);

      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allActivities.get(i));
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
      Thread.sleep(1000);

      String titleFromPLP = allActivities.get(i).getText();
      System.out.println("#"+i+".title: " + titleFromPLP);
      allActivities.get(i).click();
      Thread.sleep(6000);

      String header = driver.findElement(By.cssSelector("[class*='Service__HeaderWrap'] h2")).getText();
      System.out.println("#"+i+".header: " + header);
      Assert.assertEquals(titleFromPLP, header);
      driver.navigate().back();
    }
  }

}
