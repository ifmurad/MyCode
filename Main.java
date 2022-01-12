import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        System.out.println(calc(inputString));
    }
    public static String calc(String inputString) {
        String exception = "Исключение";
        String inputString1;
        if (inputString.contains("+")) {
            inputString1 = inputString.replace("+", " + ");
        }
        else if (inputString.contains("-")) {
            inputString1 = inputString.replace("-", " - ");
        }
        else if (inputString.contains("*")) {
            inputString1 = inputString.replace("*", " * ");
        }
        else if (inputString.contains("/")) {
            inputString1 = inputString.replace("/", " / ");
        }
        else {
            return exception;
        }
        String[] symbols = inputString1.split("\\s*(\\s)");
        int count = (int) Arrays.stream(symbols).count();
        if (count != 3) {
            return exception;
        }
        else {
            Convertion convertion = new Convertion();
            if (convertion.IsRoman(symbols[0]) && convertion.IsRoman(symbols[2])) {
                symbols[0] = convertion.RomanToArab(symbols[0]);
                symbols[2] = convertion.RomanToArab(symbols[2]);
                int num1 = Integer.parseInt(symbols[0]);
                int num2 = Integer.parseInt(symbols[2]);
                if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                    return exception;
                }
                else {
                    Arithmetic arithmetic = new Arithmetic();
                    String result = arithmetic.getResult(num1, symbols[1], num2);
                    if (!result.equals(exception)) {
                        if ( Integer.parseInt(result) < 1) {
                            return exception;
                        }
                        else {
                            int resultInt = Integer.parseInt(result);
                            return convertion.ArabToRoman(resultInt);
                        }
                    }
                    else {
                        return exception;
                    }
                }
            }
            else if (convertion.IsRoman(symbols[0]) || convertion.IsRoman(symbols[2])) {
                return exception;
            }
            else {
                try {
                    int num1 = Integer.parseInt(symbols[0]);
                    int num2 = Integer.parseInt(symbols[2]);
                    if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                        return exception;
                    }
                    else {
                        Arithmetic arithmetic = new Arithmetic();
                        return arithmetic.getResult(num1, symbols[1], num2);
                    }
                }
                catch (Exception ex) {
                    return exception;
                }
            }
        }
    }
}

class Convertion {
    String RomanToArab(String RomanNum) {
        Convertion convertion = new Convertion();
        String ArabNum;
        for (int i = 1; i <= 10; ++i) {
            if (convertion.ArabToRoman(i).equals(RomanNum)) {
                ArabNum = String.valueOf(i);
                return ArabNum;
            }
        }
        return RomanNum;
    }
    boolean IsRoman(String RomanNum) {
        Convertion convertion = new Convertion();
        for (int i = 1; i <= 10; ++i) {
            if (convertion.ArabToRoman(i).equals(RomanNum)) {
                return true;
            }
        }
        return false;
    }
    String ArabToRoman(int ArabNum) {
        Map<Integer, String> RomanNum = new HashMap<>();
        RomanNum.put(0, "X");
        RomanNum.put(1, "I");
        RomanNum.put(2, "II");
        RomanNum.put(3, "III");
        RomanNum.put(4, "IV");
        RomanNum.put(5, "V");
        RomanNum.put(6, "VI");
        RomanNum.put(7, "VII");
        RomanNum.put(8, "VIII");
        RomanNum.put(9, "IX");
        RomanNum.put(10, "X");
        String resultStr;
        if ((ArabNum >= 1) && (ArabNum <= 10)) {
            resultStr = RomanNum.get(ArabNum);
        }
        else if ((ArabNum >= 11) && (ArabNum <= 20)) {
            resultStr = "X" + RomanNum.get(ArabNum % 10);
        }
        else if ((ArabNum >= 21) && (ArabNum <= 30)) {
            resultStr = "XX" + RomanNum.get(ArabNum % 10);
        }
        else if ((ArabNum >= 31) && (ArabNum <= 39)) {
            resultStr = "XXX" + RomanNum.get(ArabNum % 10);
        }
        else if (ArabNum == 40) {
            resultStr = "XL";
        }
        else if ((ArabNum >= 41) && (ArabNum <= 49)) {
            resultStr = "XL" + RomanNum.get(ArabNum % 10);
        }
        else if (ArabNum == 50) {
            resultStr = "L";
        }
        else if ((ArabNum >= 51) && (ArabNum <= 60)) {
            resultStr = "L" + RomanNum.get(ArabNum % 10);
        }
        else if ((ArabNum >= 61) && (ArabNum <= 70)) {
            resultStr = "LX" + RomanNum.get(ArabNum % 10);
        }
        else if ((ArabNum >= 71) && (ArabNum <= 80)) {
            resultStr = "LXX" + RomanNum.get(ArabNum % 10);
        }
        else if ((ArabNum >= 81) && (ArabNum <= 89)) {
            resultStr = "LXXX" + RomanNum.get(ArabNum % 10);
        }
        else if (ArabNum == 90) {
            resultStr = "XC";
        }
        else {
            resultStr = "C";
        }
        return resultStr;
    }
}

class Arithmetic {
    String getResult(int num1, String operator, int num2) {
        String result;
        switch (operator) {
            case "+":
                result = String.valueOf(num1 + num2);
                break;
            case "-":
                result = String.valueOf(num1 - num2);
                break;
            case "*":
                result = String.valueOf(num1 * num2);
                break;
            case "/":
                result = String.valueOf(num1 / num2);
                break;
            default:
                result = "Исключение";
                break;
        }
        return result;
    }
}
