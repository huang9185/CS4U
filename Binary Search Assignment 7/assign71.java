import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class assign71 {
    public static String[] words = new String[10*6-2];
    public static ArrayList<String> inputs = new ArrayList<String>();
    public static ArrayList<String> dic = new ArrayList<String>();

    public static void main(String[] args){
        Random ran = new Random();
        int totalscore = 0;
        open_dictionary();
        quickSort(dic, 0, dic.size()-1);
        for (int i = 0; i < 10; i++){
            String row = "";
            for (int j = 0; j < 10; j++){
                int next = ran.nextInt(26)+65;
                char nextchar = (char)(next);
                row = row + nextchar;
            } words[i] = row;
        }
        int size = 10;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                words[size+i] = (words[size+i] != null) ? words[size+i]+words[j].charAt(i) : "" +words[j].charAt(i);
        for (int i = 0; i < size; i++)
            for (int j = 0; j <= i; j++) {
                words[2*size+i] = (words[2*size+i] != null) ? words[2*size+i]+words[size+j].charAt(size - i - 1 + j):""+words[size+j].charAt(size - i - 1 + j);
                words[4*size-1+i] = (words[4*size-1+i] != null) ? words[4*size-1+i]+words[size-1-i+j].charAt(size - 1 - j): ""+words[size-1-i+j].charAt(size - 1 - j);
            }
        for (int i = 1; i < size; i++)
            for (int j = 0; j <= size - i - 1; j++) {
                words[3*size + i - 1] = (words[3*size + i - 1] != null) ? words[3*size + i - 1]+words[size+i + j].charAt(j):""+words[size+i + j].charAt(j);
                words[5*size + i - 2] = (words[5*size + i - 2]!= null) ? words[5*size + i - 2]+words[j].charAt(size-1-i-j):""+words[j].charAt(size-1-i-j);;
            }
        Scanner input = new Scanner(System.in);
        String inputword = "";
        while (inputword.equals("-1") == false){
            if (inputword.equals("") == false && in_dictionary(inputword)){
                if (inputs.isEmpty() == false && inputs.contains(inputword.toUpperCase())) System.out.println("You have entered the word previously");
                else {
                    for (int i = 0; i < 58; i++){
                        if (words[i].contains(inputword.toUpperCase()) || (new StringBuilder(words[i]).reverse().toString()).contains(inputword.toUpperCase())){
                            inputs.add(inputword.toUpperCase());
                            System.out.println("You scored "+ inputword.length() + " points!");
                            totalscore += inputword.length();
                            break;
                        }
                        else if (i == 57) System.out.println("The word does not exist in dictioary or not in the game board.");

                    }
                }
            }
            display();
            System.out.println("Enter a word(-1 to end):");
            inputword = input.nextLine().toUpperCase();
        }
        writescore(totalscore);
        input.close();
    }
    public static void display(){
        for (int i = 0; i < 10; i++){
            for (int j = 0;j < 10; j++){
                System.out.print(words[i].charAt(j)+" ");
            } System.out.print("\n");
        }
    }
    public static void writescore(int total){
        try {
            ArrayList<Integer> scores = new ArrayList<Integer>();
            int high = total;
            scores.add(total);
            BufferedReader br = new BufferedReader(new FileReader(new File("scores.txt")));
            String line = "";
            while((line = br.readLine())!= null){
                int score = Integer.parseInt(line);
                scores.add(score);
                if (score > high) high = score;
            }
            br.close();
            System.out.println("The highest score for this game is "+high);
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("scores.txt")));
            for (int score : scores){
                bw.write(String.valueOf(score));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e){
            System.out.println("File not found");
        }
    }
    public static void open_dictionary(){
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File("dictionary.txt")));
            String line;
            while((line = bf.readLine())!= null){
                dic.add(line);
            }
            bf.close();
        }
        catch (IOException e){
            System.out.println("Dictionary file not exist");
        }
    }
    public static boolean in_dictionary(String word){
        word = word.toLowerCase();
    
        int start = 0;
        int end = dic.size()-1;
        int mid = 0;
        boolean flag = false;
        while (start < end){
            mid = (start+end)/2;
            if (dic.get(mid).equals(word)) {
                flag = true;
                break;
            } else {
                if (dic.get(mid).compareTo(word) < 0){
                    start = mid;
                } else {end = mid;}
            }
        }
        if (flag) return true;
        else return false;
    }
    public static void quickSort(ArrayList<String> words, int start, int end){
        int i = start;
        int j = end;
        if (j-i >= 1)
        {
            String pivot = words.get(i);
            while(j>i)
            {
                while (words.get(i).compareTo(pivot) <= 0 && i <= end && j> i){
                    i++;
                } while(words.get(j).compareTo(pivot)>= 0 && j >= start && j >= i){
                    j--;
                }
                if (j > i) Collections.swap(words, i, j);
            }
            Collections.swap(words, start, j);
            quickSort(words, start, j-1);
            quickSort(words, j+1, end);
        }
    }
}