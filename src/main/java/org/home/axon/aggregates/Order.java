package org.home.axon.aggregates;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.home.axon.events.OrderCreatedEvent;

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    private String orderName;
    private String customer;
    private String description;

    public Order() {
    }

    public Order(String orderName, String customer, String description) {
        this.orderName = orderName;
        this.customer = customer;
        this.description = description;
        registerEvent(new OrderCreatedEvent(orderName));
    }

    /**
     * Returns the identifier of this aggregate.
     *
     * @return the identifier of this aggregate
     */
    public String getIdentifier() {
        return orderName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
