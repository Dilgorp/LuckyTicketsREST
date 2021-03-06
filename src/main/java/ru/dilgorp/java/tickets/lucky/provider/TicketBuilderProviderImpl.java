package ru.dilgorp.java.tickets.lucky.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import ru.dilgorp.java.tickets.lucky.builder.TicketBuilder;

@Service
public class TicketBuilderProviderImpl implements TicketBuilderProvider, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public TicketBuilder getTicketBuilder() {
        return context.getBean("ticketBuilder", TicketBuilder.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        context = applicationContext;
    }
}
