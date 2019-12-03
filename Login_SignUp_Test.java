import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Login_SignUp_Test extends BaseTest {

  private static By email = By.cssSelector("#email");
  private static By pass = By.cssSelector("#password");
  private static By username = By.cssSelector("#username");

  private void clickLoginButton() {
    WebElement loginButton = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='loginSubmit']")));
    loginButton.click();
  }

  private String getErrorMessage() {
    WebElement errorMessage = findElementOnThePage(".ui.error.message p");
    return errorMessage.getText();
  }

  private WebElement findElementOnThePage(String cssLocator) {
    return driver.findElement(By.cssSelector(cssLocator));
  }


  @Test
  public void incorrectPassAndEmailTest() {
    driver.get("https://deens-master.now.sh");
    findElementOnThePage("a[href*='login']").click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    driver.findElement(email).sendKeys("valery@gmail.com");
    driver.findElement(pass).sendKeys("Testing123");
    clickLoginButton();

    Assert.assertEquals(getErrorMessage(), "Wrong email or password.");
  }

  @Test
  public void loginEmptyParametersTest() {
    driver.get("https://deens-master.now.sh/login");
    clickLoginButton();
    Assert.assertEquals(getErrorMessage(), "Empty email or password");
  }

  @Test
  public void loginSuccessTest() {
    driver.get("https://deens.com/login");
    driver.findElement(email).sendKeys("valery.kells0202@gmail.com");
    driver.findElement(pass).sendKeys("Testing12345");
    findElementOnThePage(".ui.large.fluid.button.green-btn.pl-btn").click();

    WebElement userAvatar = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']")));

    Assert.assertTrue(userAvatar.isDisplayed());
  }

  @Test
  public void signUpSuccessTest() {
//    driver.get("https://deens-master.now.sh/register");
    driver.get("https://deens.com/register");

    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(3000);

    driver.findElement(username).sendKeys("username" + randomInt);
    driver.findElement(email).sendKeys("username" + randomInt + "@gmail.com");
    driver.findElement(pass).sendKeys("Testing12345");
    findElementOnThePage(".ui.large.fluid.button.green-btn.pl-btn").click();

    WebElement userAvatar = driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']")));
    String img_text = userAvatar.findElement(By.tagName("img")).getAttribute("alt");

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

    Assert.assertEquals(errorMessageText, "Please enter a valid email address");
  }

  @Test
  public void signUpEmptyCredentialsTest() {
    driver.get("https://deens-master.now.sh/register");

    findElementOnThePage(".ui.large.fluid.button.green-btn.pl-btn").click();
    String errorMessageText = findElementOnThePage(".ui.message").getText();

    Assert.assertEquals(errorMessageText, "Password must be at least 8 characters long");
  }


}