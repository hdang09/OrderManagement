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

/**
 *
 * @author Admin
 */
public class CustomerList extends ArrayList<Customer> {
    String filePath = "src\\data\\customers.txt";
    File file = new File(filePath);

    public CustomerList readFile() {
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
                } catch (IOException ex) {
                    Logger.getLogger(CustomerList.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (Exception e) {
        }
    }
    
}
