package ru.dilgorp.java.LuckyTicketsREST.impl;

import org.junit.Test;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.filter.impl.AllTickets;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Набор тестов для класса {@link AllTickets}
 */
public class AllTicketsTest {

    /**
     * Проверяем, что фильтр всегда возвращает true
     */
    @Test
    public void doFilter() {
        // when
        Filter filter = new AllTickets();

        //then
        assertTrue(filter.doFilter(0));
        assertTrue(filter.doFilter(-1));
        assertTrue(filter.doFilter(100000));
    }
}