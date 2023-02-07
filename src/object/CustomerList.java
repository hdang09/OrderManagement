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
        String customerID = input.string("Input customer ID your want to search: ");
        int customerIndex = this.find(customerID);
        if (customerIndex == -1) {
            System.err.println("This customer does not exist");
        } else {
            System.out.println(this.get(customerIndex));
        }
    }

    public void add() {
        boolean isCreateContinuously;
        do {
            String id = input.customerID("Input customer ID: ", this);
            String name = input.string("Input customer's name: ");
            String address = input.string("Input customer's address: ");
            String phone = input.customerPhone("Input customer's phone: ");
            Customer customer = new Customer(id, name, address, phone);
            this.add(customer);

            isCreateContinuously = input.yesNo(true);
        } while (isCreateContinuously);
    }

    public void update() {
        String customerID = input.string("Input customer ID your want to search: ");
        int customerIndex = this.find(customerID);
        if (customerIndex == -1) {
            System.err.println("This customer does not exist");
        } else {
            String id = input.customerID("Input customer ID: ", this);
            String name = input.string("Input customer's name: ");
            String address = input.string("Input customer's address: ");
            String phone = input.customerPhone("Input customer's phone: ");
            Customer customer = new Customer(id, name, address, phone);
            this.set(customerIndex, customer);
            System.out.println("Update successfully!");
        }
    }

    public CustomerList readFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String words[] = line.split(",");
                String id = words[0];
                String name = words[1];
                String address = words[2];
                String phone = words[3];
                this.add(new Customer(id, name, address, phone));
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
            bw.close();
        } catch (Exception e) {
            System.err.println("Error while writing to file!");
        }
    }
}
