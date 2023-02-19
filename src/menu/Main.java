/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;

import object.CustomerList;
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
            System.out.println("| 11. Save Orders to file, named orders.txt              |");
            System.out.println("| Others- Quit                                           |");
            System.out.println("---------------------------------------------------------");
            System.out.println();

            int choice = input.choice("Your choice: ");
            switch (choice) {
                case 1 -> productList.print();
                case 2 -> customerList.print();
                case 3 -> customerList.search();
                case 4 -> customerList.add();
                case 5 -> customerList.update();
                case 6 -> customerList.saveToFile();
                case 7 -> orderList.print(customerList);
                case 8 -> orderList.printPendingOrders();
                case 9 -> orderList.add(customerList, productList);
                case 10 -> orderList.update();
                case 11 -> orderList.saveToFile();
                default -> {
                    System.out.println("Good bye! Have a nice day!");
                    isContinue = false;
                }
            }
        } while (isContinue);
    }

}
