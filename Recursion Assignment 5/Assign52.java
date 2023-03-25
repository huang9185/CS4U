import java.util.Scanner;

public class Assign52 {
    public static void addition(String in, int count, int sum){
        if (count >= in.length())
        {
            System.out.println("The sum is: "+sum);
            return;
        } else 
        {
            sum += (in.charAt(count)-'0');
            addition(in, ++count, sum);
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        input.close();
        int count=0, sum=0;
        addition(in, count, sum);
    }
}