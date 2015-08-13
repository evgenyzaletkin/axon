package org.home.axon.events;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable {
    private final String OrderName;

    public OrderCreatedEvent(String orderName) {
        OrderName = orderName;
    }

    public String getOrderName() {
        return OrderName;
    }
}
