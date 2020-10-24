package ru.dilgorp.java.LuckyTicketsREST.generator;

import ru.dilgorp.java.LuckyTicketsREST.ticket.Lucky;

import java.util.List;

public interface TicketGenerator {
    /**
    * Выводит на экран все счастливые билеты.
     */
    void printAll();

    /**
    * Выводит на экран количество счастливых билетов.
     */
    void printCount();

    /**
     * Возвращает список счастливых билетов
     * @return список счастливых билетов
     */
    List<Lucky> getLuckyTickets();

    /**
     * Возвращает количество счастливых билетов
     * @return количество счастливых билетов
     */
    int getLuckyTicketsCount();
}
