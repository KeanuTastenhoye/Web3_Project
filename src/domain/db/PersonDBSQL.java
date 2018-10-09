package domain.db;

import domain.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonDB {
    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0667956";

    public PersonDBSQL() {
        properties.setProperty("user", "r0667956");
        properties.setProperty("password", "Ghia2016");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "INSERT INTO person (userid, email, password, firstName, lastName)"
                   + "VALUES ('"
                   + person.getUserid() + "', '"
                   + person.getEmail() + "', '"
                   + person.getPassword() + "', '"
                   + person.getFirstName() + "', '"
                   + person.getLastName() + ")";

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "UPDATE person SET userid, email, password, firstName, lastName WHERE userid = " + person.getUserid();

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(String personId) {
        String sql = "DELETE FROM person WHERE userid = " + personId;

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Person get(String personId) {
        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM person WHERE userid = " + personId);
            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            Person person = new Person(personId, email, password, firstName, lastName);
            return person;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<Person>();
        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                Person person = new Person(userid, email, password, firstName, lastName);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    @Override
    public int getNumberOfPersons() {
        List<Person> personen = new ArrayList<Person>();
        personen = getAll();
        return personen.size();
    }
}
