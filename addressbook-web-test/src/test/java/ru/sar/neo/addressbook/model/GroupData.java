package ru.sar.neo.addressbook.model;

public class GroupData {
  private final String id;
  private final String name;
  private final String header;
  private final String footer;


  public GroupData(String name, String header, String footer) {
    this.id = null;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(String id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getId() {
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
}
