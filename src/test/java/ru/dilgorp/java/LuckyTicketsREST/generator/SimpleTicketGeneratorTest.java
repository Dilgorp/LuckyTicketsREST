package ru.dilgorp.java.LuckyTicketsREST.generator;

import org.junit.Before;
import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.builder.impl.TicketBuilderImpl;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.AllTickets;
import ru.dilgorp.java.LuckyTicketsREST.provider.TicketBuilderProvider;
import ru.dilgorp.java.LuckyTicketsREST.ticket.Lucky;
import ru.dilgorp.java.LuckyTicketsREST.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SimpleTicketGeneratorTest {

    private TicketBuilderProvider ticketBuilderProvider;

    @Before
    public void initTicketBuilderProvider(){
//        ticketBuilderProvider = Mockito.mock(TicketBuilderProvider.class);
//        Mockito.when(ticketBuilderProvider.getTicketBuilder())
//                .thenReturn(new TicketBuilderImpl());
        ticketBuilderProvider = TicketBuilderImpl::new;
    }

    /**
     * Проверяем, что генератор создается
     */
    @Test
    public void generatorConstructorIsCorrect(){
        // when
        TicketGenerator generator = new SimpleTicketGenerator(2, new AllTickets(), ticketBuilderProvider);

        //then
        assertNotNull(generator);
    }

    /**
     * Проверяем, что если на вход в конструктор будет подано отрицательно число,
     * то вылетит {@link IllegalArgumentException}
     */
    @Test
    public void negativeLengthException(){
        // when
        Exception exception = null;
        try {
            new SimpleTicketGenerator(-2, new AllTickets(), ticketBuilderProvider);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Некорректная длина номера билета. Должно быть >= 2", exception.getMessage());
    }

    /**
     * Проверяем, что если на вход в конструктор будет подано недопустимое число,
     * то вылетит {@link IllegalArgumentException}
     */
    @Test
    public void incorrectLengthException(){
        // when
        Exception exception = null;
        try {
            new SimpleTicketGenerator(1, new AllTickets(), ticketBuilderProvider);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Некорректная длина номера билета. Должно быть >= 2", exception.getMessage());
    }

    /**
     * Проверяем, что если на вход в конструктор будет подано null в качестве фильтра,
     * то вылетит {@link NullPointerException}
     */
    @Test
    public void nullFilterException(){
        // when
        Exception exception = null;
        try {
            new SimpleTicketGenerator(2, null, ticketBuilderProvider);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertEquals(NullPointerException.class, exception.getClass());
    }

    /**
     * Проверяем, что список билетов формируется коректно
     */
    @Test
    public void ticketListCreatedCorrect(){
        // given
        TicketGenerator generator = new SimpleTicketGenerator(2, new AllTickets(), ticketBuilderProvider);
        List<Lucky> tickets = new ArrayList<>();
        tickets.add(new Ticket(2, 0));
        tickets.add(new Ticket(2, 11));
        tickets.add(new Ticket(2, 22));
        tickets.add(new Ticket(2, 33));
        tickets.add(new Ticket(2, 44));
        tickets.add(new Ticket(2, 55));
        tickets.add(new Ticket(2, 66));
        tickets.add(new Ticket(2, 77));
        tickets.add(new Ticket(2, 88));
        tickets.add(new Ticket(2, 99));

        // when
        List<Lucky> generatedTickets = generator.getLuckyTickets();

        // then
        assertEquals(tickets, generatedTickets);
    }

    /**
     * Проверяем, что количество билетов формируется коректно
     */
    @Test
    public void ticketCountCreatedCorrect(){
        // given
        TicketGenerator generator = new SimpleTicketGenerator(2, new AllTickets(), ticketBuilderProvider);

        // when
        int ticketCount = generator.getLuckyTicketsCount();

        // then
        assertEquals(10, ticketCount);
    }
}