package org.home.axon;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.home.axon.commands.CreateNewOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        CommandGateway gateway = context.getBean(CommandGateway.class);
        CreateNewOrder command = new CreateNewOrder("Vasya", "666", "From Hell");
        gateway.sendAndWait(command);
    }
}
