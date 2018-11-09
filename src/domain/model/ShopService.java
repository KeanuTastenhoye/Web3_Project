package domain.model;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.db.ProductDB;
import domain.db.ProductDBSQL;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDB personDb;
    private ProductDB productDb;

    public ShopService (Properties properties) {
        personDb = new PersonDBSQL(properties);
        productDb = new ProductDBSQL(properties);
    }

    private PersonDB getPersonDb() { return personDb; }

    private ProductDB getProductDb() { return productDb; }

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
