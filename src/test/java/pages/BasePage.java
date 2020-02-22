package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  WebDriver driver;
  WebDriverWait wait;

  public BasePage(WebDriver myDriver) {
    this.driver = myDriver;
    wait = new WebDriverWait(driver, 10);
  }

  protected WebElement findElementByCssSelector(String cssLocator) {
    return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssLocator)));
  }

  protected WebElement findElements(String cssLocator) {
    return (WebElement) driver.findElements(By.cssSelector(cssLocator));
  }

  protected WebElement findElementByXPath(String xpath) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
  }

}
