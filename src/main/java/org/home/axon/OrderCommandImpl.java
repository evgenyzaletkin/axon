package org.home.axon;


public class OrderCommandImpl implements OrderCommand {
    private final String customer;
    private final String orderName;
    private final String description;

    public OrderCommandImpl(String customer, String orderName, String description) {
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
    public String toString() {
        return "OrderCommand{" +
                "customer='" + customer + '\'' +
                ", orderName='" + orderName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
