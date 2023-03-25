import java.util.Scanner;
import java.util.stream.IntStream;
public class Assign21 {
    public static void main(String[] args)
    {
        System.out.println("Enter a sentence: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();

        // Split input into words
        String words[] = s.toUpperCase().split(" ");
        
        for(String word:words)
        {
            int N = (word.length()-1)*2+1;
            for(int i = 0; i < word.length(); i++)
            {
                IntStream.range(0, word.length()-(i+1)).forEach(n->System.out.print(" "));
                for(int j = 0; j <= i; j++) System.out.print(word.charAt(j));
                for(int j = i-1; j >=0; j--) System.out.print(word.charAt(j));
                IntStream.range(0, word.length()-(i+1)).forEach(n->System.out.print(" "));
                System.out.print("\n");
            }
            for(int i = word.length()-2; i >=0; i--)
            {
                IntStream.range(0, word.length()-(i+1)).forEach(n->System.out.print(" "));
                for(int j = 0; j <= i; j++) System.out.print(word.charAt(j));
                for(int j = i-1; j >=0; j--) System.out.print(word.charAt(j));
                IntStream.range(0, word.length()-(i+1)).forEach(n->System.out.print(" "));
                System.out.print("\n");
            }
            for(int i = 1; i < word.length(); i++)
            {
                IntStream.range(0, word.length()-1).forEach(n->System.out.print(" "));
                System.out.print(word.charAt(i));
                IntStream.range(0, word.length()-1).forEach(n->System.out.print(" "));
                System.out.print("\n");
            }
            System.out.println("");
        }
    }

}