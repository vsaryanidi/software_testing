 package ru.sar.neo.addressbook.appmanager;


 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.Select;
 import org.testng.Assert;
 import ru.sar.neo.addressbook.model.ContactData;
 import ru.sar.neo.addressbook.model.Contacts;

 import java.util.List;


public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  private boolean acceptNextAlert = true;

  public void goToHomePage(){ click(By.linkText("home"));}

  public void confirmDeletionContact() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void gotoContactAddPage() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome_phone());
    type(By.name("mobile"), contactData.getMobile_phone());
    type(By.name("work"), contactData.getWork_phone());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void submitContactModification() {
    click(By.name("update"));
  }
  private Contacts contactCache = null;

  public void create(ContactData contact, boolean b) {
    gotoContactAddPage();
    fillContactForm(contact,true);
    submitContactCreation();
    contactCache = null;
    goToHomePage();

  }
  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact,false);
    submitContactModification();
    contactCache = null;
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    confirmDeletionContact();
    contactCache = null;
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {

    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    Contacts contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

    for (WebElement element: elements) {

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
      contactCache.add(new ContactData()
              .withId(id).withFirstname(firstname).withLastname(lastname)
              .withHome_phone(phones[0]).withMobile_phone(phones[1]).withWork_phone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      wd.navigate().back();
      return new ContactData()
              .withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
              .withHome_phone(home).withMobile_phone(mobile).withWork_phone(work);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  }

