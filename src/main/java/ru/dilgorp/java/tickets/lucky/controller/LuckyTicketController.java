package ru.dilgorp.java.tickets.lucky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dilgorp.java.tickets.lucky.generator.TicketGenerator;
import ru.dilgorp.java.tickets.lucky.view.TicketListView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Контроллер для работы со счастливыми билетами
 */
@RestController
@RequestMapping("/tickets")
public class LuckyTicketController {

    private final TicketGenerator generator;
    private final TicketGenerator sixLengthEvenTicketGenerator;
    private final TicketGenerator fourLengthEvenTicketGenerator;
    private final TicketGenerator fourLengthDivBy3Generator;
    private final TicketGenerator fourLengthDivBy5Generator;

    @Autowired
    public LuckyTicketController(
            @Qualifier("allTicketsGenerator") TicketGenerator generator,
            @Qualifier("sixLengthEvenTicketGenerator") TicketGenerator sixLengthEvenTicketGenerator,
            @Qualifier("fourLengthEvenTicketGenerator") TicketGenerator fourLengthEvenTicketGenerator,
            @Qualifier("fourLengthDivBy3Generator") TicketGenerator fourLengthDivBy3Generator,
            @Qualifier("fourLengthDivBy5Generator") TicketGenerator fourLengthDivBy5Generator
    ) {
        this.generator = generator;
        this.sixLengthEvenTicketGenerator = sixLengthEvenTicketGenerator;
        this.fourLengthEvenTicketGenerator = fourLengthEvenTicketGenerator;
        this.fourLengthDivBy3Generator = fourLengthDivBy3Generator;
        this.fourLengthDivBy5Generator = fourLengthDivBy5Generator;
    }


    /**
     * @return набор счастливых билетов
     */
    @GetMapping
    public TicketListView getTickets() {
        return createView(generator);
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping("/six/even")
    public TicketListView getSixLengthEvenTickets() {
        return createView(sixLengthEvenTicketGenerator);
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping("/four/even")
    public TicketListView getFourLengthEvenTickets() {
        return createView(fourLengthEvenTicketGenerator);
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping("/four/by5")
    public TicketListView getFourLengthDivBy5Tickets() {
        return createView(fourLengthDivBy5Generator);
    }

    /**
     * @return набор счастливых билетов
     */
    @GetMapping("/four/by3")
    public TicketListView getFourLengthDivBy3Tickets() {
        return createView(fourLengthDivBy3Generator);
    }

    private TicketListView createView(TicketGenerator ticketGenerator){
        Collection<String> tickets = new ArrayList<>();
        ticketGenerator.getLuckyTickets().forEach(t -> tickets.add(t.toString()));

        return TicketListView.builder()
                .tickets(tickets)
                .count(ticketGenerator.getLuckyTicketsCount())
                .build();
    }
}
