import java.util.Scanner;

public class Assign51 {
    public static void minus(int count, int up, int down){
        if (up < down)
        {
            System.out.println("Quotient: "+count+"   Remainder: "+up);
            return;
        } else{
            minus(++count, up-down, down);
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter the number to be divided: ");
        Scanner input = new Scanner(System.in);
        int up = input.nextInt();
        System.out.println("Enter the denominator: ");
        int down = input.nextInt();
        int count = 0;
        input.close();
        minus(count, up, down);
    }
}