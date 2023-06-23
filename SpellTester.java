import java.util.*;

public class SpellTester {
    public static void main (String [] args){

        SpellChecker jonah = new SpellChecker("words.txt");
        List<String> ar = jonah.getIncorrectWords("testspell.txt");
        
        
        for(String word : ar){
            System.out.println("Word is: " + word + ". Suggestions are:");
            Set<String> test = jonah.getSuggestions(word);
            for(String i : test){
                System.out.println(i);
            }
            System.out.println("");
            
        }
        
    }
}
