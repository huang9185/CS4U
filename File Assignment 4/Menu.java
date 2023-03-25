import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Methods{
    
}

public class Menu
{
    public static final Scanner input = new Scanner(System.in);
    public static void openFile(Map<String, List<String>> dts){
        try{
            String line;
            String words[];
            BufferedReader readFile = new BufferedReader(new FileReader(new File("employee.txt")));
            readFile.readLine();
            while ((line = readFile.readLine()) != null){
                words = line.split(" ");
                List<String> wordList = Arrays.asList(words);
                dts.put(words[1], wordList);
            }
            readFile.close();
            System.out.println("File opened.");
        } catch (IOException e) {System.out.println("Problem reading file.");}
    }
    public static void saveFile(Map<String, List<String>> dts) {
        try{
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(new File("employee.txt")));
            writeFile.write("Employee# Employee-Name Marital-Status Age Salary");
            writeFile.newLine();
            for(Map.Entry<String, List<String>> en:dts.entrySet()){
                List<String> value = (List<String>) en.getValue();
                for(String dt: value) writeFile.write(dt+" ");
                writeFile.newLine();
            }
            writeFile.close();
            System.out.println("File saved");
        } catch (IOException e) {System.out.println("Problem writing to file.");}
    }
    public static void addEntry(Map<String, List<String>> dts) {
        List<String> dt = new ArrayList<String>();
        String key = "";
        System.out.println("Enter the employee number: ");
        dt.add(input.nextLine());
        System.out.println("Enter the employee name: ");
        key = input.nextLine();
        dt.add(key);
        System.out.println("Enter the employee's marital Status: ");
        dt.add(input.nextLine());
        System.out.println("Enter the employee's age: ");
        dt.add(input.nextLine());
        System.out.println("Enter the employee's salary: ");
        dt.add(input.nextLine());
        dts.put(key, dt);
        System.out.println("Entry added.");
    }
    public static void deleteEntry(Map<String, List<String>> dts) {
        System.out.println("Below is a list of your employees' names: ");
        for(Map.Entry<String, List<String>> en:dts.entrySet()) {
            System.out.println(en.getKey());
        }
        System.out.println("Select the employee's name to delete: ");
        String name = input.nextLine();
        dts.remove(name);
        System.out.println("Entry closed");
    }
    public static void modifyEntry(Map<String, List<String>> dts) {
        System.out.println("Below is a list of your employees' names: ");
        for(Map.Entry<String, List<String>> en:dts.entrySet()) {
            System.out.println(en.getKey());
        }
        System.out.println("Select the employee's name to modify: ");
        String name = input.nextLine();
        System.out.print("\n");
        // Show the data entry for the employee selected
        String category = "Employee# Employee-Name Marital-Status Age Salary";
        String categories[] = category.split(" ");
        for(String i : categories){
            System.out.print(String.format("%18s", i));
        } System.out.print("\n");
        List<String> tmp = (List<String>) dts.get(name);
        for(String i: tmp) System.out.print(String.format("%18s", i));
        // Change data according to the input
        System.out.print("\nEnter the category and the information you want to modify in two lines: \n");
        String userin = input.nextLine().toLowerCase();
        String valuein = input.nextLine();
        if (userin.equals("marital-status")) tmp.set(2, valuein);
        else if (userin.equals("age")) tmp.set(3, valuein);
        else if (userin.equals("employee#")) tmp.set(0, valuein);
        else if (userin.equals("salary")) tmp.set(4, valuein);
        else if (userin.equals("employee-name")){
            dts.remove(name);
            tmp.set(1, valuein);
        }
        dts.put(name,tmp);
        System.out.println("Entry modified.");
    }
    public static void displayEntries(Map<String, List<String>> dts){
        String category = "Employee# Employee-Name Marital-Status Age Salary";
        String categories[] = category.split(" ");
        for(String i : categories){
            System.out.print(String.format("%15s", i));
        } System.out.print("\n");
        for(Map.Entry<String, List<String>> en:dts.entrySet()){
            List<String> tmp = (List<String>) en.getValue();
            for(String i:tmp) System.out.print(String.format("%15s", i));
            System.out.print("\n");
        }
    }
    // Name: number, name, etc.
    public static Map<String, List<String>> dts = new HashMap<String, List<String>>();

    public static void main(String[] args)
    {
        int userin = 9;
        while (userin != 7)
        {
            if (userin == 1) openFile(dts);
            else if (userin == 2) saveFile(dts);
            else if (userin == 3) addEntry(dts);
            else if (userin == 4) deleteEntry(dts);
            else if (userin == 5) modifyEntry(dts);
            else if (userin == 6) displayEntries(dts);

            System.out.print("1. Open file\n2. Save file\n3. Add a record\n4. Delete a record\n5. Modify a record\n6. Display all\n7. Quit\n");
            userin = Integer.parseInt(input.nextLine());
        }
        input.close();

    }
}