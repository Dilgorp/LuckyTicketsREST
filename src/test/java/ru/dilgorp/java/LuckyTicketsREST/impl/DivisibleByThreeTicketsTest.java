package ru.dilgorp.java.LuckyTicketsREST.impl;

import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.DivisibleByThreeTickets;

import static org.junit.Assert.*;

/**
 * Набор тестов для класса {@link DivisibleByThreeTickets}
 */
public class DivisibleByThreeTicketsTest {
    /**
     * Проверяем, что для чисел, делящихся на 3, возвращается true
     */
    @Test
    public void doFilterTrue(){
        // when
        Filter filter = new DivisibleByThreeTickets();

        // then
        assertTrue(filter.doFilter(3));
        assertTrue(filter.doFilter(27));
        assertTrue(filter.doFilter(1008));
        assertTrue(filter.doFilter(0));
        assertTrue(filter.doFilter(-6));
    }

    /**
     * Проверяем, что для чисел, не делящихся на 3, возвращается false
     */
    @Test
    public void doFilterFalse(){
        // when
        Filter filter = new DivisibleByThreeTickets();

        // then
        assertFalse(filter.doFilter(55));
        assertFalse(filter.doFilter(2552));
        assertFalse(filter.doFilter(1001));
        assertFalse(filter.doFilter(-17));
    }
}