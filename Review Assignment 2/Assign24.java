import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Assign24 {
    static List<Integer> scores = new ArrayList<Integer>();

    public static void main(String[] args)
    {
        System.out.println("Enter scores -1 to end");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        while(num != -1)
        {
            scores.add(num);
            if (scores.size() == 6) break;
            num = input.nextInt();
        }

        quickSort(scores, 0, scores.size()-1);

        // The differences
        int n = scores.size();
        int[][] dataset = new int[n-1][n];
        int[] sums = new int[n];

        for(int i = 0; i < n; i++)
        {
            List<Integer> indexes = adjustValue(i, n);
            int current = scores.get(i);
            int sum = 0;
            for(int j = 0 ; j < n-1; j++)
            {
                int index = indexes.get(j);
                int difference = current - scores.get(index);
                dataset[j][i] = difference;
                sum += difference;
            }
            sums[i] = sum;
        }

        // Format output TODO
        String format = "%5d";
        for (int i: scores) System.out.format(format, i);
        System.out.print("\n");
        for(int i = 0; i < n*5; i++) System.out.print("-");
        System.out.print("\n");
        for(int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < n; j++) System.out.format(format, dataset[i][j]);
            System.out.print("\n");
        }
        for(int i = 0; i < n*5; i++) System.out.print("-");
        System.out.print("\n");
        for(int sum:sums) System.out.format(format, sum);
        System.out.print("\n");
    }

    public static void quickSort(List<Integer> list, int begin, int end)
    {
        final int LEFT = 0;
        final int RIGHT = 1;
        if (begin < end)
        {
            int left = begin;
            int right = end;
            int current = LEFT;
            int pivot = list.get(begin);
            while(left < right)
            {
                if (current == LEFT)
                {
                    while(list.get(right) >= pivot && left < right)
                        right--;
                    list.set(left, list.get(right));
                    current = RIGHT;
                }
                if (current == RIGHT)
                {
                    while(list.get(left) <= pivot && left < right) left++;
                    list.set(right, list.get(left));
                    current = LEFT;
                }
            }
            list.set(left, pivot);
            quickSort(list, begin, left-1);
            quickSort(list, right+1, end);
        }
    }

    // If value >0, then 
    public static List<Integer> adjustValue(int index, int size)
    {
        List<Integer> result = new ArrayList<Integer>();

        if (index == 0){
            for(int i = index+1; i < size; i++) result.add(i);
        }
        else{
            // Before the index
            for(int i = 0; i < index; i++) result.add(i);
            // After the index
            for(int i = index+1; i < size; i++) result.add(i);
        }
        return result;
    }
}