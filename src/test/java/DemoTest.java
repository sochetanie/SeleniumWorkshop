import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DemoTest {
  WebDriver driver;


  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
  }

  @AfterMethod
  public void close() {
    driver.close();
    driver.quit();
  }


  @Test
  public void incorrectPassAndEmailTest() {
    driver.get("https://deens-master.now.sh");

    driver.findElement(By.cssSelector("a[href*='login']")).click();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    WebElement email = driver.findElement(By.cssSelector("#email"));
    email.sendKeys("valery@gmail.com");
    WebElement pass = driver.findElement(By.cssSelector("#password"));
    pass.sendKeys("Testing123");
    driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

    String errorMessageText = driver.findElement(By.cssSelector(".ui.error.message p")).getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Wrong email or password.");
  }

  @Test
  public void loginEmptyParametrsTest() {
    driver.get("https://deens-master.now.sh/login");

    driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();
    String errorMessageText = driver.findElement(By.cssSelector(".ui.error.message p")).getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Empty email or password");
  }

  @Test
  public void loginSuccessTest() throws InterruptedException {
//    driver.get("https://deens-master.now.sh/login");
    driver.get("https://deens.com/login");

    driver.findElement(By.cssSelector("#email")).sendKeys("valery.kells0202@gmail.com");
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");
    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

//    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    Thread.sleep(5000);
    driver.navigate().refresh();

//    Assertion
    Assert.assertTrue(driver.findElement(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']")).isDisplayed());
  }

  @Test
  public void signUpSuccessTest() throws InterruptedException {
    driver.get("https://deens.com/register");

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    driver.findElement(By.cssSelector("#username")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#email")).sendKeys("username" + randomInt + "@gmail.com");
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");
    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Thread.sleep(5000);
    WebElement imageDiv = driver.findElement(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']"));

    String img_text = imageDiv.findElement(By.tagName("img")).getAttribute("alt");

//    Assertion
    Assert.assertEquals(img_text, "user avatar");
  }

  @Test
  public void signUpInvalidEmailTest() {
    driver.get("https://deens-master.now.sh/register");

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);

    driver.findElement(By.cssSelector("#username")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#email")).sendKeys("username" + randomInt);
    driver.findElement(By.cssSelector("#password")).sendKeys("Testing12345");

    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

    String errorMessageText = driver.findElement(By.cssSelector(".ui.message")).getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Please enter a valid email address");
  }

  @Test
  public void signUpEmptyCredentialsTest() {
    driver.get("https://deens-master.now.sh/register");

    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();
    String errorMessageText = driver.findElement(By.cssSelector(".ui.message")).getText();

//    Assertion
    Assert.assertEquals(errorMessageText, "Password must be at least 8 characters long");
  }


}