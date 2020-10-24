package ru.dilgorp.java.tickets.lucky.ticket;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class AbstractTicket implements Lucky {

    private final int numberLength;
    private final int[] digits;

    public AbstractTicket(int numberLength, int number) {
        if(number < 0){
            throw new IllegalArgumentException("Билет не может иметь отрицательный номер!");
        }

        if(number >= (int) Math.pow(10d, numberLength)){
            throw new IllegalArgumentException("Выход за верхнюю границу!");
        }

        this.numberLength = numberLength;
        this.digits = getDigits(number);
    }

    @Override
    public boolean isLucky() {
        int middle = digits.length / 2
                + ((digits.length % 2 == 0) ? 0 : 1);

        return Arrays.stream(digits, 0, middle).sum() ==
                Arrays.stream(digits, middle, digits.length).sum();
    }

    private int[] getDigits(int number) {
        int[] arrDigits = new int[numberLength];

        for (int j = number, count = 0; j > 0; j /= 10) {
            arrDigits[count++] = j % 10;
        }

        return reverseArray(arrDigits);
    }

    private int[] reverseArray(int[] digits) {
        int maxIdx = digits.length - 1;
        return IntStream.range(0, digits.length)
                .map(i -> digits[maxIdx - i])
                .toArray();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int digit : digits) {
            builder.append(digit);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTicket that = (AbstractTicket) o;
        return numberLength == that.numberLength &&
                Arrays.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberLength);
        result = 31 * result + Arrays.hashCode(digits);
        return result;
    }
}
