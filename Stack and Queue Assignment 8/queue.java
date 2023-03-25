import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class queue {
    public static Queue<String> children = new LinkedList<String>();
    public static void main(String[] args){
        System.out.println("Enter a list of names in one line");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] names = line.split(" ");
        for (String name: names){
            children.add(name);
        }
        input.close();
        int random = (int)(Math.random()*10)+1;
        System.out.println("The randome number is "+random);
        while(children.size() != 1){
            for (int i = 0; i < random-1; i++){
                String first = children.peek();
                children.poll();
                children.add(first);
            }
            String removed = children.peek();
            children.poll();
            System.out.println(removed + " is removed from the list");
        }
        System.out.println("The winner of the game is "+children.peek());
    }
}