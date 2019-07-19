package ru.sar.neo.addressbook.generators;

import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException{
      int count = Integer.parseInt(args[0]);
      File file = new File(args[1]);

      List<ContactData> contacts = generateContacts(count);
      save(contacts, file);

    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
      Writer writer = new FileWriter(file);
      for (ContactData contact : contacts){
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                contact.getHome_phone(), contact.getMobile_phone(),contact.getWork_phone(),contact.getAddress(),
                contact.getEmail(),contact.getEmail1(),contact.getEmail2(),contact.getGroup()));
      }
      writer.close();
    }

    private static List<ContactData> generateContacts (int count) {
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
