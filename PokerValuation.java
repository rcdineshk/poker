package poker;

import java.io.*;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Card{
    private char suit;
    private char rank;
    private int rankValue;
    private int suitValue;
    private int score;
    private String rankIndex = " A23456789TJQK";
    private String suiteIndex = "SHDC";
    
    public Card(String card){
        
        this.rank = card.charAt(0);
        this.suit = card.charAt(1);
        this.rankValue = rankIndex.indexOf(""+rank);
        this.suitValue = suiteIndex.indexOf(""+suit);
        this.score = 13 * suitValue + rankValue;
    }
    
    
    public String toString(){
        return new String(""+rank + suit);
    }
    
    public int compareValue(Card card){
        return (this.rankValue - card.rankValue);
    }
    
    public int compareScore(Card card){
        return (this.score - card.score);    
    }
    
    public String getRank(){
        return new String(""+this.rank);
    }
    
    public int getRankValue(){
        return this.rankValue;
    }
    
    public String getSuite(){
        return new String(""+this.suit);
    }
    
    public int getSuitValue(){
        return this.suitValue;
    }
}

class PokerValuation
{

    ArrayList<Card> hand;
    
    public PokerValuation(String cardsSet)
    {
        hand = new ArrayList<Card>();
        parseInput(cardsSet);
    } 
 
    private void parseInput(String cardsSet)
    {
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
    
    public ArrayList<Card> sortByScore()
    {
        Collections.sort(hand,new Comparator<Card>(){
                                            @Override
                                            public int compare(Card o1, Card o2) {
                                                return (o1.compareScore(o2));
                                            }
                                            } );
        return hand;
    }
    
    public boolean isRoyalFlush()
    {
        return isStraightFlush() && hand.get(4).getRankValue() == 13 && hand.get(0).getRankValue() == 1;
    }
	 
	 public boolean isStraightFlush()
    {
        return isFlush() && isStraight();
    }
	 
	 public boolean isFourOfAKind()
    {
        
        int commonRank = hand.get(2).getRankValue();
        if (hand.get(0).getRankValue() == commonRank || hand.get(4).getRankValue() == commonRank)
        {
            if (hand.get(1).getRankValue() == commonRank && hand.get(3).getRankValue() == commonRank)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFullHouse()
    {
        //Sort cards by rank while implementation
        if (hand.get(0).getRankValue() == hand.get(1).getRankValue() && hand.get(3).getRankValue() == hand.get(4).getRankValue())
        {
            if (hand.get(2).getRankValue() == hand.get(1).getRankValue() || hand.get(2).getRankValue() == hand.get(3).getRankValue())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFlush()
    {
        int commonSuit = hand.get(0).getSuitValue();
        if (hand.get(1).getSuitValue() == commonSuit && hand.get(2).getSuitValue() == commonSuit && hand.get(3).getSuitValue() == commonSuit && hand.get(4).getSuitValue() == commonSuit)
        {
            return true;
        }
        return false;
    }
    
   
    
    
   public boolean isStraight()
   {
	   int start = 0;
	   if(hand.get(0).getRankValue() == 1 && hand.get(1).getRankValue() == 10)
   	{
   		start = 2;
   	}
   	for( int i = start ; i < hand.size()-1 ; i++ )
   	{
   		if(hand.get(i+1).getRankValue()-hand.get(i).getRankValue() != 1)
   		{
   			return false;
   		}
   	}
   	return true;
   }
   
   public boolean isThreeOfAKind()
   {
       for(int i= 0 ; i < hand.size()-2 ; i ++)
       {
       if(hand.get(i).getRankValue() == hand.get(i+2).getRankValue())
        	{
       			return true;
        	}
       }
       
       	return false;
       
   }
   
   public boolean isTwoPair()
   {
   	int two_pair_check=0;
       for(int i= 0 ; i < hand.size()-1 ; i ++)
       {
       if(hand.get(i).getRankValue() == hand.get(i+1).getRankValue())
        	{
       			two_pair_check+=1;
        	}
       }
       if(two_pair_check == 2)
       {
       		return true;
       }
       return false;
       
   }
   
   public boolean isOnePair()
   {
       for(int i= 0 ; i < hand.size()-1 ; i ++)
       {
       if(hand.get(i).getRankValue() == hand.get(i+1).getRankValue())
        {
       	return true;
        }
       }
       return false;
       
   }
   
   public String decidePokerValuation()
   {
	   sortByRank();
	   if (isRoyalFlush())
	   {
		   return "Royal Flush";
	   }
	   else if(isStraightFlush())
	   {
		   return "Straight Flush";
	   }
	   else if(isFourOfAKind())
	   {
		   return "Four Of A Kind";
	   }
	   else if(isFullHouse())
	   {
		   return "Full House";
	   }
	   else if(isFlush())
	   {
		   return "Flush";
	   }
	   else if(isStraight())
	   {
		   return "Straight";
	   }
	   else if(isThreeOfAKind())
	   {
		   return "Three Of A Kind";
	   }
	   else if(isTwoPair())
	   {
		   return "Two Pair";
	   }
	   else if(isOnePair())
	   {
		   return "One Pair";
	   }
	   else
	   {
		   return "High Card";
	   }
   }  
}
