import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class WordStatInput {
    public static void main (String[] args) {
        File src = new File(args[0]);
        File dst = new File(args[1]);
        //File src = new File("C:/Users/11038/proga/input.txt");
        //File dst = new File("C:/Users/11038/proga/output.txt");
        StringBuilder word = new StringBuilder();
        LinkedHashMap<String, Integer> list = new LinkedHashMap<>();
        char[] input;
        int count;
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
                            count = list.getOrDefault(String.valueOf(word), 0);
                            list.put(String.valueOf(word), count + 1);
                            word = new StringBuilder();
                        }
                    }
                }
                if (!word.isEmpty()) {
                    count = list.getOrDefault(String.valueOf(word), 0);
                    list.put(String.valueOf(word), count + 1);
                    word = new StringBuilder();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("No source file");
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst), "utf8"));
            try {
                for (String key : list.keySet()) {
                    out.write(key);
                    out.write(" ");
                    out.write(String.valueOf(list.get(key)));
                    out.write('\n');
                }
            } catch (IOException e) {
                System.out.println("Output error");
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Error in closing");
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("UTF-8 is not supported");
        } catch (FileNotFoundException e) {
            System.out.println("No destination file");
        }
    }
}