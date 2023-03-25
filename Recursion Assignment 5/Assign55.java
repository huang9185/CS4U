import java.util.Scanner;

public class Assign55 {
    public static boolean flag = false; // Indicate of this phrase is palindrome
    public static int size = 0; // Indicate the largest size of palindrome
    public static String cur = "";

    // initial count2 equals length -1
    public static void palindrom(String word, int count1, int count2){
        if (count1 >= count2){
            flag = true;
            return;
        } else{
            char first = word.charAt(count1);
            while(first == ' '){
                count1++;
                first = word.charAt(count1);
            }
            char second = word.charAt(count2);
            while(second == ' ')
            {
                count2--;
                second = word.charAt(count2);
            }
            if ((first-32==second || second-32 == first || second == first) == false)
            {
                flag = false;
                return;
            } else palindrom(word, ++count1, --count2);
        }
    }
    // initial count2 equals string length
    public static void loop(String phrase, int count1, int count2){
        if (count1 == phrase.length()-1) return;
        else if (count1+1 == count2) loop(phrase, count1+1, phrase.length());
        else {
            flag = false;
            String sub = phrase.substring(count1, count2);
            palindrom(sub, 0, sub.length()-1);
            if (flag == true && size < sub.length()) {
                size = sub.length();
                cur = sub;
            }
            loop(phrase, count1, --count2);
        }
    }
    public static void main(String[] args){
        System.out.println("Input: ");
        Scanner input = new Scanner(System.in);
        String userin = input.nextLine();
        loop(userin, 0, userin.length());
        System.out.println("Below is the longest palindrome with spaces and capitalization: \n"+cur);
        input.close();
    }
}