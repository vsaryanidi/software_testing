package ru.sar.neo.addressbook.model;

public class ContactData {
  private final String address;
  private final String firstname;
  private final String lastname;
  private final String home_phone;
  private final String email;

  public ContactData(String address, String firstname, String lastname, String home_phone, String email) {
    this.address = address;
    this.firstname = firstname;
    this.lastname = lastname;
    this.home_phone = home_phone;
    this.email = email;
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

  public String getEmail() {
    return email;
  }
}
