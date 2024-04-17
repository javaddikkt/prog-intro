package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Md2Html {
    private static final Map<String, String> markSymbols = new HashMap<>();
    static {
        markSymbols.put("*", "em");
        markSymbols.put("_", "em");
        markSymbols.put("`", "code");
        markSymbols.put("%", "var");
    }

    private static final Map<String, String> markDoubleSymbols = new HashMap<>();
    static {
        markDoubleSymbols.put("**", "strong");
        markDoubleSymbols.put("__", "strong");
        markDoubleSymbols.put("--", "s");
    }

    private static final Map<String, String> specialSymbols = new HashMap<>();
    static {
        specialSymbols.put("<", "&lt;");
        specialSymbols.put(">", "&gt;");
        specialSymbols.put("&", "&amp;");
    }

    public static void main(String[] args) {
        File src = new File(args[0]);
        File dst = new File(args[1]);
        try {
            MyScanner scanner = new MyScanner(src);
            try {
                Writer writer = new OutputStreamWriter(new FileOutputStream(dst), StandardCharsets.UTF_8);
                List<String> paragraph = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isBlank() || line.isEmpty()) {
                        if (!paragraph.isEmpty()) {
                            try {
                                convert(paragraph, writer);
                            } catch (IOException e) {
                                System.out.println("writing error");
                            }
                            paragraph = new ArrayList<>();
                        }

                    } else {
                        paragraph.add(line);
                    }
                }
                if (!paragraph.isEmpty()) {
                    try {
                        convert(paragraph, writer);
                    } catch (IOException e) {
                        System.out.println("writing error");
                    }
                }
                writer.close();
                scanner.close();
            } catch (UnsupportedEncodingException e) {
                System.out.println("encoding error");
            } catch (IOException e) {
                System.out.println("file writer error");
            }

        } catch (FileNotFoundException e) {
            System.out.println("source file not found");
        }
    }

    public static void convert(List<String> paragraph, Writer writer) throws IOException{
        String firstLine = paragraph.get(0);
        String sym = markHeading(firstLine);
        List<String> markings = new ArrayList<>();

        writer.write("<" + sym + ">");
        if (sym.charAt(0) == 'h') {
            int skip = Integer.parseInt(String.valueOf(sym.charAt(1))) + 1;
            firstLine = firstLine.substring(skip);
            paragraph.set(0, firstLine);
        }
        for (int j = 0; j < paragraph.size(); j++) {
            String line = paragraph.get(j);
            char[] charLine = line.toCharArray();
            int i = 0;
            while (i < charLine.length) {
                Trio converted = convertSymbol(charLine, i);
                String token = converted.string;
                if (converted.bool) {
                    if (!markings.isEmpty() && token.equals(markings.get(markings.size() - 1))) {
                        markings.remove(markings.size() - 1);
                        token = "</" + token + ">";
                    } else {
                        markings.add(token);
                        token = "<" + token + ">";
                    }
                }
                writer.write(token);
                i += converted.integer;
            }
            if (j != paragraph.size() - 1) {
                writer.write(System.lineSeparator());
            }
        }
        writer.write("</" + sym + ">");
        writer.write(System.lineSeparator());

    }

    public static Trio convertSymbol(char[] charLine, int start) {
        String cur = String.valueOf(charLine[start]);
        String next;
        String find;

        if (start < charLine.length - 1) {
            next = String.valueOf(charLine[start + 1]);
        } else {
            next = "";
        }
        if (cur.equals("\\")) {
            if (next.equals("*") || next.equals("_") || next.equals("%")) {
                return new Trio(next, 2, false);
            }
        }
        if (cur.equals("*") || cur.equals("_")) {
            if (next.isEmpty() || (Character.isWhitespace(next.charAt(0)))) {
                if (start > 0 && Character.isWhitespace(charLine[start - 1]))
                    return new Trio(cur, 1, false);
            }
        }
        find = specialSymbols.get(cur);
        if (find != null) {
            return new Trio(find, 1, false);
        }

        String token;
        int skip;
        if (cur.equals(next)) {
            token = cur + next;
            find = markDoubleSymbols.get(token);
            skip = 2;
        } else {
            token = cur;
            find = markSymbols.get(token);
            skip = 1;
        }
        if (find != null) {
            return new Trio(find, skip, true);
        }

        return new Trio(cur, 1, false);
    }

    public static String markHeading(String line) {
        char[] firstLine = line.toCharArray();
        int i = 0;
        while (firstLine[i] == '#') {
            i++;
        }
        if (Character.isWhitespace(firstLine[i]) && i > 0) {
            return "h" + i;
        } else {
            return "p";
        }
    }
}
