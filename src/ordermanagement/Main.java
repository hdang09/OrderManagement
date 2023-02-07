/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ordermanagement;

import object.Customer;
import object.CustomerList;
import object.Order;
import object.OrderList;
import object.ProductList;
import validate.Input;

/**
 *
 * @author Admin
 */
public class Main {

    static Input input = new Input();
    static ProductList productList = new ProductList().readFile();
    static CustomerList customerList = new CustomerList().readFile();
    static OrderList orderList = new OrderList().readFile();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean isContinue = true;
        do {
            System.out.println();
            System.out.println("-------------------- ORDER MANAGEMENT --------------------");
            System.out.println("| 1. List all Products                                   |");
            System.out.println("| 2. List all Customers                                  |");
            System.out.println("| 3. Search a Customer based on his/her ID               |");
            System.out.println("| 4. Add a Customer                                      |");
            System.out.println("| 5. Update a Customer                                   |");
            System.out.println("| 6. Save Customers to the file, named customers.txt     |");
            System.out.println("| 7. List all Orders in ascending order of Customer name |");
            System.out.println("| 8. List all pending Orders                             |");
            System.out.println("| 9. Add an Order                                        |");
            System.out.println("| 10. Update an Order                                    |");
//            System.out.println("| 10.1. Update an Order based on its ID                  |");
//            System.out.println("| 10.2. Delete an Order based on its ID                  |");
            System.out.println("| 11. Save Orders to file, named orders.txt              |");
            System.out.println("| Others- Quit                                           |");
            System.out.println("---------------------------------------------------------");

            int choice = input.choice("Your choice: ");
            System.out.println();
            switch (choice) {
                case 1 -> {
                    productList.print();
                }
                case 2 -> {
                    customerList.print();
                }
                case 3 -> {
                    int customerIndex = input.findCustomerIndexByID("Input customer ID your want to search: ", customerList);
                    if (customerIndex == -1) {
                        System.err.println("“This customer does not exist");
                    } else {
                        System.out.println(customerList.get(customerIndex));
                    }
                }
                case 4 -> {
                    boolean isCreateContinuously;
                    do {
                        String id = input.customerID("Input customer ID: ", customerList);
                        String name = input.string("Input customer's name: ");
                        String address = input.string("Input customer's address: ");
                        String phone = input.customerPhone("Input customer's phone: ");
                        Customer customer = new Customer(id, name, address, phone);
                        customerList.add(customer);

                        isCreateContinuously = input.yesNo(true);
                    } while (isCreateContinuously);
                }
                case 5 -> {
                    int customerIndex = input.findCustomerIndexByID("Input customer ID your want to update: ", customerList);
                    if (customerIndex == -1) {
                        System.err.println("“This customer does not exist");
                    } else {
                        String id = input.customerID("Input customer ID: ", customerList);
                        String name = input.string("Input customer's name: ");
                        String address = input.string("Input customer's address: ");
                        String phone = input.customerPhone("Input customer's phone: ");
                        Customer customer = new Customer(id, name, address, phone);
                        customerList.set(customerIndex, customer);
                    }
                }
                case 6 -> {
                    customerList.saveToFile();
                }
                case 7 -> {
                    orderList.print();
                }
                case 8 -> {
                    orderList.printPendingOrders();
                }
                case 9 -> {
                    boolean isCreateContinuously;
                    do {
                        String orderID = input.orderID("Input order ID: ", orderList);
                        String customerID = input.findCustomerID("Input customer ID your want to choose: ", customerList);
                        String productID = input.findProductId("Input product ID you want to choose: ", productList);
                        int quantity = input.number("Input order's quantity: ");
                        String date = input.string("Input order's date: ");
                        Order order = new Order(orderID, customerID, productID, quantity, date, false);
                        orderList.add(order);

                        isCreateContinuously = input.yesNo(true);
                    } while (isCreateContinuously);
                }
                case 10 -> {
                    boolean isContinueSubmenu = true;
                    do {
                        System.out.println();
                        System.out.println("----------------- 10. Update an Order -----------------");
                        System.out.println("| 1. Update an Order based on its ID                  |");
                        System.out.println("| 2. Delete an Order based on its ID                  |");
                        System.out.println("| Others- Go back to menu                             |");
                        System.out.println("-------------------------------------------------------");

                        int subChoice = input.choice("Your choice: ");
                        System.out.println();
                        switch (subChoice) {
                            case 1 -> {
                                int orderIndex = input.findOrderIndexByID("Input order ID you want to update: ", orderList);
                                if (orderIndex != -1) {
                                    boolean status = orderList.get(orderIndex).isStatus();
                                    System.out.println("Do you want to change status to " + !status + "?");
                                    boolean changeChoice = input.yesNo(false);
                                    if (changeChoice) {
                                        orderList.get(orderIndex).setStatus(!status);
                                    }
                                }
                            }
                            case 2 -> {
                                int orderIndex = input.findOrderIndexByID("Input order ID you want to update: ", orderList);
                                if (orderIndex != -1) {
                                    System.out.println("Are you sure to delete this order?");
                                    boolean deleteChoice = input.yesNo(false);
                                    if (deleteChoice) {
                                        orderList.remove(orderIndex);
                                    }
                                }
                            }
                            default -> isContinueSubmenu = false;
                        }
                    } while (isContinueSubmenu);
                }
                case 11 -> {
                    orderList.saveToFile();
                }
                default -> {
                    System.out.println("Good bye! Have a nice day!");
                    isContinue = false;
                }

            }
        } while (isContinue);
    }

}
