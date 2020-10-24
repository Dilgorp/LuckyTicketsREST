package ru.dilgorp.java.tickets.lucky.view;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class TicketListView {
    private final int count;
    private final Collection<String> tickets;
}
