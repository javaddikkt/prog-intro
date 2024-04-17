import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyScanner {
    private Reader reader;
    private boolean isClosed = false;
    private char[] block;
    private int real = 0;
    private int i = 0;
    private StringBuilder token = new StringBuilder();
    private int curToken = 0;
    private StringBuilder line = new StringBuilder();
    private ArrayList<String> tokens = new ArrayList<>();
    private String resLine = null;
    private ArrayList<Integer> eol = new ArrayList<>();

    public MyScanner(InputStream inputStream){
        reader = new InputStreamReader(inputStream);
    }
    public MyScanner (String string) {
        reader = new StringReader(string + System.lineSeparator());
    }
    public MyScanner (File file) throws FileNotFoundException {
        try {
            reader = new InputStreamReader(new FileInputStream(file), "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("encoding error");
        }
    }

    private void readBlock(){
        i = 0;
        block = new char[1024];
        try {
            real = reader.read(block);
        } catch (IOException e) {
            System.out.println("reading error");
        }
    }

    public void close() {
        try {
            reader.close();
            isClosed = true;
        } catch (IOException e) {
            System.out.println("closing error");
        }
    }

    private void read() {
        while (true) {
            if (i >= real) {
                readBlock();
                if (real == -1) {
                    if (!token.isEmpty()) {
                        tokens.add(token.toString());
                    }
                    eol.add(tokens.size() -1 );
                    resLine = line.toString();
                    return;
                }
            }
            if (block[i] == System.lineSeparator().charAt(0)) {
                resLine = line.toString();
                line = new StringBuilder();
                if (!token.isEmpty()) {
                    tokens.add(token.toString());
                    token = new StringBuilder();
                }
                eol.add(tokens.size() - 1);
                int j = 0;
                int len = System.lineSeparator().length();
                while (j < len) {
                    if (i == real) {
                        len = len - j;
                        j = 0;
                        readBlock();
                        if (real == -1) {
                            return;
                        }
                    }
                    i++;
                    j++;
                }

                return;
            } else if (Character.isWhitespace(block[i])) {
                if (!token.isEmpty()) {
                    tokens.add(token.toString());
                    token = new StringBuilder();
                }
                line.append(block[i]);
                i++;
            } else {
                token.append(block[i]);
                line.append(block[i]);
                i++;
            }
        }
    }

    public boolean hasNextLine() {
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (resLine != null) {
            return true;
        }
        if (real == -1) {
            return false;
        }
        read();
        return hasNextLine();
    }

    public String nextLine() {
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (resLine == null) {
            if (block == null) {
                read();
                return nextLine();
            } else {
                throw new NoSuchElementException();
            }
        }
        String res = resLine;
        resLine = null;
        return res;

    }

    public boolean hasNext() {
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (curToken < tokens.size()) {
            return true;
        }
        if (real == -1) {
            return false;
        }
        read();
        return hasNext();
    }

    public String next() {
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (curToken < tokens.size()) {
            return tokens.get(curToken++);
        }
        if (real == -1) {
            throw new NoSuchElementException();
        }
        read();
        return next();
    }

    public boolean hasNextInt() {
        if (hasNext()) {
            try {
                Integer.parseInt(tokens.get(curToken));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public int nextInt() {
        String s = next();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NoSuchElementException();
        }
    }

    public boolean isEOL(int index) {
        return eol.contains(index);
    }
}
