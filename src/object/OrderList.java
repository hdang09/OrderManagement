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
        boolean isContinue;
        do {
            String orderID = input.orderID("Input order ID: ", this);
            String customerID = input.findCustomerID(customerList);
            String productID = input.findProductId(productList);
            int quantity = input.number("Input order's quantity: ");
            String date = input.date("Input order's date (MM/dd/yyyy): ");
            System.out.println("Input order's status (Y: true, N: false)");
            boolean status = input.yesNo();
            
            Order order = new Order(orderID, customerID, productID, quantity, date, status);
            this.add(order);

            System.out.println("Do you want to create new customer continuously or going back to the main menu?");
            isContinue = input.yesNo();
        } while (isContinue);
    }

    void updateOrder() {
        int orderIndex = input.findOrderIndexByID("Input order ID you want to update: ", this);
        
        if (orderIndex == -1) {
            System.err.println("Order ID is not exist");
            return;
        }

        boolean status = this.get(orderIndex).isStatus();
        System.out.println("Do you want to change status to " + !status + "?");
        boolean changeChoice = input.yesNo();
        if (changeChoice) {
            this.get(orderIndex).setStatus(!status);
        }

    }

    void delete() {
        int orderIndex = input.findOrderIndexByID("Input order ID you want to update: ", this);

        if (orderIndex == -1) {
            System.err.println("Order ID is not exist");
            return;
        }

        System.out.println("Are you sure to delete this order?");
        boolean deleteChoice = input.yesNo();
        if (deleteChoice) {
            this.remove(orderIndex);
        }
    }

    public void update() {
        boolean isContinue = true;
        do {
            System.out.println();
            System.out.println("----------------- 10. Update an Order -----------------");
            System.out.println("| 1. Update an Order based on its ID                  |");
            System.out.println("| 2. Delete an Order based on its ID                  |");
            System.out.println("| Others- Go back to menu                             |");
            System.out.println("-------------------------------------------------------");

            int choice = input.number("Your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> updateOrder();
                case 2 -> delete();
                default -> isContinue = false;
            }
        } while (isContinue);
    }

    public OrderList readFile() {
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
                if (words.length < 5) continue;
                
                String orderID = words[0].trim();
                String customerID = words[1].trim();
                String productID = words[2].trim();
                int quantity = Integer.parseInt(words[3].trim());
                String date = words[4].trim();
                boolean status = Boolean.parseBoolean(words[5].trim());
                
                Order order = new Order(orderID, customerID, productID, quantity, date, status);
                this.add(order);
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

        for (Customer customer : customerList) {
            for (Order order : this) {
                if (order.getCustomerID().equals(customer.getId())) 
                    System.out.println(order);
            }
        }
    }

    public void printPendingOrders() {
        this.forEach(order -> {
            if (order.isStatus()) 
                System.out.println(order.toString());
        });
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            this.forEach(order -> {
                try {
                    bw.write(order.toFile());
                    bw.newLine();
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
