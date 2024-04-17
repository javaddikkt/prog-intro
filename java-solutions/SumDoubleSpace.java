import static java.lang.Character.isSpaceChar;

public class SumDoubleSpace {
    public static void main(String[] args) {
        double sum = 0;
        StringBuilder num;
        char[] chars;
        
        for (String line : args) {
            chars = (line + " ").toCharArray();
            num = new StringBuilder();
            for (Character element : chars) {
                if (isSpaceChar(element)) {
                    if (num.length() > 0) {
                        sum += Double.parseDouble(num.toString());
                    }
                    num = new StringBuilder();
                } else {
                    num.append(element);
                }
            }
        }
        System.out.println(sum);
    }
}