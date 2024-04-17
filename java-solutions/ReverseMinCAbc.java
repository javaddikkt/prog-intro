import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ReverseMinCAbc {
    private static final String strMaxValue = "jjjjjjjjjj";

    public static void main(String[] args) {
        ArrayList<String[]> input = new ArrayList<>();
        String[] numbers;
        MyScanner scanner = new MyScanner(System.in);
        MyScanner lineScanner;
        String num;
        String line;
        int i = 0;
        int j = 0;
        int max_j = 0;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                input.add(new String[0]);
            } else {
                lineScanner = new MyScanner(line);
                numbers = new String[10];
                while (lineScanner.hasNext()) {
                    num = lineScanner.next();
                    if (j >= numbers.length) {
                        numbers = Arrays.copyOf(numbers, j*2);
                    }
                    numbers[j] = num;
                    j++;
                }
                input.add(Arrays.copyOf(numbers, j));
                if (j > max_j) {
                    max_j = j;
                }
                j = 0;
            }
            i++;
        }

        scanner.close();

        String[] result = new String[max_j];
        for (int b = 0; b < max_j; b++) {
            result[b] = strMaxValue;
        }
        for (int a = 0; a < i - 1; a++) {
            for (int b = 0; b < input.get(a).length; b++) {
                result[b] = minStr(result[b], input.get(a)[b]);
                System.out.print(result[b]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static String minStr(String str1, String str2) {
        if (str1.equals(strMaxValue)) {
            return str2;
        }
        if (str2.equals(strMaxValue)) {
            return str1;
        }
        char[] str1_c = str1.toCharArray();
        char[] str2_c = str2.toCharArray();
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            char c = str1_c[i];
            if (c < 'a') {
                num1.append(c);
            } else {
                num1.append(c - 'a');
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            char c = str2_c[i];
            if (c < 97) {
                num2.append(c);
            } else {
                num2.append(c - 97);
            }
        }
        int number1 = Integer.parseInt(num1.toString());
        int number2 = Integer.parseInt(num2.toString());
        if (number1 < number2) {
            return str1;
        }
        return str2;
    }
}
