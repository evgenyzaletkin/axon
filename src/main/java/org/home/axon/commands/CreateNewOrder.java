package org.home.axon.commands;

public class CreateNewOrder {
    private final String customer;
    private final String orderName;
    private final String description;

    public CreateNewOrder(String customer, String orderName, String description) {
        this.customer = customer;
        this.orderName = orderName;
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getDescription() {
        return description;
    }
}
