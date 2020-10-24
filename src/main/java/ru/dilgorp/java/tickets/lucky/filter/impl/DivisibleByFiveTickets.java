package ru.dilgorp.java.tickets.lucky.filter.impl;

import ru.dilgorp.java.tickets.lucky.filter.Filter;

public class DivisibleByFiveTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return n % 5 == 0;
    }
}
