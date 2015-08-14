package org.home.axon;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.home.axon.aggregates.Order;
import org.home.axon.commands.CreateNewOrder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AxonCommandsService {

    private final CommandGateway gateway;
    private final EntityManagerFactory entityManagerFactory;

    public AxonCommandsService(CommandGateway gateway,
                               EntityManagerFactory entityManagerFactory) {
        this.gateway = gateway;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeCommand() {
        CreateNewOrder command = new CreateNewOrder("Vasya", "666", "From Hell");
        Order order = new Order();
        order.setDescription("From Hell");
        order.setCustomer("Vasya");
        order.setOrderName("555");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.flush();
        gateway.sendAndWait(command);

    }
}
