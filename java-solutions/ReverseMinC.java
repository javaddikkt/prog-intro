import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ReverseMinC {
    public static void main (String[] args) {
        ArrayList<int[]> input = new ArrayList<>();
        int[] numbers;
        Scanner scanner = new Scanner(System.in);
        Scanner lineScanner;
        int num;
        String line;
        int i = 0;
        int j = 0;
        int max_j = 0;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isBlank()) { 
                input.add(new int[0]);
            } else {
                lineScanner = new Scanner(line);
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

       int[] result = new int[max_j];
       for (int b = 0; b < max_j; b++) {
           result[b] = Integer.MAX_VALUE; 
       }
       for (int a = 0; a < i; a++) {
           for (int b = 0; b < input.get(a).length; b++) {
                result[b] = Math.min(result[b], input.get(a)[b]);
                System.out.print(result[b]);
                System.out.print(" ");  
           }  
           System.out.println();
       }
    }
}
