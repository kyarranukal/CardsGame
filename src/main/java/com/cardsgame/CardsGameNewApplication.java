package com.cardsgame;

import com.cardsgame.model.Card;
import com.cardsgame.model.CardPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CardsGameNewApplication {

    private static final Logger log = LoggerFactory.getLogger(CardsGameNewApplication.class);

    public static int noOfPlayers = 4;
    public static int noOfCardsPerPlayer = 3;
    private static Map<CardPlayer, List<Card>> cardsPlayerMap = new HashMap<CardPlayer, List<Card>>();
    private static Stack<Card> cards;

    private static List<CardPlayer> players = new ArrayList<CardPlayer>();

    private static GameWinnerRules gameWinnerRules;
    public static void main(String[] args) {
        SpringApplication.run(CardsGameNewApplication.class, args);
        startGame();
    }

    public static void startGame(){
        /**
         * get the shuffled cards
         */
        cards = Card.getAllCards();
        log.info("CARDS LIST => " + cards);
        /**
         * Shuffle All cards to share shuffled cards to players
         */

        Card.shuffleAllCards(cards);
        log.info("SHUFFLED CARDS LIST => " + cards);

        /**
         create 4 players and share shuffled cards among them
         */

        createUsersAndShareCards();

        /**
         Perform validations to check who is the user
         */
        CardPlayer winner = finalizeWinner(cardsPlayerMap);
        log.info("Winner => "+winner);

    }

    public static CardPlayer finalizeWinner(Map<CardPlayer, List<Card>>  cardsPlayerMap){
        boolean allSame = gameWinnerRules.allAreSame(cardsPlayerMap);

        if(!allSame) {
            /**
             checking the sequence
             */
            if(!gameWinnerRules.isSequence(cardsPlayerMap)) {
                if (!gameWinnerRules.isTwoCardSame(cardsPlayerMap)) {
                    CardPlayer Winner = gameWinnerRules.TopOrderCard(cardsPlayerMap);
                    Winner.setResultOfPlayer("WINNER");
                    Winner.setPlayerPoints(2);
                }
                return gameWinnerRules.getWinner(cardsPlayerMap);
            } else {
                /**
                 * if two cards same
                 */
                return gameWinnerRules.getWinner(cardsPlayerMap);
            }
        } else {
            /**
             * if all cards same
             */
            return gameWinnerRules.getWinner(cardsPlayerMap);
        }
    }

    public static void createUsersAndShareCards() {
        for (int i = 1; i <= noOfPlayers; i++) {
            CardPlayer player = new CardPlayer(i);
            player.setCardPlayerName("Player"+i);
            players.add(player);

        }
        log.info("Players List With Initial Cards => " + players);
        distributeCardToPlayer();
        cardsPlayerMap.forEach((k,v) ->log.info("Player => "+ k.getCardPlayerName() + ", Cards => " + v));
    }

    public static boolean distributeCardToPlayer() {
        try {
            for (CardPlayer player : players) {
                List<Card> cardsPerUser = new ArrayList<Card>();
                for (int i = 1; i <= noOfCardsPerPlayer; i++) {
                    cardsPerUser.add(cards.pop());
                }
                Collections.sort(cardsPerUser);
                cardsPlayerMap.put(player, cardsPerUser);
            }
        } catch (Exception e) {
            log.error("Error while distributing cards to user = > "+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
