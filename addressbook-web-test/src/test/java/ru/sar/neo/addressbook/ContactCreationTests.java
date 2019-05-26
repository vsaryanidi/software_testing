package ru.sar.neo.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactCreationTests() throws Exception {
    wd.get("http://localhost/addressbook/index.php");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("add new")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys("Valerie");
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys("Saryanidi");
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys("Germany, Munich");
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys("+7 987 333 33 33");
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys("vsaryanidi@gmail.com");
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    wd.findElement(By.linkText("Logout")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
