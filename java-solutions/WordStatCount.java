import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WordStatCount {
    public static void mySort (ArrayList<String> words, ArrayList<Integer> values) {
        int tempValue;
        String tempWord;
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                if (values.get(j + 1) < values.get(j)) {
                    tempValue = values.get(j);
                    tempWord = words.get(j);
                    values.set(j, values.get(j + 1));
                    words.set(j, words.get(j + 1));
                    values.set(j + 1, tempValue);
                    words.set(j + 1, tempWord);
                }
            }
        }
    }
    public static void main (String[] args) {
        File src = new File(args[0]);
        File dst = new File(args[1]);
        //File src = new File("C:/Users/11038/proga/input.txt");
        //File dst = new File("C:/Users/11038/proga/output.txt");
        StringBuilder word = new StringBuilder();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        char[] input;
        try  {
            MyScanner scanner = new MyScanner(src);
            while (scanner.hasNextLine()) {
                input = scanner.nextLine().toCharArray();
                for (char c : input) {
                    c = Character.toLowerCase(c);
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        word.append(c);
                    } else {
                        if (!word.isEmpty()) {
                            int index = words.indexOf(String.valueOf(word));
                            if (index == -1) {
                                words.add(String.valueOf(word));
                                values.add(1);
                            } else {
                                values.set(index, values.get(index) + 1);
                            }
                            word = new StringBuilder();
                        }
                    }
                }
                if (!word.isEmpty()) {
                    int index = words.indexOf(String.valueOf(word));
                    if (index == -1) {
                        words.add(String.valueOf(word));
                        values.add(1);
                    } else {
                        values.set(index, values.get(index) + 1);
                    }
                    word = new StringBuilder();
                }
            }
            mySort(words, values);
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("No source file");
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst), StandardCharsets.UTF_8));
            try {
                for (int i = 0; i < values.size(); i++) {
                    out.write(words.get(i));
                    out.write(" ");
                    out.write(String.valueOf(values.get(i)));
                    out.write('\n');
                }

            } catch (IOException e) {
                System.out.println("Output error");
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Error while closing destination file");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No destination file");
        }
    }
}