package com.cardsgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPlayer implements Comparable<CardPlayer> {
    private int cardPlayerId;
    private String cardPlayerName;
    private int playerPoints;
    private String resultOfPlayer;

    public CardPlayer(int cardPlayerId) {
        this.cardPlayerId = cardPlayerId;
    }

    public int compareTo(CardPlayer o) {
        if (this.getPlayerPoints() == o.getPlayerPoints()) {
            return 0;
        } else if (this.getPlayerPoints() > o.getPlayerPoints()) {
            return 1;
        } else
            return -1;
    }
}