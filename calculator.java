
import java.util.Scanner;

public class calculator {

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter numbers: ");
        System.out.println("First Number is: ");
        int num1 = s.nextInt();
        System.out.println("Second Number is: ");
        int num2 = s.nextInt();
        System.out.println("Enter any one operator +,-,/,*");
        char OP = s.next().charAt(0);
        int result;
        try {
            switch (OP) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Addition of two numbers is " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    System.out.println("The difference between two numbers is " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    System.out.println("The product of two numbers is: " + result);
                    break;
                case '/':
                    result = num1 / num2;
                    System.out.println("The division of two numbers is: " + result);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Value error");
        }
        s.close();

    }
}
