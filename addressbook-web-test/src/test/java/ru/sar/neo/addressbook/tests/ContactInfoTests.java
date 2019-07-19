package ru.sar.neo.addressbook.tests;

import org.testng.annotations.*;
import ru.sar.neo.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions(){

    app.contact().goToHomePage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("Valerie")
              .withLastname("Saryanidi").withAddress("Germany, Munich")
              .withHome_phone("+7 987 333 33 33").withMobile_phone("+7(987)333333").withWork_phone("22-55-88")
              .withEmail("vsaryanidi@gmail.com").withEmail1("vsaryanidi1@gmail.com").withEmail2("vsaryanidi2@gmail.com")
              .withGroup("TestGroup"),true);
    }
  }
  
  @Test

  public void testContactInfo() {
    app.contact().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllMails(), equalTo(mergeMail(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {

    return Arrays.asList(contact.getHome_phone(),contact.getMobile_phone(),contact.getWork_phone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeMail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail1(), contact.getEmail2())
            .stream()
            .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining());
  }
  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()/]", "");
  }
}
