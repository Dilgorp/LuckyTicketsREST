package ru.dilgorp.java.tickets.lucky.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dilgorp.java.tickets.lucky.filter.Filter;
import ru.dilgorp.java.tickets.lucky.filter.FilterType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс конфигурации приложения
 */

@SuppressWarnings("unused")
@Configuration
public class AppConfig {

    /**
     * Возвращает карту, в которой заданному типу соответсвует свой фильтр
     * @param list список фильтров
     * @return карта соотвествия фильтров типам
     */
    @Bean
    @Autowired
    public Map<FilterType, Filter> filters(List<Filter> list) {
        Map<FilterType, Filter> map = new HashMap<>();
        list.forEach(f -> map.put(f.getFilterType(), f));
        return map;
    }
}
