package ru.sar.neo.addressbook.tests;

import org.testng.annotations.Test;
import ru.sar.neo.addressbook.model.ContactData;
import ru.sar.neo.addressbook.model.Contacts;


import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection () {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, lastname, firstname from addressbook");
      Contacts contacts= new Contacts();
      while (rs.next()) {
        contacts.add(new ContactData()
                .withId(rs.getInt("id"))
                .withFirstname(rs.getString("lastname"))
                .withLastname(rs.getString("firstname")));
      }
      rs.close();
      st.close();
      conn.close();

      System.out.println(contacts);
      // Do something with the Connection
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}