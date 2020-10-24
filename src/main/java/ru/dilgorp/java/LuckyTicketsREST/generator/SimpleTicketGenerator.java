package ru.dilgorp.java.LuckyTicketsREST.generator;

import org.springframework.stereotype.Service;
import ru.dilgorp.java.LuckyTicketsREST.builder.TicketBuilder;
import ru.dilgorp.java.LuckyTicketsREST.filter.Filter;
import ru.dilgorp.java.LuckyTicketsREST.provider.TicketBuilderProvider;
import ru.dilgorp.java.LuckyTicketsREST.ticket.AbstractTicket;
import ru.dilgorp.java.LuckyTicketsREST.ticket.Lucky;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SimpleTicketGenerator implements TicketGenerator {

    private List<Lucky> tickets;

    private final int numberLength;
    private final Filter filter;
    private final TicketBuilderProvider ticketBuilderProvider;

    public SimpleTicketGenerator(
            int numberLength, Filter filter,
            TicketBuilderProvider ticketBuilderProvider) {
        this.ticketBuilderProvider = ticketBuilderProvider;
        if (numberLength < 2) {
            throw new IllegalArgumentException("Некорректная длина номера билета. Должно быть >= 2");
        }

        Objects.requireNonNull(filter);
        Objects.requireNonNull(ticketBuilderProvider);

        this.numberLength = numberLength;
        this.filter = filter;
        computeTickets();
    }

    /**
     * Выводит на экран все счастливые билеты.
     */
    @Override
    public void printAll() {
        tickets.forEach(System.out::println);
    }

    /**
     * Выводит на экран количество счастливых билетов.
     */
    @Override
    public void printCount() {
        System.out.printf("Lucky tickets count: %d%n", tickets.size());
    }

    /**
     * Возвращает список счастливых билетов
     *
     * @return список счастливых билетов
     */
    @Override
    public List<Lucky> getLuckyTickets() {
        return new ArrayList<>(tickets);
    }

    /**
     * Возвращает количество счастливых билетов
     *
     * @return количество счастливых билетов
     */
    @Override
    public int getLuckyTicketsCount() {
        return tickets.size();
    }

    private void computeTickets() {
        tickets = IntStream.range(0, (int) Math.pow(10d, numberLength))
                .parallel()
                .filter(filter::doFilter)
                .mapToObj(
                        n -> {
                            TicketBuilder builder = ticketBuilderProvider.getTicketBuilder();
                            return builder.setNumberLength(numberLength).setNumber(n).build();
                        }
                )
                .filter(AbstractTicket::isLucky)
                .collect(Collectors.toList());
    }
}