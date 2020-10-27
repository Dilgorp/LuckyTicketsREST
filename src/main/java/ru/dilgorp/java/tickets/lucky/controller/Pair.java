package ru.dilgorp.java.tickets.lucky.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<V1, V2> {
    private V1 value1;
    private V2 value2;
}
