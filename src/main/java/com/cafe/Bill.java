package com.cafe;

import java.util.HashMap;

public class Bill {
    private final Customer customer;
    private double total;
    private final HashMap<Item, Integer> items;
    private boolean isPaid;

    public Bill(Customer customer) {
        this.customer = customer;
        this.total = 0.0;
        this.items = new HashMap<Item, Integer>();
        this.isPaid = false;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getTotal() {
        return this.total;
    }

    public HashMap<Item, Integer> getItems() {
        return this.items;
    }

    public boolean getIsPaid() {
        return this.isPaid;
    }

    public Integer getItemQuantity(Item item) {
        return this.items.getOrDefault(item, 0);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void purchaseItem(Item item, int quantity) {
        this.items.put(item, items.getOrDefault(item, 0) + quantity);
        this.total += (item.getPrice() * quantity);
    }

    public void removeItem(Item item) {
        this.total -= (item.getPrice() * getItemQuantity(item));
        this.items.remove(item);
    }

    public void makePayment() {
        this.isPaid = true;
    }
}

