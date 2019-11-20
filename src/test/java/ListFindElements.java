import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ListFindElements {

  @Test
  public void verifyEachElementInTheList() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get("https://deens.com/");
    driver.findElement(By.cssSelector("a[href*='fun-getaway-with-friends-in-london']")).click();

    List<WebElement> allActivities = driver.findElements(By.cssSelector("[class*='Itinerary__ServiceTitle'] a"));
    Assert.assertTrue(allActivities.size() > 0);

    System.out.println("allActivities text "+allActivities.get(0).getText());
    System.out.println("allActivities.get(0) "+allActivities.get(0));

    Thread.sleep(6000);
    allActivities.get(0).click();
    Thread.sleep(6000);
//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    String header = driver.findElement(By.cssSelector("[class*='Service__HeaderWrap'] h2")).getText();
    System.out.println("header "+header);

    Assert.assertEquals(allActivities.get(0).getText(),header);

//    for (int i = 0; i < allActivities.size(); i++) {
//      allActivities.get(i).click();
//      driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//      String header = driver.findElement(By.cssSelector("[class*='Service__HeaderWrap'] h2")).getText();
//      System.out.println("allActivities text "+allActivities.get(i).getText());
//      System.out.println("header "+header);
//      Assert.assertEquals(allActivities.get(i).getText(),header);
//    }
    System.out.println("allTrips"+ allActivities);

    //    Assertion
//    Assert.assertEquals(allActivities.size(), 11);
    driver.quit();
  }


//  @Test
//  public void verifyEachElementInTheList() throws InterruptedException {
//    WebDriver driver = new ChromeDriver();
//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    driver.manage().window().maximize();
//    driver.get("https://www.sourcedadventures.com/category/new-york-adventures/");
//
//    driver.findElement(By.cssSelector("[class='privy-dismiss-content']")).click();
//
//    List<WebElement> allActivities = driver.findElements(By.cssSelector("[class*='card-adventures'] h2 a:last-child"));
//    Assert.assertTrue(allActivities.size() > 0);
//
//    System.out.println("allActivities text "+allActivities.get(0).getText());
//    allActivities.get(0).click();
//    Thread.sleep(6000);
//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//    String header = driver.findElement(By.cssSelector("[class='content-container'] h1")).getText();
//    System.out.println("header "+header);
//    Assert.assertEquals(allActivities.get(0).getText(),header);
//
////    for (int i = 0; i < allActivities.size(); i++) {
////      allActivities.get(i).click();
////      driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
////      String header = driver.findElement(By.cssSelector("[class*='Service__HeaderWrap'] h2")).getText();
////      System.out.println("allActivities text "+allActivities.get(i).getText());
////      System.out.println("header "+header);
////      Assert.assertEquals(allActivities.get(i).getText(),header);
////    }
//    System.out.println("allTrips"+ allActivities);
//    System.out.println("allTrips.size"+ allActivities.size());
////    Assert.assertEquals(allActivities.size(), 11);
////    driver.quit();
//  }


}
