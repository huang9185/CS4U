import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Wordsearch2 {
    public static void main(String[] args) {
        try {
            String line;
            BufferedReader readFile = new BufferedReader(new FileReader(new File("boardgame.txt")));
            int size = Integer.parseInt(readFile.readLine());
            String input[] = new String[size*6-2];
            int m = 0;
            while ((line = readFile.readLine()) != null) {
                input[m] = line.toUpperCase();
                m++;
            }
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    input[size+i] = (input[size+i] != null) ? input[size+i]+input[j].charAt(i) : "" +input[j].charAt(i);
            for (int i = 0; i < size; i++)
                for (int j = 0; j <= i; j++) {
                    input[2*size+i] = (input[2*size+i] != null) ? input[2*size+i]+input[size+j].charAt(size - i - 1 + j):""+input[size+j].charAt(size - i - 1 + j);
                    input[4*size-1+i] = (input[4*size-1+i] != null) ? input[4*size-1+i]+input[size-1-i+j].charAt(size - 1 - j): ""+input[size-1-i+j].charAt(size - 1 - j);
                }
            for (int i = 1; i < size; i++)
                for (int j = 0; j <= size - i - 1; j++) {
                    input[3*size + i - 1] = (input[3*size + i - 1] != null) ? input[3*size + i - 1]+input[size+i + j].charAt(j):""+input[size+i + j].charAt(j);
                    input[5*size + i - 2] = (input[5*size + i - 2]!= null) ? input[5*size + i - 2]+input[j].charAt(size-1-i-j):""+input[j].charAt(size-1-i-j);;
                }
            readFile = new BufferedReader(new FileReader(new File("words.txt")));
            boolean flag = false;
            while ((line = readFile.readLine()) != null) {
                flag = false;
                line = line.toUpperCase();
                for (int i = 0; i < size*6-2; i++) {
                    if (input[i].contains(line) || (new StringBuilder(input[i]).reverse().toString()).contains(line)) {
                                
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