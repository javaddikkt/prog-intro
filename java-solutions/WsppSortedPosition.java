import java.io.*;
import java.util.*;

public class WsppSortedPosition {
    private static void changeMap(String word, int cntLines, int cntWords, TreeMap<String, ArrayList<int[]>> map) {
        ArrayList<int[]> numbers;
        if (map.containsKey(word)) {
            numbers = map.get(word);
        } else {
            numbers = new ArrayList<>();
        }
        numbers.add(new int[]{cntLines, cntWords});
        map.put(word, numbers);

    }

    public static void main(String[] args) {
        File src = new File(args[0]);
        File dst = new File(args[1]);
        //File src = new File("C:/Users/11038/proga/input.txt");
        //File dst = new File("C:/Users/11038/proga/output.txt");
        TreeMap<String, ArrayList<int[]>> map = new TreeMap<>();
        ArrayList<Integer> wordsInLine = new ArrayList<>();
        char[] token;
        StringBuilder word = new StringBuilder();
        int cntWords = 0;
        int cntLines = 1;
        int cntTokens = 0;
        try {
            MyScanner scanner = new MyScanner(src);
            while (scanner.hasNext()) {
                token = scanner.next().toCharArray();
                cntTokens++;

                for (char c : token) {
                    c = Character.toLowerCase(c);
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        word.append(c);
                    } else {
                        if (!word.isEmpty()) {
                            cntWords++;
                            changeMap(word.toString(), cntLines, cntWords, map);
                            word = new StringBuilder();
                        }
                    }
                }
                if (!word.isEmpty()) {
                    cntWords++;
                    changeMap(word.toString(), cntLines, cntWords, map);
                    word = new StringBuilder();
                }

                if (scanner.isEOL(cntTokens - 1)) {
                    cntLines++;
                    wordsInLine.add(cntWords);
                    cntWords = 0;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("no source file");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst), "utf-8"));
            Set<String> keys = map.keySet();
            ArrayList<int[]> numbers;
            try {

                for (String key : keys) {
                    numbers = map.get(key);
                    writer.write(key);
                    writer.write(" ");
                    writer.write(String.valueOf(numbers.size()));
                    writer.write(" ");
                    for (int i = 0; i < numbers.size(); i++) {
                        int line = numbers.get(i)[0];
                        int num = numbers.get(i)[1];
                        writer.write(String.valueOf(line));
                        writer.write(":");
                        writer.write(String.valueOf(wordsInLine.get(line - 1) - num + 1));
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
