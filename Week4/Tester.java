import edu.duke.FileResource;

public class Tester{
    public void test(){
		// Quiz 1
		VigenereBreaker v = new VigenereBreaker();
		FileResource fr = new FileResource("./messages/secretmessage1.txt");
		String message = fr.asString();
		int [] key = v.tryKeyLength(message, 4, 'e');
		for (Integer i: key)
			System.out.print(i + " ");
		System.out.println();
		// Quiz 2
		//VigenereCipher vc = new VigenereCipher(key);
    	//String decrypt = vc.decrypt(message);
    	//System.out.println(decrypt);
    }
}