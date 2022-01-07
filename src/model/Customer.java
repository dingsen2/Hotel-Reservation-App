package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    final String firstName;
    final String lastName;
    final String email;

    @Override
    public String toString(){
        return "First Name: "+ firstName +" Last Name: " + lastName
                + " email: " + email;
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        if(! (other instanceof Customer otherCustomer)){
            return false;
        }
        return (this.firstName.equals(otherCustomer.firstName)
                && this.lastName.equals(otherCustomer.lastName)
                && this.email.equals(otherCustomer.email));
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, email);
    }

    public Customer(String myFirstName, String myLastName, String myEmail){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(myEmail).matches()){
            email = myEmail;
        }
        else{
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        firstName = myFirstName;
        lastName = myLastName;
    }

    public String getEmail(){
        return email;
    }
}
