import java.nio.charset.StandardCharsets;
import java.util.*;


public class Main {

    public static int reg(String str){
        int count=0;
        for (int i =0; i<str.length();i++){
            if (str.toCharArray()[i] == '+' || str.toCharArray()[i] == '-' || str.toCharArray()[i] == '/' || str.toCharArray()[i] == '*'){
                count++;
            }
        }
        return  count;
    }
    public  static  TreeMap<Integer,String> RimAsArab = new TreeMap<>(); //используем TreeMap как строго типизировнную структуру
    static {
        RimAsArab.put(1,"I");
        RimAsArab.put(4,"IV");
        RimAsArab.put(9,"IX");
        RimAsArab.put(10,"X");
        RimAsArab.put(40,"XL");
        RimAsArab.put(50,"L");
        RimAsArab.put(90,"XC");
        RimAsArab.put(100,"C");
    }
    public static String perevod (int in){

            int l =  RimAsArab.floorKey(in); // берём самое большое число которое меньше нашего по ключу
            if ( in == l ) {
                return RimAsArab.get(in); //наш выход из рекурсии провееряем конечность результата и возвращем его
            }
            return RimAsArab.get(l) + perevod(in-l); // складываем наше полученое значение ключа и вызываем рекурсию со значением из которого вычитаем полученое нами значение
        }

        public static boolean isNum(String str) {//функция для проверки на целое число
            try {
                Integer.parseInt(str);
                return true;
            }catch (Exception e){
                return false;
            }
        }

    public static String proverka(String num1, String num2, char znak) {
        String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        boolean n1 = false;
        boolean n2 = false;
        try {
            if (isNum(num1)  && isNum(num2) ){//проверяем систему счисления
                if (Integer.parseInt(num1) > 10 || Integer.parseInt(num1) < 1  || (Integer.parseInt(num2)) > 10 || Integer.parseInt(num2) < 1) { //<- тут исправил
                    throw new Exception("number is over 10 or lower 1");
                }
            }else {
                for (int i=0;i< rim.length;i++){//перевод из римских в арабские
                    if(num1.toUpperCase().equals(rim[i])){
                        num1 = Integer.toString(i+1);
                        n1 = true;
                    }
                    else if (i == rim.length-1 && !isNum(num1)) {
                        throw new Exception("over 10");
                    }
                    if(num2.toUpperCase().equals(rim[i])){
                        num2 = Integer.toString(i+1);
                        n2 = true;
                    } else if (i == rim.length-1 && !isNum(num2) ) {
                        throw new Exception("over 10");
                    }

                }

            }
            if (Integer.parseInt(num1) > 10 || Integer.parseInt(num1) < 1  || (Integer.parseInt(num2)) > 10 || Integer.parseInt(num2) < 1){
                throw new Exception("number is over 10 or lower 1");
            }
            if (n1!= n2){throw new Exception("different number systems are used");}
        }catch (Exception e){
            System.out.println(e);
            return null;
        }


        try {
            double prom = 0;
            switch (znak) {
                case '+':
                    prom = Integer.parseInt(num1) + Integer.parseInt(num2);
                    break;
                case '/':
                    prom = Double.parseDouble(num1) / Double.parseDouble(num2);
                    break;
                case '-':
                    prom = Integer.parseInt(num1) - Integer.parseInt(num2);
                    break;
                case '*':
                    prom = Integer.parseInt(num1) * Integer.parseInt(num2);
                    break;
            }
            if (n1 && ((int)(prom)) > 0){// проверям в какой системе счисления выводить ответ
                System.out.println(perevod((int)(prom)));
            } else if (n1 && ((int)(prom)) <= 0) {
                throw new Exception("The result in the Roman numeral system cannot be equal or lower 0");//Результат в римской системе счисления не может быть равен 0
            } else {
                System.out.println(prom);
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }



    public static void calc()//функция калькулятор
    {
        Scanner read = new Scanner(System.in);//создаем объект для чтения вводимых данных
        String str = read.nextLine();
        str = str.trim();

        try{
            if (reg(str)>1) {throw new Exception("the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)");}
            else {
                for (int i = 0; i < str.length(); i++) {

                    if (str.toCharArray()[i] == '+' || str.toCharArray()[i] == '-' || str.toCharArray()[i] == '/' || str.toCharArray()[i] == '*') {
                        proverka(str.substring(0, i), str.substring((i + 1)), str.toCharArray()[i]);
                        break;
                    }
                    else if(i == str.length()-1){
                        throw new Exception("math is not operation");
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }



    public static void main(String[] args) {
        calc();

    }
}