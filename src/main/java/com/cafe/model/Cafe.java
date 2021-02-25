package com.cafe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cafe {
    private List<Item> items;
    private List<Bill> bills;

    // stores customers and goods
    public Cafe() {
        this.items = new ArrayList<Item>();
        this.bills = new ArrayList<Bill>();
        populateGoods();
    }

    public void populateGoods() {
        this.items.add(new Item("coffee", 6.50));
        this.items.add(new Item("toast", 3.0));
        this.items.add(new Item("eggs", 1.50));
    }

    public List<Item> getItems() {
        return this.items;
    }

    // check if good is in list
    public Item getItem(String itemName) {
        Item item = null;
        for(Item i: this.items) {
            if(i.getName().equals(itemName)) {
                item = i;
                break;
            }
        }
        return item;
    }

    public List<Bill> getBills() {
        return this.bills;
    }

    // get customer's unpaid bill
    public Bill getUnpaidCustomerBill(Customer customer) {
        Bill bill = null;
        for(Bill b: this.bills) {
            if(b.getCustomer() == customer && !b.getIsPaid()) {
                bill = b;
                break;
            }
        }
        // create new bill if customer has no unpaid bills
        if(bill == null) {
            bill = new Bill(customer);
            this.bills.add(bill);
        }
        return bill;
    }

    // get all customer's bills
    public List<Bill> getAllCustomerBills(Customer customer) {
        List<Bill> customerBills = new ArrayList<Bill>();
        for(Bill b: this.bills) {
            if(b.getCustomer() == customer) {
                customerBills.add(b);
            }
        }
        return customerBills;
    }

    // customer buys an item
    public void transaction(Customer customer, String itemName, int quantity) {
        Item item = getItem(itemName);
        if(item != null && quantity > 0) {
            Bill customerBill = this.getUnpaidCustomerBill(customer);
            customerBill.purchaseItem(item, quantity);
        }

        // todo: throw error if itemName not found, quantity invalid
    }

    // customer deletes an item
    public void delete(Customer customer, String itemName) {
        Item item = getItem(itemName);
        Bill customerBill = this.getUnpaidCustomerBill(customer);
        if(item != null && customerBill != null) {
            customerBill.removeItem(item);
        }

        // todo: throw error if itemName not found, quantity invalid
    }

    public boolean payBill(Customer customer) {
        boolean success = false;
        Bill bill = this.getUnpaidCustomerBill(customer);
        if(bill != null) {
            bill.makePayment();
            success = true;
        }
        return success;
    }

}

