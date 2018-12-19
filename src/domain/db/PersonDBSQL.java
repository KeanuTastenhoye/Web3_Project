package domain.db;

import domain.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonDB {
    private Properties properties;
    private String url;

    public PersonDBSQL(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "INSERT INTO person (userid, email, password, \"firstName\", \"lastName\")" +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getUserid());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getHashedPassword());
            statement.setString(4, person.getFirstName());
            statement.setString(5, person.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "UPDATE person " +
                     "SET email = ?, password = ?, \"firstName\" = ?, \"lastName\" = ? " +
                     "WHERE userid = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getEmail());
            statement.setString(2, person.getHashedPassword());
            statement.setString(3, person.getFirstName());
            statement.setString(4, person.getLastName());
            statement.setString(5, person.getUserid());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String personId) {
        String sql = "DELETE " +
                     "FROM person " +
                     "WHERE userid = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) {
        String sql = "SELECT * " +
                     "FROM person " +
                     "WHERE userid = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                Person person = new Person(personId, email, password, firstName, lastName);
                return person;
            }
            else {
                throw new DbException("Failed to get person.");
            }
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
            return persons;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        List<Person> personen = new ArrayList<Person>();
        personen = getAll();
        return personen.size();
    }
}
