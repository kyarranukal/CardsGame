package com.cardsgame;

import com.cardsgame.model.Card;
import com.cardsgame.model.CardNumber;
import com.cardsgame.model.CardPlayer;
import com.cardsgame.model.CardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest()
class CardsGameNewApplicationTests {

    private static Map<CardPlayer, List<Card>> cardsPlayerMap = null;
    List<CardPlayer> players = null;
    GameWinnerRules gameWinnerRules;
    CardsGameNewApplication cardsGameNewApplication;

    @BeforeEach
    public void setUp() {
        cardsPlayerMap = new HashMap<CardPlayer, List<Card>>();
        players = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            CardPlayer player = new CardPlayer(i);
            player.setCardPlayerName("Player" + i);
            players.add(player);
        }
    }

    @Test
    @DisplayName("End to End Test case")
    void test_allCardsSame() {

            List<Card> cardsPerUser = new ArrayList<Card>();

            cardsPerUser.add(new Card(CardNumber.KING, CardType.CLUB));
            cardsPerUser.add(new Card(CardNumber.ACE, CardType.DIAMOND));
            cardsPerUser.add(new Card(CardNumber.JACK, CardType.SPADE));

            cardsPlayerMap.put(players.get(0), cardsPerUser);

        cardsPerUser = new ArrayList<Card>();

        cardsPerUser.add(new Card(CardNumber.THREE, CardType.CLUB));
        cardsPerUser.add(new Card(CardNumber.TWO, CardType.HEARTS));
        cardsPerUser.add(new Card(CardNumber.FOUR, CardType.SPADE));

        cardsPlayerMap.put(players.get(0), cardsPerUser);

        cardsPerUser = new ArrayList<Card>();

        cardsPerUser.add(new Card(CardNumber.KING, CardType.SPADE));
        cardsPerUser.add(new Card(CardNumber.ACE, CardType.HEARTS));
        cardsPerUser.add(new Card(CardNumber.THREE, CardType.SPADE));

        cardsPlayerMap.put(players.get(1), cardsPerUser);


        cardsPerUser = new ArrayList<Card>();

        cardsPerUser.add(new Card(CardNumber.THREE, CardType.CLUB));
        cardsPerUser.add(new Card(CardNumber.TWO, CardType.HEARTS));
        cardsPerUser.add(new Card(CardNumber.FOUR, CardType.SPADE));

        cardsPlayerMap.put(players.get(2), cardsPerUser);

        cardsPerUser = new ArrayList<Card>();

        cardsPerUser.add(new Card(CardNumber.THREE, CardType.CLUB));
        cardsPerUser.add(new Card(CardNumber.KING, CardType.HEARTS));
        cardsPerUser.add(new Card(CardNumber.FOUR, CardType.SPADE));

        cardsPlayerMap.put(players.get(3), cardsPerUser);


        CardPlayer player= CardsGameNewApplication.finalizeWinner(cardsPlayerMap);

        Assertions.assertEquals("Player1",player.getCardPlayerName());

    }
}
