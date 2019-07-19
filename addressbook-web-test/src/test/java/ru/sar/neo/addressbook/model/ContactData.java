package ru.sar.neo.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;;
  private String address;
  private String firstname;
  private String lastname;
  private String home_phone;
  private String mobile_phone;
  private String work_phone;
  private String email;
  private String email1;
  private String email2;
  private String group;
  private String allPhones;
  private String allMails;
  private File photo;


  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

 public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withHome_phone(String home_phone) {
    this.home_phone = home_phone;
    return this;
  }

  public ContactData withMobile_phone(String mobile_phone) {
    this.mobile_phone = mobile_phone;
    return this;
  }

  public ContactData withWork_phone(String work_phone) {
    this.work_phone = work_phone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }


  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public ContactData withAllMails(String allMails) {
    this.allMails = allMails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


  public File getPhoto() {
    return photo;
  }

  public String getAllMails() {
    return allMails;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public int getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHome_phone() {
    return home_phone;
  }

  public String getMobile_phone() {
    return mobile_phone;
  }

  public String getWork_phone() {
    return work_phone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getGroup() {
    return group;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

}
