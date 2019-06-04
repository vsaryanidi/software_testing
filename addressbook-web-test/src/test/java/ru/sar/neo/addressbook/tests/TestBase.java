package ru.sar.neo.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.sar.neo.addressbook.appmanager.ApplicationManager;

public class TestBase {

  public final ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
