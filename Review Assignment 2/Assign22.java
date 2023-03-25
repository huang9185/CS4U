import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
class Method {
    public static void quickSort(ArrayList<Integer> numbers, int begin, int end)
    {
        final int MOVING_LEFT = 0;
        final int MOVING_RIGHT = 1;
        if (begin < end)
        {
            int left = begin;
            int right = end;
            int currentDirection = MOVING_LEFT;
            int pivot = numbers.get(begin);
            while(left < right)
            {
                if (currentDirection == MOVING_LEFT)
                {
                    while ((numbers.get(right) >= pivot)&&left < right) right--;
                    numbers.set(left, numbers.get(right));
                    currentDirection = MOVING_RIGHT;
                    if (currentDirection == MOVING_RIGHT)
                    {
                        while((numbers.get(left)<= pivot) && left < right ) left++;
                        numbers.set(right, numbers.get(left));
                        currentDirection = MOVING_LEFT;

                    }
                }
                numbers.set(left, pivot);
                quickSort(numbers, begin, left-1);
                quickSort(numbers, right+1, end);
            }
        }
    }
    // Use sorted list
    public static void whiskerPlot(ArrayList<Integer> numbers)
    {
        double median;
        int first, third;
        int lower, upper;
        if (numbers.size() %2 == 0)
        {
            upper = numbers.size()/2;
            lower = numbers.size()/2-1;
            double total = (numbers.get(upper) + numbers.get(lower));
            median = total / 2.0;
            first = numbers.get(lower/2);
            third = numbers.get((numbers.size()-1+upper)/2);
        }
        else{
            median = numbers.get(numbers.size()/2);
            first = numbers.get(numbers.size()/2/2);
            third = numbers.get((numbers.size()+numbers.size()/2)/2);
        }

        int min = 101, max = 0;
        for(int i = 0; i < numbers.size(); i++)
        {
            if (numbers.get(i)>max) max = numbers.get(i);
            if (numbers.get(i) < min) min = numbers.get(i);
        }
        System.out.println("The whisker plot: " + min + " " + first + " " + median + " " + third + " " +max);
    
    }
    
}
public class Assign22{

    // Create data set
    public static Map<Integer, ArrayList<String>> dic = new TreeMap
    <Integer, ArrayList<String>>(Collections.reverseOrder());
    // Mark, Frequency
    public static Map<Integer, Integer> marks = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    // 1D marks
    public static ArrayList<Integer> origin = new ArrayList<Integer>();
    
    public static void modeCalculation()
    {
        Set set = marks.entrySet();
        Iterator i = set.iterator();
        // reverse frequency: frequency, {mark}
        TreeMap<Integer, ArrayList> frequency = new TreeMap<Integer, ArrayList>(Collections.reverseOrder());
        ArrayList<Integer> marktmp;
        while(i.hasNext()) {
            Map.Entry entry = (Map.Entry)i.next();
            int fre = Integer.valueOf((Integer) entry.getValue());
            int mar = Integer.valueOf((Integer) entry.getKey());
            if (frequency.containsKey(fre))
            {
                marktmp = frequency.get(fre);
                
            }
            else{
                marktmp = new ArrayList<Integer>();
            }
            marktmp.add(mar);
            frequency.put(fre, marktmp);
        }
    
        int count = 0;
        System.out.print("Mode: ");
        if (Integer.valueOf((Integer)frequency.firstEntry().getKey()) == 1)
            System.out.print("No mode\n");
        else{
            marktmp = frequency.firstEntry().getValue();
            // Looping through modes
            while(marktmp.size() > count)
            {
                int current = marktmp.get(count);
                System.out.print(current + " ");
                count++;
            }
            System.out.println("\nBelow are students who have achieved the modes:");
            // Print names who achieved the modes
            count = 0;
            while(marktmp.size() > count)
            {
                int c = marktmp.get(count);
                System.out.println(c+": "+ dic.get(c));
                count++;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = input.nextLine();
        System.out.println("Enter a mark: ");
        Integer mark = input.nextInt();

        // Calculate for mean
        int means = 0;
        int total = 0;
        ArrayList<String> names;
        while(name.equals("-1") || mark != -1)
        {
            if (mark < 0 || mark > 100)
            {
                System.out.println("The mark is outside of a valid range.");
                return;
            }
            origin.add(mark);
            means += mark;
            total++;
            if (dic.containsKey(mark))
            {
                names = dic.get(mark);
                names.add(name);
                dic.put(mark, names);
                marks.put(mark, marks.get(mark)+1);
            } else{
            names = new ArrayList<String> ();
            names.add(name);
            dic.put(mark, names);
            marks.put(mark, 1);
        }
            input.nextLine();
            System.out.println("Enter a name: ");
            name = input.nextLine();
            if (name.equals("-1")) break;
            System.out.println("Enter a mark: ");
            mark = input.nextInt();
        }
        input.close();

        System.out.print("Mean: ");
        System.out.printf("%.1f%n", (double)means/total);

        // Mode
        modeCalculation();

        Method.quickSort(origin, 0, origin.size()-1);
        Method.whiskerPlot(origin);
    }
}