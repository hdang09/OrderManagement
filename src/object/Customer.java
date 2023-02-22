/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

/**
 *
 * @author Admin
 */
public class Customer implements Comparable<Customer>{
    private String id;
    private String name;
    private String address;
    private String phone;

    public Customer() {
        this.id = "";
        this.name = "";
        this.address = "";
        this.phone = "";
    }

    public Customer(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name.toUpperCase() + ", address=" + address.toUpperCase() + ", phone=" + phone + '}';
    }
    
    public String toFile() {
        return id + "," + name.toUpperCase() + "," + address.toUpperCase() + "," + phone;
    }

    @Override
    public int compareTo(Customer o) {
        String fullName1[] = name.split(" ");
        String name1 = fullName1[fullName1.length - 1];
        String fullName2[] = o.getName().split(" ");
        String name2 = fullName2[fullName2.length - 1];
        
        return name1.compareTo(name2);
    }
}
