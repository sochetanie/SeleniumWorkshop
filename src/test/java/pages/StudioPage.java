package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class StudioPage extends BasePage {

  public StudioPage(WebDriver myDriver) {
    super(myDriver);
  }

  public void openFirstStudio() throws InterruptedException {
    driver.get("https://www.weightwatchers.com/us/find-a-meeting/1180510/ww-studio-flatiron-new-york-ny");
    Thread.sleep(2000);
  }

  public void scrollPage(int num) {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    javascriptExecutor.executeScript("window.scrollBy(0," + num + ")");
  }

  public String getTodayDay() {
    String dayOfTheWeek = LocalDate.now().getDayOfWeek().name().substring(0, 3).toLowerCase();
    return dayOfTheWeek.substring(0, 1).toUpperCase() + dayOfTheWeek.substring(1);
  }

  public void closePopUp() {
    boolean isPopUpPresent = findElementByXPath("//button[contains(text(),'No Thanks')]").isDisplayed();
    if (isPopUpPresent) {
      findElementByXPath("//button[contains(text(),'No Thanks')]").click();
    }
  }

  public String findStudioTitle() throws InterruptedException {
    findElementByCssSelector("#ela-menu-visitor-desktop-supplementa_find-a-studio").click();
    Thread.sleep(2000);
    return driver.getTitle();
  }

  public void clickSearchButton() {
    WebElement searchInput = findElementByXPath("//input[@placeholder='Enter location']");
    searchInput.sendKeys("10011");
    findElementByXPath("//*[@spice='SEARCH_BUTTON']").click();
  }

  public boolean getFirstSearchStudioResult() {
    this.closePopUp();
    List<WebElement> titles = driver.findElements(By.xpath("//*[@class='location__name']"));
    String firstStudioName = titles.get(0).getText();
    System.out.println("Studio: " + titles.get(0).getText());

    List<WebElement> locationDistances = driver.findElements(By.xpath("//*[@class='location__distance']"));
    System.out.println("Distance: " + locationDistances.get(0).getText());
    titles.get(0).click();
    String studioTitle = findElementByXPath("//*[@class='location__top']").getText();
    return firstStudioName.equals(studioTitle);
  }

  public String getLocationsTodayHours() {
    this.closePopUp();
    this.scrollPage(800);
    return findElementByXPath("//div[@class='hours-list-item-wrapper hours-list--currentday']/div/div").getText();
  }

  public HashMap<String, Integer> printMeetings(String weekDay) {
    List<WebElement> schedule = driver.findElements(By.className("schedule-detailed-day-label"));
    HashMap<String, Integer> personAndMeetings = new HashMap<>();
    for (WebElement webElement : schedule) {
      if (webElement.getText().equals(weekDay.toUpperCase())) {
        List<WebElement> whoIsToday = driver.findElements(By.xpath(".//div[contains(text(),'" + weekDay + "') and @class='schedule-detailed-day-label']/following-sibling::div/*/div[@class='schedule-detailed-day-meetings-item-leader']"));
        whoIsToday.forEach(person -> {
          String name = person.getText();
          if (personAndMeetings.containsKey(name)) {
            personAndMeetings.put(name, (personAndMeetings.get(name) + 1));
          } else {
            personAndMeetings.put(name, 1);
          }
        });
      }
    }
    return personAndMeetings;
  }

}
