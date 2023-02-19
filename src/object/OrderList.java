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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import validate.Input;

/**
 *
 * @author Admin
 */
public class OrderList extends ArrayList<Order> {

    String filePath = "src\\data\\orders.txt";
    File file = new File(filePath);
    Input input = new Input();
    
    public int find(String orderID) {
        for (Order order : this) {
            if (order.getOrderID().equals(orderID)) {
                return this.indexOf(order);
            }
        }
        return -1;
    }

    public void add(CustomerList customerList, ProductList productList) {
        boolean isCreateContinuously;
        do {
            String orderID = input.orderID("Input order ID: ", this);
            String customerID = input.findCustomerID("Input customer ID your want to choose: ", customerList);
            String productID = input.findProductId("Input product ID you want to choose: ", productList);
            int quantity = input.number("Input order's quantity: ");
            String date = input.string("Input order's date: ");
            Order order = new Order(orderID, customerID, productID, quantity, date, false);
            this.add(order);

            isCreateContinuously = input.yesNo(true);
        } while (isCreateContinuously);
    }

    public void update() {
        boolean isContinueSubmenu = true;
        do {
            System.out.println();
            System.out.println("----------------- 10. Update an Order -----------------");
            System.out.println("| 1. Update an Order based on its ID                  |");
            System.out.println("| 2. Delete an Order based on its ID                  |");
            System.out.println("| Others- Go back to menu                             |");
            System.out.println("-------------------------------------------------------");

            int choice = input.choice("Your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> {
                    String orderID = input.string("Input order ID you want to update: ");
                    int orderIndex = this.find(orderID);
                    if (orderIndex == -1) {
                        System.err.println("Order ID is not exist");
                    } else {
                        boolean status = this.get(orderIndex).isStatus();
                        System.out.println("Do you want to change status to " + !status + "?");
                        boolean changeChoice = input.yesNo(false);
                        if (changeChoice) {
                            this.get(orderIndex).setStatus(!status);
                        }
                    }
                }
                case 2 -> {
                    String orderID = input.string("Input order ID you want to update: ");
                    int orderIndex = this.find(orderID);
                    
                    if (orderIndex == -1) {
                        System.err.println("Order ID is not exist");
                    } else {
                        System.out.println("Are you sure to delete this order?");
                        boolean deleteChoice = input.yesNo(false);
                        if (deleteChoice) {
                            this.remove(orderIndex);
                        }
                    }
                }
                default ->
                    isContinueSubmenu = false;
            }
        } while (isContinueSubmenu);
    }

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

    public void print(ArrayList<Customer> customerList) {
        Collections.sort(customerList);
//        customerList.forEach(System.out::println);
//        System.out.println("");
        
        for (Customer customer : customerList) {
            for (Order order : this) {
                if (order.getCustomerID().equals(customer.getId())) {
                    System.out.println(order);
                }
            }
        }
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
