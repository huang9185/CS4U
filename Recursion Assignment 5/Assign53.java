import java.util.Scanner;

public class Assign53 {
    public static void palindrom(String word, int count){
        if (count == word.length()/2){
            System.out.println(word +" is a palindrome");
            return;
        } else{
            if (word.charAt(count)!=word.charAt(word.length()-1-count))
            {
                System.out.println(word+" is not a palindrome");
                return;
            } else palindrom(word, ++count);
        }
    }
    public static void main(String[] args){
        System.out.println("Enter the word: ");
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        input.close();
        palindrom(in, 0);
    }
}