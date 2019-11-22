import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DemoTest extends BaseTest {

  private static By email = By.cssSelector("#email");
  private static By pass = By.cssSelector("#password");
  private static By username = By.cssSelector("#username");
  private static By loginButton = By.cssSelector("[data-testid='loginSubmit']");
  private static By errorMessage = By.cssSelector(".ui.error.message p");

  @Test
  public void incorrectPassAndEmailTest() {
    driver.get("https://deens-master.now.sh");
    driver.findElement(By.cssSelector("a[href*='login']")).click();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.findElement(email).sendKeys("valery@gmail.com");
    driver.findElement(pass).sendKeys("Testing123");
    driver.findElement(loginButton).click();

//    Assertion
    Assert.assertEquals(driver.findElement(errorMessage).getText(), "Wrong email or password.");
  }

  @Test
  public void loginEmptyParametersTest() {
    driver.get("https://deens-master.now.sh/login");
    driver.findElement(loginButton).click();

//    Assertion
    Assert.assertEquals(driver.findElement(errorMessage).getText(), "Empty email or password");
  }

  @Test
  public void loginSuccessTest() throws InterruptedException {
    driver.get("https://deens.com/login");

    driver.findElement(email).sendKeys("valery.kells0202@gmail.com");
    driver.findElement(pass).sendKeys("Testing12345");

    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

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

    driver.findElement(username).sendKeys("username" + randomInt);
    driver.findElement(email).sendKeys("username" + randomInt + "@gmail.com");
    driver.findElement(pass).sendKeys("Testing12345");
    driver.findElement(By.cssSelector(".ui.large.fluid.button.green-btn.pl-btn")).click();

    Thread.sleep(6000);
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

    driver.findElement(username).sendKeys("username" + randomInt);
    driver.findElement(email).sendKeys("username" + randomInt);
    driver.findElement(pass).sendKeys("Testing12345");

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