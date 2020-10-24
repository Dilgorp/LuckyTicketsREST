package ru.dilgorp.java.LuckyTicketsREST.ticket;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Набор тестов для класса {@link Ticket}
 */
public class TicketTest{

    /**
     * Проверяем, что конструктор генерирует билет для корректных данных (номер билета)
     */
    @Test
    public void ticketConstructorIsCorrect(){
        // when
        Lucky ticket = new Ticket(6, 10);

        // then
        assertNotNull(ticket);
    }

    /**
     * Проверяет, что если на вход в конструктор поступило отричательное число,
     * то выкидываем исключение {@link IllegalArgumentException}
     */
    @Test
    public void negativeTicket(){

        // when
        Exception exception = null;
        try {
            new Ticket(6, -1);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Билет не может иметь отрицательный номер!", exception.getMessage());
    }

    /**
     * Проверяет, что если на вход в конструктор поступило число выходящее за верхнюю границу,
     * то выкидываем исключение {@link IllegalArgumentException}
     */
    @Test
    public void beyondUpperLimit(){
        // when
        Exception exception = null;
        try {
            new Ticket(2, 100);
        } catch (Exception e) {
            exception = e;
        }

        // then
        assertNotNull(exception);
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals("Выход за верхнюю границу!", exception.getMessage());
    }

    /**
     * Проверяем, что конструктор генерирует билет для корректных данных (минимальный номер билета)
     */
    @Test
    public void ticketConstructorIsCorrectMinNumber(){
        // given
        int hash = Objects.hash(6) * 31 + Arrays.hashCode(new int[]{0, 0, 0, 0, 0, 0});

        // when
        Lucky ticket = new Ticket(6, 0);

        // then
        assertNotNull(ticket);
        assertEquals(hash, ticket.hashCode());
    }

    /**
     * Проверяем, корректность метода hashCode
     */
    @Test
    public void ticketHashCodeCorrect(){
        // given
        int hash = Objects.hash(6) * 31 + Arrays.hashCode(new int[]{0, 0, 0, 0, 0, 0});

        // when
        Lucky ticket = new Ticket(6, 0);

        // then
        assertEquals(hash, ticket.hashCode());
    }

    /**
     * Проверяем, что конструктор генерирует билет для корректных данных (максимальный номер билета)
     */
    @Test
    public void ticketConstructorIsCorrectMaxNumber(){
        // when
        Lucky ticket = new Ticket(6, 999999);

        // then
        assertNotNull(ticket);
        assertEquals("999999", ticket.toString());
    }

    /**
     * Проверяем, корректность работы метода toString
     */
    @Test
    public void ticketToStringCorrect(){
        // when
        Lucky ticket = new Ticket(6, 999999);

        // then
        assertEquals("999999", ticket.toString());
    }

    /**
     * Проверяем, что счастивый билет проверяется правильно
     */
    @Test
    public void ticketIsLuckyCorrect(){
        // when
        Lucky ticket = new Ticket(6, 123420);

        // then
        assertTrue(ticket.isLucky());
    }

    /**
     * Проверяем, что несчастивый билет проверяется правильно
     */
    @Test
    public void ticketIsNotLuckyCorrect(){
        // when
        Lucky ticket = new Ticket(6, 123421);

        // then
        assertFalse(ticket.isLucky());
    }
}