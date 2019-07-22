package ru.sar.neo.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

@XStreamAlias("contact")

public class ContactData {
@XStreamOmitField

@Id
@Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "home")
  @Type(type = "text")
  private String home_phone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile_phone;

  @Column(name = "work")
  @Type(type = "text")
  private String work_phone;

  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  private String email1;

  @Column(name = "email3")
  @Type(type = "text")
  private String email2;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups"
          , joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Transient
  private String allPhones;

  @Transient
  private String allMails;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;


  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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


  public File getPhoto() {
    return new File(photo);
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(address, that.address) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(home_phone, that.home_phone) &&
            Objects.equals(mobile_phone, that.mobile_phone) &&
            Objects.equals(work_phone, that.work_phone) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, firstname, lastname, home_phone, mobile_phone, work_phone, email);
  }
  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
