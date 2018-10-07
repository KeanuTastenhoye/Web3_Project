package domain.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import domain.model.Person;

import javax.swing.*;

public class Project_UI {

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0667956";
        properties.setProperty("user", "r0667956");
        properties.setProperty("password", "Ghia2016");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        //properties.setProperty("sslmode","prefer");

        Connection connection = DriverManager.getConnection(url,properties);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery( "SELECT * FROM person" );

        while(result.next()) {
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");

            try {    // validation of data stored in database
                Person person = new Person(userid, email, password, firstName, lastName);
                System.out.println(person.toString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
                String newUserid = JOptionPane.showInputDialog("User ID:");
                String newFirstName = JOptionPane.showInputDialog("Firstname:");
                String newLastName = JOptionPane.showInputDialog("Lastname:");
                String newEmail = JOptionPane.showInputDialog("Email:");
                String newPassword = JOptionPane.showInputDialog("Password:");
                String sql = "INSERT INTO person VALUES ('"
                        + newUserid + "', '"
                        + newFirstName + "', '"
                        + newLastName + "', '"
                        + newEmail + "', '"
                        + newPassword + "')";
                statement.executeUpdate(sql);

        statement.close();
        connection.close();
    }

}
