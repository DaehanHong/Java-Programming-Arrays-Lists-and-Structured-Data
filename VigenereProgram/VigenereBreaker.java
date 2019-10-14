import java.io.File;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder s = new StringBuilder();
        for ( int i = whichSlice; i< message.length() ; i+= totalSlices) {
            s.append(message.charAt(i));
        }
        return s.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength ; i++) {
            String s = sliceString(encrypted, i, klength);
            int dkey = cc.getKey(s);
            key[i] = dkey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int [] key = tryKeyLength(message, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypt = vc.decrypt(message);
        System.out.println(decrypt);
    }
    
    public void breakVigenere2 () {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	FileResource fr2 = new FileResource("./src/assignment/week4/dictionaries/English");
    	HashSet<String> dictionary = readDictionary(fr2);
    	String decrypt = breakForLanguage(message, dictionary);
    	System.out.println(decrypt);
    }
    
    /**  The method returns the HashSet representing the words in a dictionary */
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> vocabularySet = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.toLowerCase();
            vocabularySet.add(line);
        }
        return vocabularySet;
    }
    
    /** The method returns the integer count of how many valid words in dictionary it found from message */
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for(String word: message.split("\\W+")) {
            word = word.toLowerCase();
            if(dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }
    
    /** This method should figure out which decryption gives the largest count of real words, 
     * and return that String decryption.
     */
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] wordcount = new int[100];
        for(int i = 0; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, i+1, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String result = vc.decrypt(encrypted);
            wordcount[i] = countWords(result, dictionary);
        }
        
        int largest = 0;
        int index = 0;
        for(int i = 0; i < 100; i++) {
            if(wordcount[i] > largest) {
                largest = wordcount[i];
                index = i;
            }
        }
        
        int truekey = index + 1;
        //int[] key = tryKeyLength(encrypted, truekey, 'e'); // for English
        char mostCommonChar = mostCommonCharIn(dictionary).charAt(0);
        int[] key = tryKeyLength(encrypted, truekey, mostCommonChar);
        System.out.print("The keys are ");
        for(int i = 0; i < key.length; i++){
            System.out.print(key[i] + " ");
        }
        System.out.println("\nThe key length is " + key.length);
        VigenereCipher vc = new VigenereCipher(key);
        return vc.decrypt(encrypted);
    }
    
    public String mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for(char ch = 'a'; ch <= 'z'; ++ch) {
            map.put(String.valueOf(ch), 0);
        }
        
        for(String word: dictionary) {
            word = word.toLowerCase();
            String[] letters = word.split("");
            for(String letter: map.keySet()) {
                for(String s : letters) {
                    if(s.equals(letter)) {
                        map.put(letter, map.get(letter)+1);
                    }
                }
            }
        }
        
        int max = 0;
        String result = "";
        
        for(Map.Entry<String, Integer> e: map.entrySet()) {
            if(max < e.getValue()) {
                max = e.getValue();
                result = e.getKey();
            }
        }
        return result;
    }
    
    public HashMap<String, String> breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        HashMap<String, String> decryptedMessages = new HashMap<String, String>();
        String language = "";
        int wordcount = 0;
        for(String lang: languages.keySet()) {
            System.out.println("Currently breaking into "+lang);
            String decrypted_string = breakForLanguage(encrypted, languages.get(lang));
            //System.out.println(decrypted_string);
            int count = countWords(decrypted_string, languages.get(lang));
            if (wordcount < count) {
                wordcount = count;
            	language = lang;
            }
            //System.out.println(count + " valid words\n");
            System.out.println();
            decryptedMessages.put(lang, decrypted_string);
        }
        System.out.println("The language of this message is " + language);
        System.out.println(wordcount + " valid words\n");
        return decryptedMessages;
    }
    
    public void breakVigenere3() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File d: dr.selectedFiles()) {
            FileResource fr2 = new FileResource(d.toString());
            HashSet<String> result = new HashSet<String>();
            for(String line : fr2.lines()) {
                line = line.toLowerCase();
                result.add(line);
            }
            languages.put(d.getName(), result);
            //System.out.println("Finished reading " + f.getName());
        }
        HashMap<String, String> decrypted = breakForAllLanguages(message, languages);
        //System.out.println(decrypted.get("English"));
    }
    
    public void tester1() {
        // Quiz 1
        VigenereBreaker v = new VigenereBreaker();
        FileResource fr = new FileResource("./messages/secretmessage1.txt");
        String message = fr.asString();
        int [] key = v.tryKeyLength(message, 4, 'e');
        for (Integer i: key)
            System.out.print(i + " ");
        System.out.println();
        // Quiz 2
        VigenereCipher vc = new VigenereCipher(key);
        String decrypt = vc.decrypt(message);
        System.out.println(decrypt);
    }
    
    public void tester2() {
        // Quiz 1
	VigenereBreaker v = new VigenereBreaker();
	FileResource fr = new FileResource("./messages/secretmessage2.txt");
	String message = fr.asString();
	FileResource fr2 = new FileResource("./dictionaries/English");
	HashSet<String> dictionary = v.readDictionary(fr2);
	String decrypt = v.breakForLanguage(message, dictionary);    
	
	// Quiz 2
	int count = v.countWords(decrypt, dictionary);
	System.out.println(count + " valid words are in the decrypted String.");
	
	// Quiz 3
	String [] lines = decrypt.split("\\r?\\n");
	System.out.println("The first line of text is " + lines[0]);
	
	// Quiz 4
	int [] key = v.tryKeyLength(message, 38, 'e');
	VigenereCipher vc = new VigenereCipher(key);
    	decrypt = vc.decrypt(message);
    	count = v.countWords(decrypt, dictionary);
    	System.out.println(count + " valid words are in the decrypted String.");
    }
    
    public void tester3() {
        // Quiz 1: French
	VigenereBreaker v = new VigenereBreaker();
	// 1. read secretmessage3.txt
	// 2. choose all the dictionaries
	v.breakVigenere3();
	
	// Quiz 2: La chambre à coucher de Juliette.
	FileResource fr = new FileResource("./messages/secretmessage3.txt");
	String message = fr.asString();
	FileResource fr2 = new FileResource("./dictionaries/French");
	HashSet<String> dictionary = v.readDictionary(fr2);
	String decrypt = v.breakForLanguage(message, dictionary);
	String [] lines = decrypt.split("\\r?\\n");
	System.out.println("The first line of text is " + lines[0] + "\n");
	
	// Quiz 3: German
	// 1. read secretmessage4.txt
	// 2. choose all the dictionaries
	v.breakVigenere3();
		
	// Quiz 4: Drei Hexen treten auf.
	fr = new FileResource("./messages/secretmessage4.txt");
	message = fr.asString();
	fr2 = new FileResource("./dictionaries/German");
	dictionary = v.readDictionary(fr2);
	decrypt = v.breakForLanguage(message, dictionary);
		lines = decrypt.split("\\r?\\n");
		System.out.println("The first line of text is " + lines[0] + "\n");
    }
}
