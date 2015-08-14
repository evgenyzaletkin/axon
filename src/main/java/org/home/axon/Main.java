package org.home.axon;

import org.home.axon.config.AxonConfiguration;
import org.home.axon.config.OrmConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AxonConfiguration.class, OrmConfiguration.class);
        AxonCommandsService commandsService = context.getBean(AxonCommandsService.class);
        commandsService.executeCommand();
    }
}
