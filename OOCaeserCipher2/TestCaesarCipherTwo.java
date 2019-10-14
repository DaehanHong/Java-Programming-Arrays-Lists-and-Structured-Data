import edu.duke.*;

/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    
    private int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
     private int maxIndex(int[] values){
        int max=0;
        for(int i=0; i< values.length;i++){
            if(values[i] > values[max]){
                max = i;
            }  
        }    
        return max;
    }

    
    private String halfOfString(String message, int start){
        String answer = "";   
        for (int k = start; k< message.length() ; k+= 2) {
            answer = answer + message.charAt(k);    	
        }
        return answer;
    }
    
    private int getKey(String s){
        int[] letterFreqs = countLetters(s);
        int maxindex = maxIndex(letterFreqs);
        int dkey = maxindex - 4;
        if (maxindex < 4) {
            dkey = 26 - (4-maxindex);
        }
        return dkey;
    }
    
    public void breakCaesarCipher(String input){
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        CaesarCipherTwo c = new CaesarCipherTwo(key1,key2);
        System.out.println("\nEncrypted message:" + input);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        System.out.println("Decrypted message:" + c.decrypt(input));
        //return encrypt(input);
    }
    
    
  public void simpleTest() {
         //FileResource fr = new FileResource();
         //String message = fr.asString();
         //CaesarCipherTwo c = new CaesarCipherTwo(17,3);
         //String encrypted = c.encrypt(message);
         //System.out.println(encrypted);
         //System.out.println(c.decrypt(encrypted));
         //breakCaesarCipher("Akag tjw Xibhr awoa aoee xakex znxag xwko");
         //breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
         
        // Question 2
	// Encrypt the following phrase with the algorithm described for using two Caesar Cipher keys, with key1 = 21 and key2 = 8.
        // Can you imagine life WITHOUT the internet AND computers in your pocket?
        // What is the encrypted string?
	
        //CaesarCipherTwo o2 = new CaesarCipherTwo(21, 8);
	//System.out.println("\n" + o2.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?" + "\n"));
	
	// Question 6
	// The following phrase was encrypted with the two-key encryption method we discussed using the two keys 14 and 24. What is the decrypted message?
	// "Hfs cpwewloj loks cd Hoto kyg Cyy."
	
	//CaesarCipherTwo o3 = new CaesarCipherTwo(14, 24);
	//System.out.println("\n" + o3.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));

	// Question 7
	// The following phrase was encrypted with the two-key encryption method described in this course. You will need to figure out which keys were used to encrypt it.
	// "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"
	// What is the original message?
	
	//breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
	
	// Question 8
	// Decrypt the encrypted file mysteryTwoKeysQuiz.txt.
	// This file is encrypted with the two-key encryption method we discussed. You�ll need to decrypt the complete file by figuring out which keys were used to decrypt it.
	// What are the first five decrypted words?
	FileResource fr = new FileResource();
	String message = fr.asString();
        String s1 = halfOfString(message, 0);
        String s2 = halfOfString(message, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("\nTwo keys found: key1= " + key1 + ", key2= " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1,key2);
        System.out.println("\nEncrypted message:" + message);
        System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
        System.out.println("Decrypted message:" + cc.decrypt(message));
  }
}