package org.home.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandHandlerInterceptor;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {


    private List<? extends CommandHandlerInterceptor> interceptors() {
        return Collections.singletonList(new BeanValidationInterceptor());
    }

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setHandlerInterceptors(interceptors());
        return commandBus;
    }

    @Bean
    public OrderCommandHandler orderCommandHandler() {
        return new OrderCommandHandler();
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor(CommandBus commandBus) {
        AnnotationCommandHandlerBeanPostProcessor bpp = new AnnotationCommandHandlerBeanPostProcessor();
        bpp.setCommandBus(commandBus);
        return bpp;
    }

    @Bean
    public CommandGateway commandGateway(CommandBus bus) {
        return new DefaultCommandGateway(bus);
    }





}
