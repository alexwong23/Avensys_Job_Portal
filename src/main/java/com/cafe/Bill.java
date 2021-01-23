package com.cafe;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Customer customer;
    private HashMap<Item, Integer> items;
    private double total;

    public Bill(Customer customer) {
        this.customer = customer;
        this.items = new HashMap<Item, Integer>();
        this.total = 0.0;
    }

    public HashMap<Item, Integer> getItems() {
        return this.items;
    }

    public Integer getItemQuantity(Item item) {
        return this.items.getOrDefault(item, 0);
    }

    public double getTotal() {
        return this.total;
    }

    public void purchaseItem(Item item, int quantity) {
        this.items.put(item, items.getOrDefault(item, 0) + quantity);
        this.total += (item.getPrice() * quantity);
    }

    public void removeItem(Item item) {
        this.total -= (item.getPrice() * getItemQuantity(item));
        this.items.remove(item);
    }

    public void printBill() {
        System.out.printf("========= %s's BILL =========\n", this.customer.getName().toUpperCase());
        System.out.println("\tItem\tPrice\tSubtotal\t");
        System.out.println("---------------------------------");
        for(Map.Entry<Item, Integer> entry: this.items.entrySet()) {
            System.out.printf("\t%s\t%d\t$%.2f\t\n", entry.getKey().getName(),
                    entry.getValue(),
                    entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("---------------------------------");
        System.out.printf("\tTotal\t\t$%.2f\n", this.total);
    }
}

