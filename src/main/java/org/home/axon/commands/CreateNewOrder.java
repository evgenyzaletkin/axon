package org.home.axon.commands;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateNewOrder that = (CreateNewOrder) o;
        return Objects.equals(orderName, that.orderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderName);
    }
}
