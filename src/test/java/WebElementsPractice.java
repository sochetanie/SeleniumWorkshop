import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class WebElementsPractice {
  private static WebDriver driver;

  private static void selectOptions() {
    driver = new ChromeDriver();
    driver.get("http://demo.guru99.com/test/newtours/register.php");
    WebElement element = driver.findElement(By.cssSelector("[name=country"));
    Select country = new Select(element);
    country.selectByValue("UNITED STATES");
    WebElement option = country.getFirstSelectedOption();
    System.out.println("option: " + option.getText());

    List<WebElement> options = country.getOptions();
    for (WebElement webElement : options) {
      System.out.println(webElement.getText());
    }

    driver.close();
  }

  private static void checkBoxes() {
    driver = new ChromeDriver();
    driver.get("http://demo.guru99.com/test/radio.html");
    WebElement opt1 = driver.findElement(By.cssSelector("[value='Option 1']"));
    opt1.click();

    WebElement checkbox1 = driver.findElement(By.cssSelector("[value='checkbox1']"));
    WebElement checkbox2 = driver.findElement(By.cssSelector("[value='checkbox2']"));
    checkbox1.click();

    System.out.println("is opt1 selected: " + opt1.isSelected());
    System.out.println("is checkbox 2 selected: " + checkbox2.isSelected());
    driver.close();
  }

  private static void alerts() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver = new ChromeDriver();
    driver.get("http://demo.guru99.com/test/delete_customer.php");
    driver.findElement(By.cssSelector("[value='Submit']")).click();
    driver.switchTo().alert().accept();
    driver.switchTo().alert().accept();

    assertTrue(driver.findElement(By.cssSelector("[value='Submit']")).isDisplayed());

    driver.close();
  }

  public static void main(String[] args) {
    selectOptions();
    checkBoxes();
    alerts();
  }


}
