package org.home.axon.events;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreatedEvent that = (OrderCreatedEvent) o;
        return Objects.equals(orderName, that.orderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderName);
    }
}
