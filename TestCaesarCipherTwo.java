import edu.duke.FileResource;

public class TestCaesarCipherTwo {
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
    
    private String[] separateString (String message) {
        String[] halves = new String[2];
        for (int i = 0; i < message.length(); i++) {
            if (i % 2 == 0) {
                halves[0] = halves[0] + message.charAt(i);
            }
            else {
                halves[1] = halves[1] + message.charAt(i);
            }
        }
        return halves;
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
        String[] halves = separateString(input);
        int key1 = findKey(halves[0]);
        int key2 = findKey(halves[1]);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        System.out.println("Key 1 = " + key1 + " Key 2 = " + key2);
        return cct.decrypt(input);
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key1 = 21;
        int key2 = 8;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        System.out.println("Original message:\n" + message);
        String encrypted = cct.encrypt(message);
        System.out.println("Encrypted message:\n" + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("Decrypted message:\n" + decrypted);
        
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted message (frequency analysis):\n" + decrypted);
    }
}
