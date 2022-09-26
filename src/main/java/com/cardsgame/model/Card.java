package com.cardsgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Card implements Comparable<Card> {
    private CardNumber number;
    private CardType type;

    public static Stack<Card> getAllCards() {
        Stack<Card> cardList = new Stack<Card>();

        for (CardType type : CardType.values()) {
            for (CardNumber number : CardNumber.values()) {
                Card cards = new Card(number, type);
                cardList.add(cards);
            }
        }
        return cardList;
    }

    public static void shuffleAllCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public int compareTo(Card o) {
        if (this.getNumber() == o.getNumber()) {
            return 0;
        } else if (this.getNumber().getCardOrder() > o.getNumber().getCardOrder()) {
            return -1;
        } else
            return 1;
    }

}

