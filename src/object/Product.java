/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

/**
 *
 * @author Admin
 */
public class Product {
    private String id;
    private String name;
    private String unit;
    private String origin;
    private String price;

    public Product() {
        this.id = "";
        this.name = "";
        this.unit = "";
        this.origin = "";
        this.price = "";
    }

    public Product(String id, String name, String unit, String origin, String price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", unit=" + unit + ", origin=" + origin + ", price=" + price + '}';
    }
    
}
