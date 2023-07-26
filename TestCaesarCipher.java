import edu.duke.FileResource;

public class TestCaesarCipher {
    private int[] countLetters (String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }
        }
        return counts;
    }
    
    private int maxIndex (int[] values) {
        int indexMax = 0;
        int mostCommon = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > mostCommon) {
                mostCommon = values[i];
                indexMax = i;
            }
        }
        return indexMax;
    }
    
    private int findKey (String messsage) {
        int[] freqs = countLetters(messsage);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    private String breakCaesarCipher (String input) {
        int key = findKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println("Key = " + key);
        return cc.decrypt(input);
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println("Original message:\n" + message);
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted message:\n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message:\n" + decrypted);
        
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted message (frequency analysis):\n" + decrypted);
    }
}
