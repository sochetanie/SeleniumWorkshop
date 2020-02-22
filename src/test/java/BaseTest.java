import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

}
