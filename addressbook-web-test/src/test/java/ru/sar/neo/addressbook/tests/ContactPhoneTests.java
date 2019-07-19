package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions(){

    app.contact().goToHomePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("Valerie")
              .withLastname("Saryanidi").withAddress("Germany, Munich")
              .withHome_phone("+7 987 333 33 33").withMobile_phone("+7(987)333333").withWork_phone("22-55-88")
              .withEmail("vsaryanidi@gmail.com").withGroup("TestGroup"),true);
    }
  }
  
  @Test

  public void testContactPhones() {
    app.contact().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {

    return Arrays.asList(contact.getHome_phone(),contact.getMobile_phone(),contact.getWork_phone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()/]", "");
  }
}
