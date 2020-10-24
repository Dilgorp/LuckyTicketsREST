package ru.dilgorp.java.LuckyTicketsREST.provider;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dilgorp.java.LuckyTicketsREST.builder.TicketBuilder;
import ru.dilgorp.java.LuckyTicketsREST.builder.impl.TicketBuilderImpl;

import static org.junit.Assert.assertNotNull;


/**
 * Тесты для класса {@link TicketBuilderProviderImpl}
 */
@RunWith(SpringRunner.class)
public class TicketBuilderProviderImplTest {

    @Configuration
    static class TestConfiguration{

        @Bean
        @Scope("prototype")
        TicketBuilder ticketBuilder(){
            return new TicketBuilderImpl();
        }

        @Bean
        TicketBuilderProvider ticketBuilderProvider(ApplicationContext context){
            TicketBuilderProviderImpl provider = new TicketBuilderProviderImpl();
            provider.setApplicationContext(context);
            return provider;
        }
    }

    @Autowired
    TicketBuilderProvider provider;

    @Test
    public void provideTicketBuilderCorrect(){
        assertNotNull(provider.getTicketBuilder());
    }
}