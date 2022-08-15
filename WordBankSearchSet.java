import java.util.HashSet;  
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * WordBankSearchArr class to help check for valid input  
 * based on the words in the wordbank file. 
 */
public class WordBankSearchSet {
    public static HashSet<String> getStringSet() throws FileNotFoundException {
        File f = new File("./jordle-bank.txt"); 
        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        
        Scanner sc = new Scanner(f); 
        HashSet<String> words = new HashSet<String>(); 
        while (sc.hasNextLine()) {
            words.add(sc.nextLine()); 
        } 
        sc.close(); 
        return words; 
    }

}
