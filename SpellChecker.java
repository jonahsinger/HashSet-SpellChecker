/*
    Jonah Singer
    04/07/2023
    Spell Checker with HashSet
 */


import java.util.*;
import java.io.*;

public class SpellChecker {

    // HashSet to store dictionary
    private HashSet<String> dictHash = new HashSet<>();

    // Constructor
    public SpellChecker(String filename) {

        // Takes in file and prints error message if DNE
        File f = new File(filename);
        Scanner in = null;
        try{
            in = new Scanner(f);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        // makes HashSet of lowercase words with no punctuation
        HashSet<String> hash = new HashSet<>();
        // loops through the lines in file
        while(in.hasNextLine()){
            // takes in the line
            String line = in.nextLine();

            // removes white space
            String [] words = line.split("\\s+");

            // gets rid of punctuation
            for(String word : words){
                word = word.toLowerCase();
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                if(word.equals("")){
                    continue;
                }
                hash.add(word);
            }

        }
        in.close();

        this.dictHash = hash;

    }


    // returns a list of all words in the
    // input file that are incorrectly spelled
    // according to the dictionary file provided to the constructor.
    // The String filename contains the name of the file to be spell-checked.
    public List<String> getIncorrectWords(String filename){

        // ArrayList for misspelled words
        ArrayList<String> badWords = new ArrayList<>();

        // gets words from input file
        List<String> inputList = new ArrayList<>();

        // Takes in file and prints error message if DNE
        File f = new File(filename);
        Scanner in = null;
        try{
            in = new Scanner(f);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        // makes HashSet of lowercase words with no punctuation
        // loops through the lines in file
        while(in.hasNextLine()){
            // takes in the line
            String line = in.nextLine();

            // removes white space
            String [] words = line.split("\\s+");

            // gets rid of punctuation
            for(String word : words){
                word = word.toLowerCase();
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                inputList.add(word);
            }

        }
        in.close();


        // adds words not in dict to arraylist
        for(String word : inputList){
            if(word.equals("")){
              continue;  
            }
            if (!dictHash.contains(word)){
                badWords.add(word);
            }
        }

        return badWords;
    }




    // Remove one character - remove one character at a time from each position in the string
    // Swap adjacent characters - swap every pair of adjacent characters in the string
    public Set<String> getSuggestions(String word){
        Set<String> words = new HashSet<>();
        String tempAdd;
        word = word.toLowerCase();

        //adding letters in between chars
        for(int i = 1; i < word.length(); i++){

            // for letters in the middle
            for (char alphabet = 'a'; alphabet <= 'z'; alphabet++){

                tempAdd = word.substring(0, i) + alphabet + word.substring(i);

                // test if valid word if so add to array
                if(dictHash.contains(tempAdd)){
                    words.add(tempAdd);
                }
            }
        }

        // adding letters at the beginning
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++){
            // test letters at the beginning
            tempAdd = alphabet + word;

            // test if valid word if so add to array
            if(dictHash.contains(tempAdd)){
                words.add(tempAdd);
            }
        }

        // adding letters at the end
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++){

            tempAdd =  word + alphabet;

            // test if valid word if so add to array
            if(dictHash.contains(tempAdd)){
                words.add(tempAdd);
            }
        }

        // Removes one character at each position
        for(int i = 0; i < word.length(); i++){
            tempAdd = word.substring(0,i) + word.substring(i+1);

            // test if valid word if so add to array
            if(dictHash.contains(tempAdd)){
                words.add(tempAdd);
            }
        }

        //Swap adjacent characters - swap every pair of adjacent characters in the string
        for(int i = 0; i < word.length()-1; i++){
            tempAdd = word.substring(0,i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2);
            // test if valid word if so add to array
            if(dictHash.contains(tempAdd)){
                words.add(tempAdd);
            }
        }

        return words;
    }


}
