package org.home.axon.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandHandlerInterceptor;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.repository.GenericJpaRepository;
import org.axonframework.repository.Repository;
import org.home.axon.AxonCommandsService;
import org.home.axon.aggregates.Order;
import org.home.axon.commands.handlers.OrderCommandHandler;
import org.home.axon.events.handlers.OrderEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;

@Configuration
@DependsOn("ormConfiguration")
@EnableTransactionManagement
public class AxonConfiguration {


    private List<? extends CommandHandlerInterceptor> interceptors() {
        return Collections.singletonList(new BeanValidationInterceptor());
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

//    @Bean
//    public InMemoryRepository<Order> ordersRepository(EventBus eventBus) {
//        InMemoryRepository<Order> orderInMemoryRepository = new InMemoryRepository<>(Order.class);
//        orderInMemoryRepository.setEventBus(eventBus);
//        return orderInMemoryRepository;
//    }

    @Bean
    public AxonCommandsService axonCommandsService(CommandGateway commandGateway, EntityManagerFactory emf) {
        return new AxonCommandsService(commandGateway, emf);
    }

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setHandlerInterceptors(interceptors());
        return commandBus;
    }

    @Bean
    public OrderCommandHandler orderCommandHandler(Repository<Order> repository) {
        return new OrderCommandHandler(repository);
    }

    @Bean
    public OrderEventHandler orderEventHandler() {
        return new OrderEventHandler();
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor(CommandBus commandBus) {
        AnnotationCommandHandlerBeanPostProcessor bpp = new AnnotationCommandHandlerBeanPostProcessor();
        bpp.setCommandBus(commandBus);
        return bpp;
    }

    @Bean
    public AnnotationEventListenerBeanPostProcessor eventListenerBeanPostProcessor(EventBus eventBus) {
        AnnotationEventListenerBeanPostProcessor bpp = new AnnotationEventListenerBeanPostProcessor();
        bpp.setEventBus(eventBus);
        return bpp;
    }

    @Bean
    public CommandGateway commandGateway(CommandBus bus) {
        return new DefaultCommandGateway(bus);
    }


    @Bean
    public GenericJpaRepository<Order> jpaRepository(EntityManagerFactory emf) {
        return new GenericJpaRepository<>(new SimpleEntityManagerProvider(emf.createEntityManager()), Order.class);
    }




}
