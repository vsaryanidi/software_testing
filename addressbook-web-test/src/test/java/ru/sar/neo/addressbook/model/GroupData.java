package ru.sar.neo.addressbook.model;

import org.hibernate.annotations.Type;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity

@Table(name = "group_list")

public class GroupData {
  @XStreamOmitField

  @Id
  @Column(name = "group_id")

  private int id  = Integer.MAX_VALUE;

  @Column(name = "group_name")

  private String name;

  @Column(name = "group_header")
  @Type(type = "text")

  private String header;

  @Column(name = "group_footer")
  @Type(type = "text")

  private String footer;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();


  public GroupData withId(int id) {
    this.id = id;
    return this;}

    public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupDate = (GroupData) o;
    return id == groupDate.id &&
            Objects.equals(name, groupDate.name) &&
            Objects.equals(header, groupDate.header) &&
            Objects.equals(footer, groupDate.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, header, footer);
  }

}
