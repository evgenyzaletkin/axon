package org.home.axon.events.handlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.home.axon.events.OrderCreatedEvent;

public class OrderEventHandler {

    @EventHandler
    public void handleOrderCreation(OrderCreatedEvent event) {
        throw new RuntimeException("Hello mofos");
    }
}
