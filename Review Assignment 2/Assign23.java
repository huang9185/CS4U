import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Need to assume one column could be filled up before one player wins - Yes
public class Assign23 {
    public static Scanner input = new Scanner(System.in);
    public static String in;

    // # row, # column
    public static int[][] field = new int[8][8];
    public static Map<Integer, List<Integer>> player1 = new HashMap<Integer, List<Integer>>();
    public static Map<Integer, List<Integer>> player2 = new HashMap<Integer, List<Integer>>();
    // For the V shape only
    public static int[] vRow = {-1, -2, -1, 0};
    public static int[] vCol = {1, 2, 3, 4};
    public static int[] vrRow = {1, 2, 1, 0};

    public static int row = 0;
    public static int col = -1;
    public static List<Integer> columns;    
    public static int totalsteps = 0;
    
    public static void main(String[] args)
    {
        printField();
        while(true)
        {
            System.out.println("Player 1");
            System.out.println("Enter the column letter: ");
            in = input.nextLine().toUpperCase();
            col = in.charAt(0);
            proceed(player1, 1);
            totalsteps++;
            
            for(Map.Entry<Integer, List<Integer>> entry : player1.entrySet())
            {
                row = entry.getKey();
                for(Integer i: entry.getValue()){
                    if (checkShape(row, i, player1)) 
                    {
                        System.out.println("Player 1 wins");
                        return;
                    }
                }
            }
            
            System.out.println("Player 2");
            System.out.println("Enter the column letter: ");
            in = input.nextLine().toUpperCase();
            col = in.charAt(0);
            proceed(player2, 2);
            totalsteps++;
            for(Map.Entry<Integer, List<Integer>> entry : player2.entrySet())
            {
                row = entry.getKey();
                for(Integer i: entry.getValue()){
                    if (checkShape(row, i, player2)) 
                    {
                        System.out.println("Player 2 wins");
                        return;
                    }
                }
            }
            if (totalsteps == 64) {
                System.out.println("Game ends with a tie.");
                return;
            }
            
        }
    }
    public static boolean checkShape(int row, int col, Map<Integer, List<Integer>> player)
    {
        // Check for V shape
        int V = 0;
        for (int i = 0; i < 4; i++)
        {
            if (containsPair(row+vRow[i], col+vCol[i], player)) V++;
            else break;
        }
        if (V == 4) return true;
        // Reverse V shape
        int rV = 0;
        for (int i = 0; i < 4; i++) {if (containsPair(row+vrRow[i], col+vCol[i], player)) rV++;else break;}
        if (rV == 4) return true;
        return false;
    }

    public static int dropChip(int col)
    {
        int row = 0;
        for(int i = 7; i >= 0; i--)
        {
            if (i == 0 && field[i][col] == 0) {
                row = 0; break;}
            else if (i == 7 && field[i][col] != 0) {row = -1; break;}
            else if (field[i][col] == 0 && field[i-1][col] != 0) 
            {
                row = i; break;}
        }
        return row;
    }
    public static void printField()
    {
        for(int i = 7; i >=0 ; i--)
        {
            for(int j = 0; j < 8; j++)
            {
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }
        for(int i = 65; i<=65+7; i++) 
        {
            char c = (char)i;
            System.out.print(c+" ");
        }
        System.out.print("\n");
    }
    public static boolean containsPair(int row, int col, Map<Integer, List<Integer>> player)
    {
        Iterator i = player.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry pair = (Map.Entry) i.next();
            ArrayList<Integer> tmp = (ArrayList<Integer>)pair.getValue();
            // Check if col and row equals any pair of key and value in player
            if ((Integer)pair.getKey() == row && tmp.contains(col)) return true;
        }
        return false;
    }
    // Num: the number of player
    public static void proceed(Map<Integer, List<Integer>> player, int num)
    {
        col -= 65;
        row = dropChip(col);
        while(row == -1)
        {
            System.out.println("The column is filled. Please choose another one: ");
            in = input.nextLine().toUpperCase();
            col = in.charAt(0);
            col-=65;
            row = dropChip(col);
        }
        field[row][col] = num;
        printField();

        // Organize individual dataset
        if (player.containsKey(row))
        {
            columns = player.get(row);
            columns.add(col);
            player.put(row, columns);
        }
        else{
            columns = new ArrayList<Integer>();
            columns.add(col);
            player.put(row, columns);
        }
    }
}