package org.home.axon;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.home.axon.aggregates.Order;
import org.home.axon.commands.CreateNewOrder;
import org.home.axon.commands.handlers.OrderCommandHandler;
import org.home.axon.events.OrderCreatedEvent;
import org.home.axon.repositories.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class GenericTest {


    @Autowired
    private InMemoryRepository<Order> ordersRepository;


    FixtureConfiguration fixtureConfiguration;

    @Before
    public void setUp() throws Exception {
        fixtureConfiguration = Fixtures.newGivenWhenThenFixture(Order.class);
//        fixtureConfiguration.registerRepository(ordersRepository);
        OrderCommandHandler handler = new OrderCommandHandler(fixtureConfiguration.getRepository());
        fixtureConfiguration.registerAnnotatedCommandHandler(handler);

    }

    @Test
    public void orderCreateTest() throws Exception {
        String customer = "Vasya";
        String orderName = "555";
        String description = "Hey";
        fixtureConfiguration.given()
                .when(new CreateNewOrder(customer, orderName, description))
                .expectEvents(new OrderCreatedEvent(orderName));
    }
}
