/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokervaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dirc
 */
class Card{
    char suite;
    char rank;
    int rankValue;
    int suiteValue;
    int score;
    String rankIndex = " A23456789TJQK";
    String suiteIndex = "SHDC";
    
    public Card(String card){
        
        this.rank = card.charAt(0);
        this.suite = card.charAt(1);
        this.rankValue = rankIndex.indexOf(""+rank);
        this.suiteValue = suiteIndex.indexOf(""+suite);
        this.score = 13 * suiteValue + rankValue;
    }
    
    public String toString(){
        return new String(""+rank + suite);
    }
    
    public int compareValue(Card card){
        return (this.rankValue - card.rankValue);
    }
    
    public int compareScore(Card card){
        return (this.score - card.score);    
    }
}
public class PokerValuation {

    ArrayList<Card> hand;
    public PokerValuation(String cardsSet){
        hand = new ArrayList<Card>();
        parseInput(cardsSet);
    } 
    private void parseInput(String cardsSet){
        String[] cards = cardsSet.toUpperCase().split(",");
        for(String card: cards)
        {
                hand.add(new Card(card.trim()));
        }
    }
    public ArrayList<Card> sortByRank(){
        
        Collections.sort(hand,new Comparator<Card>(){
                                            @Override
                                            public int compare(Card o1, Card o2) {
                                                return (o1.compareValue(o2));
                                            }
                                            } );
        return hand;
    }
    public ArrayList<Card> sortByScore(){
        Collections.sort(hand,new Comparator<Card>(){
                                            @Override
                                            public int compare(Card o1, Card o2) {
                                                return (o1.compareScore(o2));
                                            }
                                            } );
        return hand;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
     PokerValuation poker = new PokerValuation("KS,AD,4C,4S,3S");
     System.out.println(poker.sortByRank());
    }
    
}
