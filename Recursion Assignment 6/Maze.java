import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    // % wall
    // . empty floor
    // e entrance
    // x exit
    // + steps
    public static char maze[][];
    public static int visited[][];
    public static int r, c, startr, startc, exitr, exitc;

    public static boolean findPath(int x, int y){
        
        if ((x < r && y < c && y >=0 && x >= 0) == false) return false;
        else if (x == exitr && y == exitc) return true;
        else if (maze[x][y] == '%' || visited[x][y] == 1 || maze[x][y] == '+') return false;
        visited[x][y] = 1;
        maze[x][y] = '+';
        if (findPath(x, y+1)) return true;
        else if (findPath(x, y-1)) return true;
        else if (findPath(x-1, y)) return true;
        else if (findPath(x+1, y)) return true;
        else {
            maze[x][y] = '.';
            return false;
        }
    }
    public static void main(String[] args){
        try{
            BufferedReader readFile = new BufferedReader(new FileReader(new File("maze.txt")));
            String line;
            line = readFile.readLine();
            String input[] = line.split(",");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);
            maze = new char[r][c];
            visited = new int[r][c];
            int tmp = 0;
            while ((line = readFile.readLine())!= null) {
                for(int i = 0; i < c; i++) {
                    maze[tmp][i] = line.charAt(i);
                    if (line.charAt(i) == 'e'){
                        startr = tmp;
                        startc = i;
                    } else if (line.charAt(i) == 'x'){
                        exitr = tmp;
                        exitc = i;
                    }
            }tmp++;}
            findPath(startr, startc);
            maze[startr][startc] = 'e';
            for(char row[]: maze){
                for(char col:row) System.out.print(col);
                System.out.print("\n");
            }
            readFile.close();
        } catch (IOException e) {}
    }
}