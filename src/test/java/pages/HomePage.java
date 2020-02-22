package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
  public HomePage(WebDriver myDriver) {
    super(myDriver);
  }

  public void openHomepage() {
    driver.get("https://www.weightwatchers.com/us/");
  }

  public String getTitle() {
    return driver.getTitle();
  }

}
