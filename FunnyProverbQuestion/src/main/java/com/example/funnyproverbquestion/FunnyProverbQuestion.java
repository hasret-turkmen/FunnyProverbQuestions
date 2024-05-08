package com.example.funnyproverbquestion;


import java.util.*;
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
                writer.println(i + ": " + originalProverb); // Write to file

                Set<String> changedWords = new HashSet<>();
// First, apply antonyms transformations
                String modifiedProverb = applyTransformations(originalProverb, antonymsList, changedWords, true);

// Check if the proverb has been changed by antonyms; if not, apply pun transformations.
                if (modifiedProverb.equals(originalProverb)) {
                    // Reset or create a new set for changedWords if you want to track changes separately for puns
                    changedWords.clear();
                    // Now indicate we're applying pun transformations with the second call
                    modifiedProverb = applyTransformations(originalProverb, changes, changedWords, false);
                }

                String funnyQuestion = modifiedProverb + " " + determineQuestionSuffix(modifiedProverb) + "?";
                //System.out.println("Funny Question: " + funnyQuestion + "\n");
                writer.println(i + ": " + funnyQuestion + "\n"); // Write to file
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

    private static String applyTransformations(String text, Map<String, String> transformations, Set<String> changedWords, boolean checkChanged) {
        String[] tokens = text.split("(?<=\\p{Punct})|(?=\\p{Punct})|\\s+");
        StringBuilder modifiedTextBuilder = new StringBuilder();

        // Determine the frequency of each word
        Map<String, Integer> wordCount = new HashMap<>();
        for (String token : tokens) {
            wordCount.put(token, wordCount.getOrDefault(token, 0) + 1);
        }

        for (String token : tokens) {
            String modifiedToken = token;

            // Check if the token has a transformation and if it appears more than once
            if (transformations.containsKey(token) && wordCount.get(token) > 1) {
                modifiedToken = transformations.get(token);
                changedWords.addAll(Collections.nCopies(wordCount.get(token), token)); // Track all occurrences as changed
            } else {
                // Attempt to get a transformation for the token, checking if changes are tracked
                if (!checkChanged || !changedWords.contains(token)) {
                    modifiedToken = transformations.getOrDefault(token, token);
                    if (!modifiedToken.equals(token)) {
                        changedWords.add(token); // Track the original token as changed
                    }
                }
            }

            // Append the modified token. If it's punctuation, don't add an extra space.
            if (modifiedTextBuilder.length() > 0 && !token.matches("\\p{Punct}")) {
                modifiedTextBuilder.append(" ");
            }
            modifiedTextBuilder.append(modifiedToken);
        }
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
