import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Wordstats {

    public static void main(String[] args)
    {
        File dataFile = new File("stats.txt");
        FileReader in;
        BufferedReader readFile;
        String line;
        String words[];
        int length = 0; // the largest length of a word
        Map<String, Integer> dts = new HashMap<String, Integer>();
        ArrayList<String> allWords = new ArrayList<String>();

        try {
            in = new FileReader(dataFile);
            readFile = new BufferedReader(in);
            while ((line = readFile.readLine()) != null) {
                words = line.split(" ");
                for(String word : words)
                {
                    if (dts.containsKey(word.toLowerCase())) dts.put(word.toLowerCase(), dts.get(word.toLowerCase())+1);
                    else 
                    {
                        dts.put(word.toLowerCase(), 1);
                        allWords.add(word.toLowerCase());
                        length = Math.max(word.length(), length);
                    }
                }
            }
            quickSort(allWords, 0, allWords.size()-1);
            System.out.print("WORD");
            String tmp = String.format("%"+Integer.toString(length)+"s", "OCCURENCES");
            System.out.print(tmp+"\n");
            for(String word:allWords)
            {
                System.out.print(word);
                tmp = String.format("%"+Integer.toString((length+4-word.length()))+"s", dts.get(word));
                System.out.print(tmp+"\n");
            }
        } catch (IOException e)
        {
            System.out.println("Problem reading file.");
            System.out.println("IOException: " +e.getMessage());
        }
    }

    
    private static void quickSort(ArrayList<String> a, int start, int end)
    {
        int i = start;
        int j = end;
        if (j - i >= 1)
        {
        String pivot = a.get(i);
        while (j > i)
        {
            while (a.get(i).compareTo(pivot) <= 0 && i <= end && j > i){i++;}
            while (a.get(j).compareTo(pivot) >= 0 && j >= start && j >= i){j--;}
            if (j > i) Collections.swap(a, i, j);
        }

        Collections.swap(a, start, j);
        quickSort(a, start, j - 1);
        quickSort(a, j + 1, end);
        }
    }
}