package ru.dilgorp.java.tickets.lucky.filter.impl;

import org.springframework.stereotype.Component;
import ru.dilgorp.java.tickets.lucky.filter.Filter;
import ru.dilgorp.java.tickets.lucky.filter.FilterType;

@Component
public class DivisibleByFiveTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return n % 5 == 0;
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.FIVE;
    }
}
