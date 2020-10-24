package ru.dilgorp.java.LuckyTicketsREST.impl;

import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.EvenTickets;

import static org.junit.Assert.*;

/**
 * Набор тестов для класса {@link EvenTickets}
 */
public class EvenTicketsTest {

    /**
     * Проверяем, что для четных чисел возвращается true
     */
    @Test
    public void doFilterTrue(){
        // when
        Filter filter = new EvenTickets();

        // then
        assertTrue(filter.doFilter(2));
        assertTrue(filter.doFilter(32));
        assertTrue(filter.doFilter(64));
        assertTrue(filter.doFilter(0));
        assertTrue(filter.doFilter(-18));
    }

    /**
     * Проверяем, что для нечетных чисел возвращается false
     */
    @Test
    public void doFilterFalse(){
        // when
        Filter filter = new EvenTickets();

        // then
        assertFalse(filter.doFilter(51));
        assertFalse(filter.doFilter(2553));
        assertFalse(filter.doFilter(1003));
        assertFalse(filter.doFilter(-7));
    }
}