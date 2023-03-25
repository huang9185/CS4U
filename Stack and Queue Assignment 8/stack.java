import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class stack {
    public static Stack<Character> operatorsst = new Stack<Character>();
    public static Stack<Integer> numbersst = new Stack<Integer>();
    public static HashMap<Character, Integer> operators = new HashMap<Character, Integer>();
    
    public static void main(String[] args){
        operators.put('+', 1);
        operators.put('-', 1);
        operators.put('*', 2);
        operators.put('/', 2);
        operators.put('^', 3);
        System.out.println("Enter the expression:");
        Scanner input = new Scanner(System.in);
        String expressionline = input.nextLine();
        String[] inputs = expressionline.split(" ");
        char operator = inputs[1].charAt(0);
        operatorsst.push(operator);
        int number = Integer.parseInt(inputs[0]);
        numbersst.push(number);
        number = Integer.parseInt(inputs[2]);
        numbersst.push(number);
        char lastop = operator;
        for (int i = 3; i < inputs.length; i++){
            if (i%2 == 0) {
                // is numbers
                number = Integer.parseInt(inputs[i]);
                numbersst.push(number);
            } else {
                // is operators
                operator = inputs[i].charAt(0);

                if (operators.get(operator)<operators.get(lastop)){
                    calculate();
                }
                operatorsst.push(operator);
                lastop = operator;
            }
        }
        input.close();
        calculate();
        System.out.println("The answer is "+numbersst.peek());
    }
    public static void calculate(){
        while(operatorsst.isEmpty() == false) {
            int cur = 0;
            int second = numbersst.peek();
            numbersst.pop();
            int first = numbersst.peek();
            numbersst.pop();
            switch(operatorsst.peek()){
                case '+':
                    cur = first + second;
                    break;
                case '-':
                    cur = first - second;
                    break;
                case '*':
                    cur = first * second;
                    break;
                case '/': 
                    cur = first / second;
                    break;
                case '^':
                    cur = (int)(Math.pow(first, second));
                    break;
            }
            operatorsst.pop();
            numbersst.push(cur);
        }
    }
}