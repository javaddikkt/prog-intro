
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wspp {
    private static void changeMap(String word, int cntWords, LinkedHashMap<String, ArrayList<Integer>> map) {
        ArrayList<Integer> numbers;
        if (map.containsKey(word)) {
            numbers = map.get(word);
        } else {
            numbers = new ArrayList<>();
        }
        numbers.add(cntWords);
        map.put(word, numbers);
    }

    public static void main(String[] args) {
        File src = new File(args[0]);
        File dst = new File(args[1]);
        //File src = new File("C:/Users/11038/proga/input.txt");
        //File dst = new File("C:/Users/11038/proga/output.txt");
        LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        String str;
        StringBuilder word = new StringBuilder();
        int cntWords = 0;
        try {
            MyScanner scanner = new MyScanner(src);
            //Scanner scanner = new Scanner(src);
            while (scanner.hasNext()) {
                str = scanner.next();
                char[] line = str.toCharArray();
                for (char c : line) {
                    c = Character.toLowerCase(c);
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        word.append(c);
                    } else {
                        if (!word.isEmpty()) {
                            cntWords++;
                            changeMap(word.toString(), cntWords, map);
                            word = new StringBuilder();
                        }
                    }
                }
                if (!word.isEmpty()) {
                    cntWords++;
                    changeMap(word.toString(), cntWords, map);
                    word = new StringBuilder();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("no source file");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst), "utf-8"));
            Set<String> keys = map.keySet();
            ArrayList<Integer> numbers;
            try {
                for (String key : keys) {
                    numbers = map.get(key);
                    writer.write(key);
                    writer.write(" ");
                    writer.write(String.valueOf(numbers.size()));
                    writer.write(" ");
                    for (int i = 0; i < numbers.size(); i++) {
                        writer.write(String.valueOf(numbers.get(i)));
                        if (i < numbers.size() - 1) {
                            writer.write(" ");
                        }
                    }
                    writer.write(System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("writing error");
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("encoding error");
        } catch (FileNotFoundException e) {
            System.out.println("no destination file");
        }
    }
}
