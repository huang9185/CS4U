import java.util.Scanner;

public class Assign54 {

    public static void sequence(int cur, int term, int goal)
    {
        if (term == goal) {
            System.out.println("The value in the sequence is "+cur);
            return;
        }
        else{
            if (term%2 == 0) sequence(2*cur+3, ++term, goal);
            else sequence(cur-2, ++ term, goal);
        }
    }
    public static void main(String[] args){
        System.out.println("Initial Term: ");
        Scanner input = new Scanner(System.in);
        int initial = input.nextInt();
        System.out.println("Term: ");
        int term = input.nextInt();
        sequence(initial, 0, term-1);
        input.close();
    }
}