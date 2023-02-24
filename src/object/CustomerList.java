/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import validate.Input;

/**
 *
 * @author Admin
 */
public class CustomerList extends ArrayList<Customer> {

    String filePath = "src\\data\\customers.txt";
    File file = new File(filePath);
    Input input = new Input();

    public int find(String customerID) {
        for (Customer customer : this) {
            if (customer.getId().equals(customerID)) {
                return this.indexOf(customer);
            }
        }
        return -1;
    }

    public void search() {
        int customerIndex = input.findCustomerIndexByID("Input customer ID your want to search: ", this);

        if (customerIndex == -1) {
            System.err.println("This customer does not exist");
            return;
        }

        System.out.println(this.get(customerIndex));
    }

    public void add() {
        boolean isContinue;
        do {
            String id = input.customerID("Input customer ID: ", this);
            String name = input.stringNotEmpty("Input customer's name: ");
            String address = input.stringNotEmpty("Input customer's address: ");
            String phone = input.customerPhone("Input customer's phone: ");
            
            Customer customer = new Customer(id, name, address, phone);
            this.add(customer);
            System.out.println("Add customer successfully");

            System.out.println("Do you want to create new customer continuously or going back to the main menu?");
            isContinue = input.yesNo();
        } while (isContinue);
    }

    public void update() {
        System.out.println("Input customer ID you want to search: ");
        int customerIndex = input.findCustomerIndexByID("Input customer ID you want to search: ", this);

        if (customerIndex == -1) {
            System.err.println("This customer does not exist");
            return;
        }

        String id = input.customerID("Input customer ID you want to update: ", this);
        String name = input.string("Input customer's name: ");
        String address = input.string("Input customer's address: ");
        String phone = input.updateCustomerPhone("Input customer's phone: ", this.get(customerIndex).getPhone());
        
        if (id.isBlank()) id = this.get(customerIndex).getId();
        if (name.isBlank()) name = this.get(customerIndex).getName();
        if (address.isBlank()) address = this.get(customerIndex).getAddress();
        
        Customer customer = new Customer(id, name, address, phone);
        this.set(customerIndex, customer);
        System.out.println("Update successfully!");
    }

    public CustomerList readFile() {
        // Create file if it don't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Read file
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String words[] = line.split(",");
                if (words.length < 4) continue;
                
                String id = words[0].trim();
                String name = words[1].trim();
                String address = words[2].trim();
                String phone = words[3].trim();
                
                Customer customer = new Customer(id, name, address, phone);
                this.add(customer);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public void print() {
        this.forEach(System.out::println);
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            this.forEach(customer -> {
                try {
                    bw.write(customer.toFile());
                    bw.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            System.out.println("Write to file successfully!");
        } catch (Exception e) {
            System.err.println("Error while writing to file!");
        }
    }
}
