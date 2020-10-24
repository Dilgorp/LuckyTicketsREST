package ru.dilgorp.java.tickets.lucky.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.dilgorp.java.tickets.lucky.filter.Filter;
import ru.dilgorp.java.tickets.lucky.filter.impl.*;
import ru.dilgorp.java.tickets.lucky.generator.SimpleTicketGenerator;
import ru.dilgorp.java.tickets.lucky.generator.TicketGenerator;
import ru.dilgorp.java.tickets.lucky.provider.TicketBuilderProvider;

/**
 * Класс конфигурации приложения
 */

@SuppressWarnings("unused")
@Configuration
public class AppConfig {

    /**
     * @return длина билета
     */
    @Bean("sixDigitsTickets")
    @Primary
    public int numberLength() {
        return 6;
    }

    /**
     * @return длина билета
     */
    @Bean("fourDigitsTickets")
    public int fourDigitsLength() {
        return 4;
    }

    /**
     * @return текущий фильтр для билетов
     */
    @Bean("allTickets")
    @Primary
    public Filter filter() {
        return new AllTickets();
    }

    /**
     * @return фильтр для нечетных билетов
     */
    @Bean("oddTickets")
    public Filter oddTickets() {
        return new OddTickets();
    }

    /**
     * @return фильтр для четных билетов
     */
    @Bean("evenTickets")
    public Filter evenTickets() {
        return new EvenTickets();
    }

    /**
     * @return фильтр для билетов делящихся на 3
     */
    @Bean("divisibleByThreeTickets")
    public Filter divisibleByThreeTickets() {
        return new DivisibleByThreeTickets();
    }

    /**
     * @return фильтр для билетов делящихся на 5
     */
    @Bean("divisibleByFiveTickets")
    public Filter divisibleByFiveTickets() {
        return new DivisibleByFiveTickets();
    }

    @Bean("allTicketsGenerator")
    @Primary
    public TicketGenerator ticketGenerator(
            int length, Filter filter, TicketBuilderProvider provider
    ){
        return new SimpleTicketGenerator(length, filter, provider);
    }

    @Bean("sixLengthEvenTicketGenerator")
    @Primary
    public TicketGenerator sixLengthEvenTicketGenerator(
            @Qualifier("sixDigitsTickets") int length,
            @Qualifier("evenTickets") Filter filter,
            TicketBuilderProvider provider
    ){
        return new SimpleTicketGenerator(length, filter, provider);
    }

    @Bean("fourLengthEvenTicketGenerator")
    @Primary
    public TicketGenerator fourLengthEvenTicketGenerator(
            @Qualifier("fourDigitsTickets") int length,
            @Qualifier("evenTickets") Filter filter,
            TicketBuilderProvider provider
    ){
        return new SimpleTicketGenerator(length, filter, provider);
    }

    @Bean("fourLengthDivBy5Generator")
    @Primary
    public TicketGenerator fourLengthDivBy5Generator(
            @Qualifier("fourDigitsTickets") int length,
            @Qualifier("divisibleByFiveTickets") Filter filter,
            TicketBuilderProvider provider
    ){
        return new SimpleTicketGenerator(length, filter, provider);
    }

    @Bean("fourLengthDivBy3Generator")
    @Primary
    public TicketGenerator fourLengthDivBy3Generator(
            @Qualifier("fourDigitsTickets") int length,
            @Qualifier("divisibleByThreeTickets") Filter filter,
            TicketBuilderProvider provider
    ){
        return new SimpleTicketGenerator(length, filter, provider);
    }
}
