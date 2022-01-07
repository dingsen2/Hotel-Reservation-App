package service;

import model.Customer;

import java.util.*;
import java.util.regex.Pattern;

public class CustomerService {
    private static CustomerService customerService;
    private CustomerService(){}
    public static CustomerService getCustomerService(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }
    private Map<String, Customer> customerMap = new HashMap<>();
    public void addCustomer(String email, String firstName, String lastName){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        if(customerMap.containsKey(email)){
            throw new IllegalArgumentException("Email already exists, please enter other email addresses");
        }
        Customer newCustomer = new Customer(firstName, lastName, email);
        customerMap.put(email, newCustomer);
    }

    public Customer getCustomer(String customerEmail){
        return customerMap.get(customerEmail);
    }

    public Collection<Customer>getAllCustomers(){
        List<Customer> allCustomers = new ArrayList<Customer>(customerMap.values());
        return allCustomers;
    }
}
