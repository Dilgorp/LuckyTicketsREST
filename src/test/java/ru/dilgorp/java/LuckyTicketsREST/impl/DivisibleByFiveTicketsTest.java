package ru.dilgorp.java.LuckyTicketsREST.impl;

import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.DivisibleByFiveTickets;

import static org.junit.Assert.*;

/**
 * Набор тестов для класса {@link DivisibleByFiveTickets}
 */
public class DivisibleByFiveTicketsTest {

    /**
     * Проверяем, что для чисел, делящихся на 5, возвращается true
     */
    @Test
    public void doFilterTrue(){
        // when
        Filter filter = new DivisibleByFiveTickets();

        // then
        assertTrue(filter.doFilter(5));
        assertTrue(filter.doFilter(255));
        assertTrue(filter.doFilter(1000));
        assertTrue(filter.doFilter(0));
        assertTrue(filter.doFilter(-5));
    }

    /**
     * Проверяем, что для чисел, не делящихся на 5, возвращается false
     */
    @Test
    public void doFilterFalse(){
        // when
        Filter filter = new DivisibleByFiveTickets();

        // then
        assertFalse(filter.doFilter(51));
        assertFalse(filter.doFilter(2553));
        assertFalse(filter.doFilter(1002));
        assertFalse(filter.doFilter(-6));
    }
}