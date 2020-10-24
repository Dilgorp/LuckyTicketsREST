package ru.dilgorp.java.tickets.lucky.builder.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.dilgorp.java.tickets.lucky.builder.TicketBuilder;
import ru.dilgorp.java.tickets.lucky.ticket.AbstractTicket;
import ru.dilgorp.java.tickets.lucky.ticket.Ticket;

@Service("ticketBuilder")
@Scope("prototype")
public class TicketBuilderImpl implements TicketBuilder {

    private int numberLength;
    private int number;

    private boolean used;

    @Override
    public AbstractTicket build() {
        if (used) {
            throw new IllegalStateException("Данный строитель уже использовался ранее!");
        }
        used = true;
        return new Ticket(numberLength, number);
    }

    @Override
    public TicketBuilder setNumberLength(int numberLength) {
        this.numberLength = numberLength;
        return this;
    }

    @Override
    public TicketBuilder setNumber(int number) {
        this.number = number;
        return this;
    }
}
