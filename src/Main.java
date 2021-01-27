import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Введите операцию:");
        Scanner in = new Scanner(System.in);
        String oper = in.nextLine();
        System.out.println(calc.setCommand(oper));
    }
}

class Calculator{
    private  int a;
    private int b;
    private String oper;
    private boolean isArab;

    public Calculator()
    {
    }

    String setCommand(String command){
        String[] arrCommand = command.split(" ");
        if(arrCommand.length == 3){
            oper = arrCommand[1];

            try {
                a = Integer.parseInt(arrCommand[0]);
                b = Integer.parseInt(arrCommand[2]);
                isArab = true;
            }catch (NumberFormatException e1){
                a = RomanToDecimal.romanToDecimal(arrCommand[0]);
                b = RomanToDecimal.romanToDecimal(arrCommand[2]);
                isArab = false;
                if (a == 0 || b == 0){
                    return "Неправильный формат цифр!";
                }
            }
            if(a > 10 || b > 10  || a < 1 || b < 1)
                return "Числа должны быть от 1 до 10 включительно!";
            return getCalculate();
        }
        return "Неправильно указана команда!";
    }

    private String getCalculate(){
        int res;
        switch (oper){
            case ("+"):
                res = a + b;
                break;
            case ("-"):
                res = a - b;
                break;
            case ("/"):
                res = a / b;
                break;
            case ("*"):
                res = a * b;
                break;
            default:
                return  "Неправильно указан опаратор!";
        }
        if(!isArab){
            return RomanToDecimal.decimalToRoman(res);
        }
        return Integer.toString(res);
    }

}

class RomanToDecimal {
    public static int romanToDecimal(String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();

        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M' -> {
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                }
                case 'D' -> {
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                }
                case 'C' -> {
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                }
                case 'L' -> {
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                }
                case 'X' -> {
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                }
                case 'V' -> {
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                }
                case 'I' -> {
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                }
            }
        }
        return decimal;
    }

    private static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }

    public static String decimalToRoman(int decimalNumber){
        int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] letters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder roman = new StringBuilder();
        int N = decimalNumber;

        for (int i = 0; i < numbers.length; i++) {
            while (N >= numbers[i]) {
                roman.append(letters[i]);
                N -= numbers[i];
            }
        }
        return roman.toString();
    }
}