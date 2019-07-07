package ru.sar.neo.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.sar.neo.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import static org.testng.Assert.assertTrue;

public class TestBase{

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
  app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
  app.stop();
  }

}
