package ru.sar.neo.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String address;
  private final String firstname;
  private final String lastname;
  private final String home_phone;
  private final String email;
  private String group;

   public ContactData(String address, String firstname, String lastname, String home_phone, String email, String group) {
    this.id = null;
    this.address = address;
    this.firstname = firstname;
    this.lastname = lastname;
    this.home_phone = home_phone;
    this.email = email;
    this.group = group;
  }

  public ContactData(String id, String address, String firstname, String lastname, String home_phone, String email, String group) {
    this.id = id;
    this.address = address;
    this.firstname = firstname;
    this.lastname = lastname;
    this.home_phone = home_phone;
    this.email = email;
    this.group = group;
  }

  public String getId() {
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

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
