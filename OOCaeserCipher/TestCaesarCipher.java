import edu.duke.*;

/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {

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
    
    public void breakCaesarCipher(String input){
        int[] letterFreqs = countLetters(input);
        int maxindex = maxIndex(letterFreqs);
        int dkey = maxindex - 4;
        if (maxindex < 4) {
            dkey = 26 - (4-maxindex);
        }
        CaesarCipher c = new CaesarCipher(dkey);
        System.out.println("Encrypted message:\n" + input);
        System.out.println("\nkey:" + dkey);
        System.out.println("\nDecrypted message:\n" + c.decrypt(input));
    }
    
    public void simpleTests(){
       //FileResource fr = new FileResource();
       //String message = fr.asString();
       //CaesarCipher c = new CaesarCipher(18);
       //String encrypted = c.encrypt(message);
       //System.out.println(encrypted);
       //System.out.println(c.decrypt(encrypted));
       //breakCaesarCipher("XJWW USCW AF LZW UGFXWJWFUW JGGE!");
       
       // Question 1
       // Encrypt the following phrase with the Caesar Cipher algorithm, using key 15.
       // "Can you imagine life WITHOUT the internet AND computers in your pocket?"
       // What is the encrypted string?
       CaesarCipher o1 = new CaesarCipher(15);
       System.out.println(o1.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
    }
}