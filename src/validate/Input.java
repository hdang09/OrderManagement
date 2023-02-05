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
    String customerIDRegex = "[C]{1}\\d{3}";
    String nameRegex = ".{5,30}";
    String bookIdRegex = "[B]{1}\\d{5}";

    public int choice(String message) {
        String choiceRegex = "\\d";
        do {
            wrong = true;
            System.out.print(message);
            String choice = sc.next();

            if (Pattern.matches(choiceRegex, choice)) {
                return Integer.parseInt(choice);
            } else {
                System.err.println("Please input a number!");
            }
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

    public String findCustomerID(String message, ArrayList<Customer> customerList) {
        do {
            wrong = true;
            customerList.forEach(System.out::println);
            System.out.println("-----------------");

            System.out.print(message);
            String id = sc.next();

//            if (Pattern.matches(customerIDRegex, id)) {
            for (Customer customer : customerList) {
                if (customer.getId().equals(id)) {
                    return id;
                }
            }
            System.err.println("This customer does not exist");
//                customerList.forEach(p -> System.out.print(p.getId() + " "));
//                System.out.println();
//            } else {
//                System.err.println("Publisher’s Id has pattern 'Pxxxxx', with xxxxx is five digits");
//            }

        } while (wrong);

        return "";
    }
    
    public String string(String message) {
        do {
            wrong = true;
            System.out.print(message);
            String string = sc.next();
            if (string.isEmpty()) {
                System.err.println("Fields do not allow to contain null value");
            } else {
                return string;
            }
        } while (wrong);
        return "";
    }

    public int number(String message) {
        String numberRegex = "\\d";
        do {            
            wrong = true;
            System.out.print(message);
            String number = sc.next();
            
            if (Pattern.matches(numberRegex, number)) {
                return Integer.parseInt(number);
            } else {
                System.err.println("Please input a number!");
            }
        } while (wrong);
        return 0;
    }

    public int findCustomerIndexByID(String message, ArrayList<Customer> customerList) {
        do {
            wrong = false;
            System.out.print(message);
            String id = sc.next();

//            if (Pattern.matches(publisherIdRegex, id)) {
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getId().equals(id)) {
                    return i;
                }
            }
//            } else {
//                wrong = true;
//                System.err.println("Publisher’s Id has pattern 'Pxxxxx', with xxxxx is five digits");
//            }
        } while (wrong);

        return -1;
    }

//    public String publisherName(String message) {
//        do {
//            wrong = true;
//            System.out.print(message);
//            String name = sc.next();
//
//            if (Pattern.matches(nameRegex, name)) {
//                return name;
//            } else {
//                System.err.println("Publisher’s Name is a string and has a length from 5 to 30 characters.");
//            }
//        } while (wrong);
//
//        return "";
//    }
    public String customerPhone(String message) {
        String phoneRegex = "\\d{10,12}";
        do {
            wrong = true;
            System.out.print(message);
            String phone = sc.next();

            if (Pattern.matches(phoneRegex, phone)) {
                return phone;
            } else {
                System.err.println("The phone is a number string that has a length from 10 to 12");
            }
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
                    System.err.println("Book’s id is not allowed to duplicate");
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

//    public int findBookIndexById(String message, ArrayList<Book> books) {
//        do {
//            wrong = false;
//            System.out.print(message);
//            String id = sc.next();
//
//            if (Pattern.matches(bookIdRegex, id)) {
//                for (int i = 0; i < books.size(); i++) {
//                    if (books.get(i).getId().equals(id)) {
//                        return i;
//                    }
//                }
//            } else {
//                wrong = true;
//                System.err.println("Book’s Id has pattern 'Bxxxxx', with xxxxx is five digits");
//            }
//        } while (wrong);
//
//        return -1;
//    }
//
//    public String bookName(String message) {
//        do {
//            wrong = true;
//            System.out.print(message);
//            String name = sc.next();
//
//            if (Pattern.matches(nameRegex, name)) {
//                return name;
//            } else {
//                System.err.println("Book’s name is a string and has a length from 5 to 30 characters.");
//            }
//        } while (wrong);
//
//        return "";
//    }
//
//    public Double bookPrice(String message) {
//        do {
//            System.out.print(message);
//            Double price = sc.nextDouble();
//
//            wrong = price <= 0;
//            if (wrong) {
//                System.err.println("Book’s Price is a real number and greater than 0");
//            } else {
//                return price;
//            }
//        } while (wrong);
//
//        return 0.0;
//    }
//
//    public int bookQuantity(String message) {
//        do {
//            System.out.print(message);
//            int quantity = sc.nextInt();
//
//            wrong = quantity <= 0;
//            if (wrong) {
//                System.err.println("Book’s Quantity is an integer number and greater than 0");
//            } else {
//                return quantity;
//            }
//        } while (wrong);
//
//        return 0;
//    }
//
//    public String bookStatus(String message) {
//        do {
//            System.out.print(message);
//            String status = sc.next();
//
//            wrong = !status.equals("Available") && !status.equals("Not Available");
//            if (wrong) {
//                System.err.println("Status is a string and has values: Available or Not Available values");
//            } else {
//                return status;
//            }
//        } while (wrong);
//
//        return "";
//    }
    public boolean isCreateContinuously() {
        System.out.println("Do you want to create new customer continuously or going back to the main menu?");
        do {
            wrong = false;
            System.out.print("Your choice (Y/N): ");
            String choice = sc.next().toUpperCase();
            wrong = !choice.equals("Y") && !choice.equals("N");
            if (wrong) {
                System.err.println("Please input again!");
            } else {
                return choice.equals("Y");
            }
        } while (wrong);
        return false;
    }
}
