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
public class OrderList extends ArrayList<Order> {

    String filePath = "src\\data\\orders.txt";
    File file = new File(filePath);

    public OrderList readFile() {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String words[] = line.split(",");
                String orderID = words[0];
                String customerID = words[1];
                String productID = words[2];
                int quantity = Integer.parseInt(words[3]);
                String date = words[4];
                boolean status = Boolean.parseBoolean(words[5]);
                this.add(new Order(orderID, customerID, productID, quantity, date, status));
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

    public void printPendingOrders() {
        this.forEach(order -> {
            if (!order.isStatus()) {
                System.out.println(order.toString());
            }
        });
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            this.forEach(customer -> {
                try {
                    bw.write(customer.toFile());
                } catch (IOException ex) {
                    Logger.getLogger(OrderList.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            System.out.println("Write to file successfully!");
            bw.close();
        } catch (Exception e) {
            System.err.println("Error while writing to file!");
        }
    }
}
