package ru.dilgorp.java.LuckyTicketsREST.impl;

import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.OddTickets;

import static org.junit.Assert.*;

/**
 * Набор тестов для класса {@link OddTickets}
 */
public class OddTicketsTest {

    /**
     * Проверяем, что для нечетных чисел возвращается true
     */
    @Test
    public void doFilterTrue(){
        // when
        Filter filter = new OddTickets();

        // then
        assertTrue(filter.doFilter(5));
        assertTrue(filter.doFilter(255));
        assertTrue(filter.doFilter(1003));
        assertTrue(filter.doFilter(1));
        assertTrue(filter.doFilter(-5));
    }

    /**
     * Проверяем, что для четных чискл возвращается false
     */
    @Test
    public void doFilterFalse(){
        // when
        Filter filter = new OddTickets();

        // then
        assertFalse(filter.doFilter(50));
        assertFalse(filter.doFilter(2554));
        assertFalse(filter.doFilter(1002));
        assertFalse(filter.doFilter(-6));
    }

}