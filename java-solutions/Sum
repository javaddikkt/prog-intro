public class Sum {
    public static void main (String[] args) {
        int sum = 0;
        StringBuilder num;
        char[] chars;

        for (String line : args) {
            chars = line.toCharArray();
            num = new StringBuilder();
            for (Character element : chars) {
                if (Character.isWhitespace(element)) {
                    if (!num.isEmpty()) {
                        sum += Integer.parseInt(num.toString());
                    }
                    num = new StringBuilder();
                } else {
                    num.append(element);
                }
            }
            if (!num.isEmpty()) {
                sum += Integer.parseInt(num.toString());
            }
        }
        System.out.println(sum);
    }
}
