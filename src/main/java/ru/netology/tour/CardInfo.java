package ru.netology.tour;

import lombok.Value;

@Value
public class CardInfo {
    String number;
    String month;
    String year;
    String holder;
    String cvc;
}

