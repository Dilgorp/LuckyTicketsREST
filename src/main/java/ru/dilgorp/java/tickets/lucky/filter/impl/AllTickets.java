package ru.dilgorp.java.tickets.lucky.filter.impl;

import org.springframework.stereotype.Component;
import ru.dilgorp.java.tickets.lucky.filter.Filter;
import ru.dilgorp.java.tickets.lucky.filter.FilterType;

@Component
public class AllTickets implements Filter {
    @Override
    public boolean doFilter(int n) {
        return true;
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.ALL;
    }
}
