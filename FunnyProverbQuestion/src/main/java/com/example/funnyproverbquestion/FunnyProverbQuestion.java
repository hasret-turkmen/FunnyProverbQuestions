package com.example.funnyproverbquestion;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.PrintWriter;
import java.io.FileNotFoundException;


public class FunnyProverbQuestion {
    public static void main(String[] args) {
        //create objects from Proverbs, Antonyms ve Puns classes
        Proverbs proverbs = new Proverbs();
        Antonyms antonyms = new Antonyms();
        Puns puns = new Puns();

        //create a map from antonyms and add all antonyms to the map
        Map<String, String> antonymsList = new HashMap<>();
        for (String[] antonymPair : antonyms.turkishAntonyms) {
            antonymsList.put(antonymPair[0], antonymPair[1]);
        }

        //create a map from punPairs and add all punPairs to the map
        Map<String, String> changes = new HashMap<>();
        for (String[] changePair : puns.punPairs) {
            changes.put(changePair[0], changePair[1]);
        }

        PrintWriter writer = null;
        try {
            // Open the file (or create it if it doesn't exist) and associate the PrintWriter with it
            writer = new PrintWriter("funny_proverbs_output.txt");

            int i = 1;
            //for each proverb in turkishProverbs array
            for (String proverb : proverbs.turkishProverbs) {
                //make all characters lowercase since the antonyms and punPairs are in lowercase
                String originalProverb = proverb.toLowerCase();
                //System.out.println("Original Proverb: " + originalProverb);
                writer.println(i +": " + originalProverb); // Write to file

                Set<String> changedWords = new HashSet<>();
                //First round for the antonyms
                String modifiedProverb = applyTransformations(originalProverb, antonymsList, changedWords, true);

                //If no changes were made with antonyms, continue with changePairs
                if (changedWords.isEmpty()) {
                    //second round only if no changes were made with antonyms
                    modifiedProverb = applyTransformations(modifiedProverb, changes, changedWords, false);
                }

                String funnyQuestion = modifiedProverb + " " + determineQuestionSuffix(modifiedProverb) + "?";
                //System.out.println("Funny Question: " + funnyQuestion + "\n");
                writer.println(i +": " + funnyQuestion + "\n"); // Write to file
                i++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while trying to write to the file: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close(); // Make sure to close the writer to flush its content to the file
            }
        }
    }

    //icindeki sozcuklerin hicbirinde zit anlam yoksa atasozlerini tutsun, sonra sozlukten baksin
    //isimler ve sifatlar degissin
    private static String applyTransformations(String text, Map<String, String> transformations, Set<String> changedWords, boolean checkChanged) {
        //create a stringbuilder to modify the text
        StringBuilder modifiedTextBuilder = new StringBuilder();
        //split the text into words
        String[] words = text.split("\\s+");
        for (String word : words) {
            //if the word has been changed before and we need to check, skip
            //this is so that we don't change the same word twice
            if (checkChanged && changedWords.contains(word)) {
                modifiedTextBuilder.append(word).append(" ");
                continue;
            }
            String modifiedWord = word;
            //for each word in the text, check if it is in the transformations map
            //this is where the antonyms or punPairs are applied
            for (Map.Entry<String, String> entry : transformations.entrySet()) {
                //if the word is in the map, change it
                if (word.equals(entry.getKey())) {
                    modifiedWord = entry.getValue();
                    //if the word is changed, add it to the changedWords set
                    changedWords.add(word);
                    break;
                }
            }
            //add the modified word to the stringbuilder
            modifiedTextBuilder.append(modifiedWord).append(" ");
        }
        //return the modified text
        return modifiedTextBuilder.toString().trim();
    }

    //add question suffix with this method
    private static String determineQuestionSuffix(String text) {
        char lastVowel = ' ';
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if ("aeıioöuü".indexOf(c) >= 0) {
                lastVowel = c;
                break;
            }
        }

        switch (lastVowel) {
            case 'a':
            case 'ı':
                return "mı";
            case 'o':
            case 'u':
                return "mu";
            case 'e':
            case 'i':
                return "mi";
            case 'ö':
            case 'ü':
                return "mü";
            default:
                return "mi";
        }
    }
}