package com.cafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cafe {
    private List<Item> goods;
    private HashMap<Customer, Bill> customers;

    // stores customers and goods
    public Cafe() {
        this.customers = new HashMap<Customer, Bill>();
        this.goods = new ArrayList<Item>();
        this.goods.add(new Item("coffee", 6.50));
        this.goods.add(new Item("toast", 3.0));
        this.goods.add(new Item("eggs", 1.50));
    }

    public List<Item> getGoods() {
        return this.goods;
    }

    public HashMap<Customer, Bill> getCustomers() {
        return this.customers;
    }

    // check if customer in hotel
    public boolean customerExists(Customer customer) {
        boolean result = false;
        // add only if customer exists
        if(customers.containsKey(customer)) {
            result = true;
        }
        return result;
    }

    // add new customer to hotel
    public boolean addCustomer(Customer customer) {
        boolean result = true;
        // add only if customer exists
        if(!this.customerExists(customer)) {
            this.customers.put(customer, new Bill(customer));
        } else {
            result = false;
        }
        return result;
    }

    // check if good is in list
    public boolean validGood(String itemName) {
        boolean result = false;
        for(Item i: this.goods) {
            if(i.getName().equals(itemName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // get the customer's bill
    public Bill getCustomerBill(Customer customer) {
        Bill bill = null;
        if(this.customerExists(customer)) {
            bill = customers.get(customer);
        }
        return bill;
    }

    // customer makes a transaction
    public void transaction(Customer customer, String item, int quantity) {
        if(this.customerExists(customer)) {
            Bill customerBill = customers.get(customer);

            boolean foundItem = false;
            for(Item i: this.goods) {
                if(i.getName().equals(item)) {
                    foundItem = true;
                    customerBill.purchaseItem(i, quantity);
                    break;
                }
            }

            if(!foundItem) {
                System.out.println(item + " could not be found!");
            }
        } else {
            System.out.println(customer.getName() + " is not in the hotel!");
        }
    }

    // customer deletes an item
    public void delete(Customer customer, String item) {
        if(this.customerExists(customer)) {
            Bill customerBill = customers.get(customer);

            boolean foundItem = false;
            for(Item i: this.goods) {
                if(i.getName().equals(item)) {
                    foundItem = true;
                    customerBill.removeItem(i);
                    break;
                }
            }

            if(!foundItem) {
                System.out.println(item + " could not be found!");
            }
        } else {
            System.out.println(customer.getName() + " is not in the hotel!");
        }
    }

    // print the customer's bill
    public void printCustomerBill(Customer customer) {
        if(this.customerExists(customer)) {
            customers.get(customer).printBill();
        }
        System.out.println(customer.getName() + " is not in the hotel.");
    }

    // print all bills
    public void printAllBills() {
        System.out.println("========= LIST OF CUSTOMER BILLS =========");
        System.out.println("\tCustomer\tTotal\t");
        System.out.println("---------------------------------");
        for(Map.Entry<Customer, Bill> entry: this.customers.entrySet()) {
            System.out.printf("\t%s\t\t$%.2f\t\n", entry.getKey().getName(),
                    entry.getValue().getTotal());
        }
        System.out.println(""); // line break
    }

    // print available goods
    public void printAvailableGoods() {
        System.out.println("========= LIST OF ITEMS =========");
        System.out.println("\tItem\tPrice");
        System.out.println("---------------------------------");
        for(Item i: this.goods) {
            System.out.printf("\t%s\t$%.2f\n", i.getName(), i.getPrice());
        }
        System.out.println(""); // line break
    }

}

