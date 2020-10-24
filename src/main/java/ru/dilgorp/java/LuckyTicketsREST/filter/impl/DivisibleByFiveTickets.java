package ru.dilgorp.java.LuckyTicketsREST.filter.impl;

import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;

public class DivisibleByFiveTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return n % 5 == 0;
    }
}
