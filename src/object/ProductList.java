/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductList extends ArrayList<Product> {

    String filePath = "src\\data\\products.txt";
    File file = new File(filePath);

    public ProductList readFile() {
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
                
                String id = words[0];
                String name = words[1];
                String unit = words[2];
                String origin = words[3];
                String price = words[4];
                this.add(new Product(id, name, unit, origin, price));
            }
            return this;
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public void print() {
        this.forEach(System.out::println);
    }
}
