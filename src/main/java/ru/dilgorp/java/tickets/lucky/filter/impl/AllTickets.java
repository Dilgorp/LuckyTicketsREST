package ru.dilgorp.java.tickets.lucky.filter.impl;

import ru.dilgorp.java.tickets.lucky.filter.Filter;

public class AllTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return true;
    }
}
