package ru.sar.neo.addressbook.generators;

import com.thoughtworks.xstream.XStream;
import ru.sar.neo.addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "ContactCount")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException{
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }
  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }

  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML (contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
              contact.getHome_phone(), contact.getMobile_phone(),contact.getWork_phone(),contact.getAddress(),
              contact.getEmail(),contact.getEmail1(),contact.getEmail2(),contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts (int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("Firstname %s",i)).withLastname(String.format("Lastname %s",i))
              .withHome_phone(String.format("Home_phone %s",i)).withMobile_phone(String.format("Mobile_phone %s",i))
              .withWork_phone(String.format("Work_phone %s",i))
              .withAddress(String.format("Address %s",i)).withEmail(String.format("Email %s",i))
              .withEmail1(String.format("Email1 %s",i)).withEmail2(String.format("Email2 %s",i))
              .withGroup(String.format("Group %s",i)));
    }
    return contacts;
  }

}