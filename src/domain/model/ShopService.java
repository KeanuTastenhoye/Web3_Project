package domain.model;

import domain.db.PersonDbInMemory;
import domain.db.ProductDbInMemory;

import java.util.List;

public class ShopService {
    private PersonDbInMemory personDb = new PersonDbInMemory();
    private ProductDbInMemory productDb = new ProductDbInMemory();

    public ShopService() { }

    private PersonDbInMemory getPersonDb() { return personDb; }

    private ProductDbInMemory getProductDb() { return productDb; }

    public Person getPerson(String personId) { return getPersonDb().get(personId); }

    public Product getProduct(int productId) { return getProductDb().get(productId); }

    public List<Person> getPersons() { return getPersonDb().getAll(); }

    public List<Product> getProducts() { return getProductDb().getAll(); }

    public void addPerson(Person person) { getPersonDb().add(person); }

    public void addProduct(Product product) { getProductDb().add(product); }

    public void updatePersons(Person person) { getPersonDb().update(person); }

    public void updateProducts(Product product) { getProductDb().update(product); }

    public void deletePerson(String id) { getPersonDb().delete(id); }

    public void deleteProduct(int id) { getProductDb().delete(id); }

}
