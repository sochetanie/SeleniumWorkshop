import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class DemoTest {

  @Test
  public void incorrectPassAndEmail() {
//    Test Case logic
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("https://deens-master.now.sh/login");

    WebElement email = driver.findElement(By.cssSelector("#email"));
    email.sendKeys("valery@gmail.com");
    WebElement pass = driver.findElement(By.cssSelector("#password"));
    pass.sendKeys("Testing123");
    driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();
    WebElement errorMessage = driver.findElement(By.cssSelector(".ui.error.message p"));
    String errorMessageText = errorMessage.getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Wrong email or password.");
    driver.quit();
  }

}
