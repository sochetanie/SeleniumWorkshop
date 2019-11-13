import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DemoTest {

  @Test
  public void incorrectPassAndEmail() {
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

    Assert.assertEquals(errorMessageText, "Wrong email or password.");
    driver.quit();
  }

  @Test
  public void loginEmptyParametrsTest() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://deens-master.now.sh/login");

    WebElement loginButton = driver.findElement(By.cssSelector("[data-testid='loginSubmit']"));
    loginButton.click();
    WebElement errorMessage = driver.findElement(By.cssSelector(".ui.error.message p"));
    String errorMessageText = errorMessage.getText();

    Assert.assertEquals(errorMessageText, "Empty email or password");
    driver.quit();
  }

  @Test
  public void loginSuccessTest() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
//    driver.get("https://deens-master.now.sh/login");
    driver.get("https://deens.com/login");
    driver.findElement(By.cssSelector("#email")).sendKeys("valery.kells0202@gmail.com");
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");
    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

    Thread.sleep(6000);
    driver.navigate().refresh();

//    Assertion
    Assert.assertTrue(driver.findElement(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']")).isDisplayed());
    driver.quit();
  }

  @Test
  public void signUpSuccessTest() throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    driver.get("https://deens.com/register");

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    driver.findElement(By.cssSelector("#username")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#email")).sendKeys("username" + randomInt + "@gmail.com");
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");
    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

    Thread.sleep(5000);

    WebElement imageDiv = driver.findElement(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']"));

    String img_text = imageDiv.findElement(By.tagName("img")).getAttribute("alt");

//    Assertion
    Assert.assertEquals(img_text, "user avatar");
    driver.quit();
  }

  @Test
  public void signUpInvalidEmailTest() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://deens-master.now.sh/register");

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    driver.findElement(By.cssSelector("#username")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#email")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");

    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

    WebElement errorMessage = driver.findElement(By.cssSelector(".ui.message"));
    String errorMessageText = errorMessage.getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Please enter a valid email address");
    driver.quit();
  }

  @Test
  public void signUpEmptyCredentialsTest() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://deens-master.now.sh/register");

    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();
    WebElement errorMessage = driver.findElement(By.cssSelector(".ui.message"));
    String errorMessageText = errorMessage.getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Password must be at least 8 characters long");
    driver.quit();
  }


}
