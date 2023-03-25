import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Viewerbywidth {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        File dataFile = new File("htmlsample.txt");
        String txt = "";
        String tmp;
        String replaced = "";
        int count = 0;
        boolean flag = false;
        System.out.println("Width: ");
        int width = input.nextInt();
        String dashes = "";
        for (int i = 0; i < width; i++) dashes += "-";
        dashes += "\n";

        try {
            FileReader in = new FileReader(dataFile);
            BufferedReader readFile = new BufferedReader(in);
            while((tmp = readFile.readLine()) != null) txt += tmp;

            
            // Go through char by char
            for(int i = 0; i < txt.length(); i++)
            {
                char cur = txt.charAt(i);
                if (cur == '<' || flag == true) {
                    flag = true;
                    replaced += cur;
                    if (cur == '>')
                    {
                        flag = false;
                        replaced = replaced.replaceAll("<br>", "\n");
                        replaced = replaced.replaceAll("<p>", "\n\n");
                        replaced = replaced.replaceAll("<hr>", dashes);
                        System.out.print(replaced);
                        replaced = "";
                        count = 0;
                    }
                }
                else {
                    if (cur == ' '){
                        int index = txt.substring(i+1).indexOf(' ');
                        index = Math.min(index, txt.substring(i+1).indexOf('<'));
                        if (index+count+1 > width) 
                        {
                            System.out.print("\n");
                            i++;
                            cur = txt.charAt(i);
                            count = 0;
                        }
                    }
                    System.out.print(txt.charAt(i));
                    count++;
                    if (count == width){
                        System.out.print("\n");
                        count = 0;
                    } 
                }

            }
            input.close();
            readFile.close();
        } catch (IOException e){
            System.out.println("Problem reading file.");
            System.out.println("IOException: " +e.getMessage());
        }
    }
}