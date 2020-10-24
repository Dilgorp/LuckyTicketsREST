package ru.dilgorp.java.tickets.lucky.filter;

public interface Filter {
    /**
     * Осуществляет фильтрацию заданного номера
     * @param n номер, который нужно отфильтровать
     * @return true, если номер удовлетворяет условию
     */
    boolean doFilter(int n);
}
