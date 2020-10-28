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
import ru.dilgorp.java.tickets.lucky.ticket.Lucky;
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
            @RequestParam(value = "number_length", required = false, defaultValue = "6") final int numberLength,
            @RequestParam(value = "filter_type", required = false, defaultValue = "ALL") final FilterType type,
            @RequestParam(value = "bottom", required = false, defaultValue = "-1") final int bottom,
            @RequestParam(value = "top", required = false, defaultValue = "-1") final int top
    ) {
        Pair<Integer, FilterType> key = new Pair<>(numberLength, type);
        TicketGenerator generator = cache.get(key);

        if (generator == null) {
            generator = new SimpleTicketGenerator(
                    numberLength, filters.get(type), ticketBuilderProvider
            );
            cache.put(key, generator);
        }

        return createView(generator, bottom, top);
    }

    /**
     * Возвращает представление
     *
     * @param ticketGenerator генератор счастливых билетов
     * @param bottom          нижняя граница диапазона
     * @param top             верхняя граница диапазона
     * @return представление
     */
    private TicketListView createView(
            final TicketGenerator ticketGenerator,
            final int bottom,
            final int top
    ) {
        Collection<String> tickets = new ArrayList<>();
        ticketGenerator.getLuckyTickets()
                .stream()
                .filter(t -> ticketInRange(bottom, top, t))
                .forEach(t -> tickets.add(t.toString()));

        return TicketListView.builder()
                .tickets(tickets)
                .count(tickets.size())
                .build();
    }

    /**
     * Проверяет находится ли билет в указанном диапазоне
     *
     * @param bottom нижняя граница диапазона
     * @param top    верхняя граница диапазона
     * @param t      проверяемый билет
     * @return true, если билет соответствует диапазону
     */
    private boolean ticketInRange(
            final int bottom,
            final int top,
            final Lucky t
    ) {
        return (bottom <= -1 || t.getNumber() >= bottom) && (top <= -1 || t.getNumber() <= top);
    }
}