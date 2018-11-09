package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.db.StringHasher;

public class Person {
    private String userid;
    private String email;
    private String hashedPassword;
    private byte[] seed;
    private String firstName;
    private String lastName;

    public Person(String userid, String email, String password, byte[] seed, String firstName, String lastName) {
        setUserid(userid);
        setEmail(email);
        setSeed(seed);
        setHashedPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person() {
    }

    public String getUserid() { return userid; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getSeed(){  return this.seed; }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setUserid(String userid) {
        if(userid.isEmpty()){
            throw new IllegalArgumentException("No userid given");
        }
        this.userid = userid;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public void setSeed(byte[] seed){
        this.seed = seed;
    }

    public void setPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }
        setSeed(StringHasher.getSeed());
        setHashedPassword(hashPassword(password));
    }

    public void setHashedPassword(String hashedPassword){
        if(hashedPassword.isEmpty()){
            throw new IllegalArgumentException("No hashedPassword given.");
        }
        if(hashedPassword.length() != 128){
            throw new IllegalArgumentException("HashedPassword is of wrong length. Length is " + hashedPassword.length());
        }
        this.hashedPassword = hashedPassword;
    }

    public String hashPassword(String password){
        return StringHasher.sha512(password, getSeed());
    }

    public boolean isCorrectPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }
        return getHashedPassword().equals(hashPassword(password));
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }
}
