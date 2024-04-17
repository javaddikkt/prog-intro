import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main (String[] args) {
        MyScanner scanner = new MyScanner(System.in);
        //Scanner scanner = new Scanner(System.in);
        MyScanner lineScanner;
        //Scanner lineScanner;
        ArrayList<int[]> input = new ArrayList<>();
        int[] numbers;
        int num;
        String line;
        int i = 0;
        int j = 0;
        int max_j = 0;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                input.add(new int[0]);
            } else {
                lineScanner = new MyScanner(line);
                //lineScanner = new Scanner(line);
                numbers = new int[10];
                while (lineScanner.hasNextInt()) {
                    num = lineScanner.nextInt();
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

        for (int a = i - 2; a >= 0; a--) {
            if (input.get(a).length != 0) {
                for (int b = input.get(a).length - 1; b >= 0; b--) {
                    System.out.print(input.get(a)[b]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
