/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import object.Customer;
import object.Order;
import object.Product;

public class Input {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    boolean wrong;

    public String string(String message) {
        do {
            wrong = true;

            System.out.print(message);
            String string = sc.next();

            if (!string.isEmpty()) {
                return string;
            }

            System.err.println("Fields do not allow to contain null value");
        } while (wrong);

        return "";
    }

    public String updateString(String message, String prevValue) {
        do {
            wrong = true;

            System.out.print(message);
            String string = sc.next();

            if (string.isEmpty()) {
                return prevValue;
            }

            return string;
        } while (wrong);
    }

    public int number(String message) {
        String numberRegex = "\\d";
        do {
            wrong = true;

            System.out.print(message);
            String number = sc.next();

            if (Pattern.matches(numberRegex, number)) {
                return Integer.parseInt(number);
            }

            System.err.println("Please input a number!");
        } while (wrong);

        return 0;
    }

    public int choice(String message) {
        String choiceRegex = "^\\d+$";
        do {
            wrong = true;
            System.out.print(message);
            String choice = sc.next();

            if (Pattern.matches(choiceRegex, choice)) {
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
            String id = sc.next();

//            if (Pattern.matches(customerIDRegex, id)) {
            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    wrong = true;
                    System.err.println("The customer’s ID field is not allowed to duplicate in the database.");
                    break;
                }
            }
//            } else {
//                wrong = true;
//                System.err.println("Publisher’s Id has pattern 'Pxxxxx', with xxxxx is five digits");
//            }

            if (!wrong) {
                return id;
            }
        } while (wrong);

        return "";
    }

    public String updateCustomerID(String message, ArrayList<Customer> customerList, String prevValue) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next();

            if (id.isBlank()) {
                return prevValue;
            }

            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    wrong = true;
                    System.err.println("The customer’s ID field is not allowed to duplicate in the database.");
                    break;
                }
            }

            if (!wrong) {
                return id;
            }
        } while (wrong);

        return "";
    }

    public String findCustomerID(String message, ArrayList<Customer> customerList) {
        do {
            wrong = true;
            customerList.forEach(System.out::println);
            System.out.println("-----------------");

            System.out.print(message);
            String id = sc.next();

            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    return id;
                }
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
            String phone = sc.next();

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
            String phone = sc.next();

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
            String id = sc.next();

//            if (Pattern.matches(bookIdRegex, id)) {
            for (Order order : orderList) {
                if (order.getOrderID().equals(id)) {
                    wrong = true;
                    System.err.println("Order’s id is not allowed to duplicate");
                    break;
                }
            }
//            } else {
//                wrong = true;
//                System.err.println("Book’s Id has pattern 'Bxxxxx', with xxxxx is five digits");
//            }

            if (!wrong) {
                return id;
            }
        } while (wrong);

        return "";
    }

    public String findProductId(String message, ArrayList<Product> productList) {
        do {
            wrong = true;
            productList.forEach(System.out::println);
            System.out.println("-----------------");

            System.out.print(message);
            String id = sc.next();

//            if (Pattern.matches(bookIdRegex, id)) {
            for (Product product : productList) {
                if (product.getId().equals(id)) {
                    return id;
                }
            }
            System.err.println("Product Id is not found: ");
//            } else {
//                System.err.println("Book’s Id has pattern 'Bxxxxx', with xxxxx is five digits");
//            }

        } while (wrong);

        return "";
    }

    public boolean yesNo(boolean isCreateContinuously) {
        if (isCreateContinuously) {
            System.out.println("Do you want to create new customer continuously or going back to the main menu?");
        }

        do {
            wrong = false;

            System.out.print("Your choice (Y/N): ");
            String choice = sc.next().toUpperCase();
            wrong = !choice.equals("Y") && !choice.equals("N");

            if (!wrong) {
                return choice.equals("Y");
            }

            System.err.println("Please input again!");
        } while (wrong);

        return false;
    }
}
