package org.home.axon.events;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable {
    private final String orderName;

    public OrderCreatedEvent(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderName() {
        return orderName;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderName='" + orderName + '\'' +
                '}';
    }
}
