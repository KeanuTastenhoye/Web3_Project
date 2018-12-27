package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDbInMemory implements PersonDB {
    private Map<String, Person> persons = new HashMap<>();

    public PersonDbInMemory () {
        Person admin = new Person();
        Person user = new Person();

        admin.setUserid("admin");
        admin.setFirstName("ad");
        admin.setLastName("min");
        admin.setEmail("ad@min.be");
        admin.setPassword("a");

        user.setUserid("user");
        user.setFirstName("us");
        user.setLastName("er");
        user.setEmail("us@er.be");
        user.setPassword("u");

        add(admin);
        add(user);
    }

    @Override
    public Person get(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        return persons.get(personId);
    }

    @Override
    public List<Person> getAll(String order){
        return new ArrayList<Person>(persons.values());
    }

    @Override
    public void add(Person person){
        if(person == null){
            throw new DbException("No person given");
        }
        if (persons.containsKey(person.getUserid())) {
            throw new DbException("User already exists");
        }
        persons.put(person.getUserid(), person);
    }

    @Override
    public void update(Person person){
        if(person == null){
            throw new DbException("No person given");
        }
        if(!persons.containsKey(person.getUserid())){
            throw new DbException("No person found");
        }
        persons.put(person.getUserid(), person);
    }

    @Override
    public void delete(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        persons.remove(personId);
    }

    @Override
    public int getNumberOfPersons() {
        return persons.size();
    }

    @Override
    public ArrayList<String> getHeaders() {
        return null;
    }
}
