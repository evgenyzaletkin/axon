package org.home.axon.commands.handlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.home.axon.aggregates.Order;
import org.home.axon.commands.CreateNewOrder;
import org.home.axon.repositories.InMemoryRepository;

public class OrderCommandHandler {

    private final InMemoryRepository<Order> ordersRepository;

    public OrderCommandHandler(InMemoryRepository<Order> ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /**
     * Handles the given <code>command</code>.
     *
     * @param commandMessage The message carrying the command to process.
     * @return The result of the command processing, if any.
     * @throws Throwable any exception that occurs during command handling
     */
    @CommandHandler()
    public void handle(CreateNewOrder commandMessage) throws Throwable {
        Order order = new Order(commandMessage.getOrderName(), commandMessage.getCustomer(), commandMessage.getDescription());
        ordersRepository.add(order);
    }
}
