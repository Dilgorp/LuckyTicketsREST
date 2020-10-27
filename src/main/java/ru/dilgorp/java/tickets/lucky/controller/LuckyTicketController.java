package ru.dilgorp.java.tickets.lucky.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dilgorp.java.tickets.lucky.filter.Filter;
import ru.dilgorp.java.tickets.lucky.filter.FilterType;
import ru.dilgorp.java.tickets.lucky.generator.SimpleTicketGenerator;
import ru.dilgorp.java.tickets.lucky.generator.TicketGenerator;
import ru.dilgorp.java.tickets.lucky.provider.TicketBuilderProvider;
import ru.dilgorp.java.tickets.lucky.view.TicketListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для работы со счастливыми билетами
 */
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class LuckyTicketController {

    private final Map<FilterType, Filter> filters;
    private final TicketBuilderProvider ticketBuilderProvider;

    private final Map<Pair<Integer, FilterType>, TicketGenerator> cache = new HashMap<>();

    @GetMapping
    public TicketListView getTickets(
            @RequestParam(value = "number_length", required = false, defaultValue = "6") int numberLength,
            @RequestParam(value = "filter_type", required = false, defaultValue = "ALL") FilterType type
    ) {
        Pair<Integer, FilterType> key = new Pair<>(numberLength, type);
        TicketGenerator generator = cache.get(key);

        if (generator == null) {
            generator = new SimpleTicketGenerator(
                    numberLength, filters.get(type), ticketBuilderProvider
            );
            cache.put(key, generator);
        }

        return createView(generator);
    }

    /**
     * Возвращает представление
     *
     * @param ticketGenerator генератор счастливых билетов
     * @return представление
     */
    private TicketListView createView(TicketGenerator ticketGenerator) {
        Collection<String> tickets = new ArrayList<>();
        ticketGenerator.getLuckyTickets().forEach(t -> tickets.add(t.toString()));

        return TicketListView.builder()
                .tickets(tickets)
                .count(ticketGenerator.getLuckyTicketsCount())
                .build();
    }
}