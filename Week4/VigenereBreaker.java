import java.io.File;

/**
 * Assignment:
 * English Language, Known Key Length 
 * English Language, Unknown Key Length 
 * Multiple Languages
 * 
 * @version June 30, 2016
 */

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	
	/** The method returns a String consisting of every totalSlices-th character from message, 
	 *  starting at the whichSlice-th character.
	 */
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	StringBuilder s = new StringBuilder();
    	for ( int i = whichSlice; i< message.length() ; i+= totalSlices) {
    		s.append(message.charAt(i));
    	}
        return s.toString();
    }

    /** The method finds the shift for each index in the key */
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
}