import java.util.ArrayList;
import java.util.List;

public class test {
    public static List<Integer>nums = new ArrayList<Integer>();
    public static void add(List<Integer> some)
    {
        some.add(3);
    }

    public static void main(String[] args)
    {
        add(nums);
        System.out.println(nums);
    }

}