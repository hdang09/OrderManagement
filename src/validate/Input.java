/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import object.Customer;
import object.Order;
import object.Product;

public class Input {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    boolean wrong;
    String customerIDRegex = "[C]{1}\\d{3}";
    String orderIDRegex = "[D]{1}\\d{3}";

    // String can be empty
    public String string(String message) {
        System.out.print(message);
        String string = sc.next().trim().replaceAll(",", "|");

        return string;
    }

    // String can NOT be empty
    public String stringNotEmpty(String message) {
        do {
            wrong = true;

            System.out.print(message);
            String string = sc.next().trim().replaceAll(",", "|");

            if (!string.isBlank()) {
                return string;
            }

            System.err.println("Fields do not allow to contain null value");
        } while (wrong);

        return "";
    }

    public int number(String message) {
        String numberRegex = "^\\d+$";
        do {
            wrong = true;
            System.out.print(message);
            String choice = sc.next().trim().replaceAll(",", "|");

            if (Pattern.matches(numberRegex, choice)) {
                return Integer.parseInt(choice);
            }

            System.err.println("Please input a number!");
        } while (wrong);

        return 0;
    }

    public String customerID(String message, ArrayList<Customer> customerList) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next().trim().replaceAll(",", "|");

            if (!Pattern.matches(customerIDRegex, id)) {
                wrong = true;
                System.err.println("Customer's Id has pattern 'Cxxx', with xxx is three digits");
                continue;
            }

            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    wrong = true;
                    System.err.println("The customer's ID field is not allowed to duplicate in the database.");
                    break;
                }
            }

            if (!wrong) {
                return id;
            }
        } while (wrong);

        return "";
    }

    public int findCustomerIndexByID(String message, ArrayList<Customer> customerList) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next().trim().replaceAll(",", "|");

            if (!Pattern.matches(customerIDRegex, id)) {
                wrong = true;
                System.err.println("Customer's Id has pattern 'Cxxx', with xxx is three digits");
                continue;
            }

            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId().equals(id)) {
                    return i;
                }
            }
        } while (wrong);
        return -1;
    }

    public String findCustomerID(ArrayList<Customer> customerList) {
        do {
            wrong = true;
            for (int i = 0; i < customerList.size(); i++) {
                System.out.println(i + ". " + customerList.get(i));
            }
            System.out.println("------------------------------------------");

            int choice = number("Your choice: ");
            if (choice < customerList.size()) {
                return customerList.get(choice).getId();
            }

            System.err.println("This customer does not exist");
        } while (wrong);

        return "";
    }

    public String customerPhone(String message) {
        String phoneRegex = "\\d{10,12}";
        do {
            wrong = true;
            System.out.print(message);
            String phone = sc.next().trim().replaceAll(",", "|");

            if (Pattern.matches(phoneRegex, phone)) {
                return phone;
            }

            System.err.println("The phone is a number string that has a length from 10 to 12");
        } while (wrong);

        return "";
    }

    public String updateCustomerPhone(String message, String prevValue) {
        String phoneRegex = "\\d{10,12}";
        do {
            wrong = true;
            System.out.print(message);
            String phone = sc.next().trim().replaceAll(",", "|");

            if (phone.isBlank()) {
                return prevValue;
            }

            if (Pattern.matches(phoneRegex, phone)) {
                return phone;
            }

            System.err.println("The phone is a number string that has a length from 10 to 12");
        } while (wrong);

        return "";
    }

    public String orderID(String message, ArrayList<Order> orderList) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next().trim().replaceAll(",", "|");

            if (!Pattern.matches(orderIDRegex, id)) {
                wrong = true;
                System.err.println("Order's Id has pattern 'Dxxx', with xxx is three digits");
                continue;
            }

            for (Order order : orderList) {
                if (order.getOrderID().equals(id)) {
                    wrong = true;
                    System.err.println("Order's id is not allowed to duplicate");
                    break;
                }
            }

            if (!wrong) {
                return id;
            }
        } while (wrong);

        return "";
    }

    public String findOrderId(ArrayList<Order> orderList) {
        do {
            wrong = true;
            for (int i = 0; i < orderList.size(); i++) {
                System.out.println(i + ". " + orderList.get(i));
            }
            System.out.println("------------------------------------------");

            int choice = number("Your choice: ");
            if (choice < orderList.size()) {
                return orderList.get(choice).getOrderID();
            }

            System.err.println("This customer does not exist");
        } while (wrong);

        return "";
    }
    
    public int findOrderIndexByID(String message, ArrayList<Order> orderList) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next().trim().replaceAll(",", "|");

            if (!Pattern.matches(orderIDRegex, id)) {
                wrong = true;
                System.err.println("Order's Id has pattern 'Dxxx', with xxx is three digits");
                continue;
            }

            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getOrderID().equals(id)) {
                    return i;
                }
            }
        } while (wrong);
        return -1;
    }

    public String findProductId(ArrayList<Product> productList) {
        do {
            wrong = true;
            for (int i = 0; i < productList.size(); i++) {
                System.out.println(i + ". " + productList.get(i));
            }
            System.out.println("------------------------------------------");

            int choice = number("Your choice: ");
            if (choice < productList.size()) {
                return productList.get(choice).getId();
            }

            System.err.println("This customer does not exist");
        } while (wrong);

        return "";
    }

    public boolean yesNo() {
        do {
            wrong = true;
            System.out.print("Your choice (Y/N): ");
            String choice = sc.next().toUpperCase().trim().replaceAll(",", "|");

            if (choice.isBlank()) {
                return false;
            }
            if (choice.equals("Y")) {
                return true;
            }
            if (choice.equals("N")) {
                return false;
            }

            System.err.println("Wrong input, please type again!");
        } while (wrong);

        return false;
    }

    public String date(String message) {
        do {
            wrong = true;
            System.out.print(message);
            String date = sc.next().trim().replaceAll(",", "|");

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(date);
                return date;
            } catch (ParseException e) {
                System.err.println("Wrnng input! Please type again!");
            }

        } while (wrong);

        return "";
    }
}
