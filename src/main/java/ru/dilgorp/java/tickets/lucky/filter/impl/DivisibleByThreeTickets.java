package ru.dilgorp.java.tickets.lucky.filter.impl;

import ru.dilgorp.java.tickets.lucky.filter.Filter;

public class DivisibleByThreeTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return n % 3 == 0;
    }
}
