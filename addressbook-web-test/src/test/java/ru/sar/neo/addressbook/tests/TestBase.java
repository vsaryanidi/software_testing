package ru.sar.neo.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.sar.neo.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import static org.testng.Assert.assertTrue;

public class TestBase{

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
  app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
  app.stop();
  }

}
