import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HTMLViewer {

    public static void main(String[] args)
    {
        File dataFile = new File("htmlsample.txt");
        BufferedReader readFile;
        String txt = "";
        String replaced;
        String line;

        try{
            FileReader in = new FileReader(dataFile);
            readFile = new BufferedReader(in);
            in = new FileReader(dataFile);
            while((line = readFile.readLine()) != null) txt += line;
            replaced = txt.replaceAll("<br>", "\n");
            replaced = replaced.replaceAll("<p>", "\n\n");
            replaced = replaced.replaceAll("<hr>", "---------------------------------\n");
            System.out.print(replaced);
        } catch (IOException e){
            System.out.println("Problem reading file.");
            System.out.println("IOException: " +e.getMessage());
        }
    }
}