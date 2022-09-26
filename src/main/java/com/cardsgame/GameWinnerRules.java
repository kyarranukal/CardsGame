package com.cardsgame;

import com.cardsgame.model.Card;
import com.cardsgame.model.CardPlayer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GameWinnerRules {
    public static boolean allAreSame(Map<CardPlayer, List<Card>>  cardPlayers) {

        for (Map.Entry<CardPlayer, List<Card>> player :cardPlayers.entrySet()) {
            CardPlayer p = player.getKey();
            List<Card> cards = player.getValue();
            if (isAllCardSame(cards)) {
                p.setResultOfPlayer("WINNER");
                p.setPlayerPoints(1);
                return true;
            }
        }

        return false;
    }

    public static boolean isAllCardSame(List<Card> card) {

        if (card.size() <= 1)
            return false;

        int numberSame = (card.size() >= 4) ? 4 : card.size();
        Collections.sort(card);

        Card c = card.get(0);
        boolean isallMatch = true;
        for (int i = 1; i < numberSame; i++) {
            if (!c.getNumber().equals(card.get(i).getNumber())) {
                isallMatch = false;
                break;
            }
        }

        return isallMatch;

    }
    public static boolean isSequence(Map<CardPlayer, List<Card>>  cardPlayers) {

        for (Map.Entry<CardPlayer, List<Card>> player :cardPlayers.entrySet()) {

            CardPlayer p = player.getKey();
            List<Card> cards = player.getValue();
            if (isCardInSequence(cards)) {
                p.setResultOfPlayer("WINNER");
                p.setPlayerPoints(1);

                return true;

            }

        }

        return false;
    }

    public static boolean isTwoCardSame(Map<CardPlayer, List<Card>>  cardPlayers) {

        for (Map.Entry<CardPlayer, List<Card>> player :cardPlayers.entrySet()) {
            CardPlayer p = player.getKey();
            List<Card> cards = player.getValue();
            if (twoCardSame(cards)) {
                p.setResultOfPlayer("WINNER");
                p.setPlayerPoints(1);

                return true;

            }

        }

        return false;
    }

    public static boolean isCardInSequence(List<Card> card) {

        if (card.size() <= 1)
            return false;

        Collections.sort(card);
        boolean isallMatch = true;
        int cardOrder = card.get(0).getNumber().getCardOrder();
        for (int k = 1; k < card.size(); k++) {
            int p = card.get(k).getNumber().getCardOrder();
            //System.out.println("cardOrder"+ cardOrder);
            //System.out.println("p"+ p);
            if (cardOrder == 14) {
                if (p == 4 || p == 13) {
                    cardOrder = p;
                } else {
                    isallMatch = false;
                    break;
                }
            } else if ( (cardOrder - 1) !=p ){
                isallMatch = false;
                break;

            } else {
                cardOrder = p;
            }

        }
        return isallMatch;

    }

    public static boolean twoCardSame(List<Card> card) {

        if (card.size() <= 1)
            return false;

        Collections.sort(card);
        boolean isallMatch = false;
        //System.out.println();
        for (int k = 1 ; k < card.size() ; k++) {
            int cardOrder = card.get(k-1).getNumber().getCardOrder();
            int p = card.get(k).getNumber().getCardOrder();
            if(cardOrder == p) {
                isallMatch= true;
                break;
            }

        }
        return isallMatch;

    }

    public static CardPlayer TopOrderCard(Map<CardPlayer, List<Card>>  cardPlayers) {
        int highvalue=-1;
        CardPlayer highPlayer=new CardPlayer();
        for(int i=0;i<CardsGameNewApplication.noOfCardsPerPlayer;i++) {

            for (Map.Entry<CardPlayer, List<Card>> player :cardPlayers.entrySet()) {
                CardPlayer p = player.getKey();
                List<Card> cards = player.getValue();
                int value = cards.get(i).getNumber().getCardOrder();
                if(value > highvalue)  {
                    highvalue=value;
                    highPlayer=p;
                }
                for (Map.Entry<CardPlayer, List<Card>> inPlayer :cardPlayers.entrySet()) {
                    CardPlayer p1 = inPlayer.getKey();
                    List<Card> incards = player.getValue();
                    if(p1.equals(p)) {
                        int value2 = incards.get(i).getNumber().getCardOrder();
                        if(value==value2 && highvalue < value2) {
                            highvalue=value;
                            highPlayer=p1;
                        } else if(value!=value2 && value < value2) {
                            highvalue=value2;
                            highPlayer=p1;
                        }
                    }
                }
            }
        }
        return highPlayer;
    }
    public static CardPlayer getWinner(Map<CardPlayer, List<Card>>  cardPlayers) {
        CardPlayer winner = new CardPlayer();
        for (CardPlayer player : cardPlayers.keySet()) {
            if (player.getResultOfPlayer() != null && player.getResultOfPlayer().equals("WINNER")) {
                winner = player;
                break;
            }

        }
return winner;
    }
}
