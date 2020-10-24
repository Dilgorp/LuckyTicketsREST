package ru.dilgorp.java.LuckyTicketsREST.filter.impl;

import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;

public class AllTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return true;
    }
}
