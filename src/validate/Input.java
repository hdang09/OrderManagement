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

    // String can be empty
    public String string(String message) {
        System.out.print(message);
        String string = sc.next().trim();

        return string;
    }

    // String can NOT be empty
    public String stringNotEmpty(String message) {
        do {
            wrong = true;

            System.out.print(message);
            String string = sc.next().trim();

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
            String choice = sc.next().trim();

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
            String id = sc.next().trim();

            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    wrong = true;
                    System.err.println("The customer's ID field is not allowed to duplicate in the database.");
                    break;
                }
            }

            if (!wrong) return id;
        } while (wrong);

        return "";
    }

    public String findCustomerID(ArrayList<Customer> customerList) {
        do {
            wrong = true;
            for (int i = 0; i < customerList.size(); i++)
                System.out.println(i + ". " + customerList.get(i));
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
            String phone = sc.next().trim();

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
            String phone = sc.next().trim();

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
            String id = sc.next().trim();

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

    public String findProductId(ArrayList<Product> productList) {
        do {
            wrong = true;
            for (int i = 0; i < productList.size(); i++)
                System.out.println(i + ". " + productList.get(i));
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
            String choice = sc.next().toUpperCase().trim();

            if (choice.isBlank()) return false;
            if (choice.equals("Y")) return true;
            if (choice.equals("N")) return false;
            
            System.err.println("Wrong input, please type again!");
        } while (wrong);

        return false;
    }

    public String date(String message) {
        do {
            wrong = true;
            System.out.print(message);
            String date = sc.next().trim();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
