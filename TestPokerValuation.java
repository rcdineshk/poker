package poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestPokerValuation {
	
	public String getHandType(String filename) throws IOException{
        PokerValuation poker;// = new PokerValuation("KS,AD,4C,4S,3S");
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line="";
        String result = "";
        while((line = br.readLine())!= null)
        { 
            poker = new PokerValuation(line);
            result += (line + " " + poker.decidePokerValuation()) + "\n";
        }
        return result;
    }
	
	public static void main(String args[])
	{
		TestPokerValuation obj = new TestPokerValuation(); 
	    try{
	         System.out.println(obj.getHandType("C:\\Users\\dsisodia\\Downloads\\testFile.txt"));
	     }catch(Exception ex){
	         ex.printStackTrace();
	     }
	}
}
