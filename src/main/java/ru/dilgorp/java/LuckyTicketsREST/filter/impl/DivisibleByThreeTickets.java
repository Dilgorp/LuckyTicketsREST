package ru.dilgorp.java.LuckyTicketsREST.filter.impl;

import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;

public class DivisibleByThreeTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return n % 3 == 0;
    }
}
