import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Wordsearch {
    public static void main(String[] args) {
        try {
            String line;
            BufferedReader readFile = new BufferedReader(new FileReader(new File("boardgame.txt")));
            int size = Integer.parseInt(readFile.readLine());
            String input[] = new String[size];
            String verticle[] = new String[size];
            String diagonal[] = new String[2 * size - 1];
            String reversediagonal[] = new String[2 * size - 1];
            int m = 0;
            while ((line = readFile.readLine()) != null) {
                input[m] = line.toUpperCase();
                m++;
            }
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    verticle[j] = (verticle[j] != null) ? verticle[j] + input[i].charAt(j) : "" + input[i].charAt(j);
            for (int i = 0; i < size; i++)
                for (int j = 0; j <= i; j++) {
                    diagonal[i] = (diagonal[i] != null) ? diagonal[i] + verticle[j].charAt(size - i - 1 + j)
                            : "" + verticle[j].charAt(size - i - 1 + j);
                    reversediagonal[i] = (reversediagonal[i] != null)
                            ? reversediagonal[i] + input[size-1-i+j].charAt(size - 1 - j)
                            : "" + input[size-1-i+j].charAt(size - 1 + j);
                }
            for (int i = 1; i < size; i++)
                for (int j = 0; j <= size - i - 1; j++) {
                    diagonal[size + i - 1] = (diagonal[size + i - 1] != null)
                            ? diagonal[size + i - 1] + verticle[i + j].charAt(j)
                            : "" + verticle[i + j].charAt(j);
                    reversediagonal[size + i - 1] = (reversediagonal[size + i - 1] != null)
                            ? reversediagonal[size + i - 1] + input[j].charAt(size-1-j-i)
                            : "" + input[j].charAt(size-1-i-j);
                }
            readFile = new BufferedReader(new FileReader(new File("words.txt")));
            boolean flag = false;
            while ((line = readFile.readLine()) != null) {
                flag = false;
                line = line.toUpperCase();
                for (int i = 0; i < size; i++) {
                    if (input[i].contains(line) || (new StringBuilder(input[i]).reverse().toString()).contains(line)
                            || verticle[i].contains(line)
                            || (new StringBuilder(verticle[i]).reverse().toString()).contains(line)
                            || diagonal[i * 2].contains(line)
                            || (new StringBuilder(diagonal[i * 2]).reverse().toString()).contains(line)
                            || reversediagonal[i * 2].contains(line)
                            || (new StringBuilder(reversediagonal[i * 2]).reverse().toString()).contains(line)) {
                                
                        System.out.print(line + " was found in the board\n");
                        flag = true;
                        break;
                    } else if (i != size-1 && (diagonal[i * 2 + 1].contains(line)
                    || (new StringBuilder(diagonal[i * 2 + 1]).reverse().toString()).contains(line)
                    || reversediagonal[i * 2 + 1].contains(line)
                    || (new StringBuilder(reversediagonal[i * 2 + 1]).reverse().toString()).contains(line))){
                        System.out.print(line + " was found in the board\n");
                        flag = true;
                        break;      
                    }
                }
                if (flag == false) {
                    System.out.print(line + " was not found in the board\n");
                }
            }
            readFile.close();
        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }
}