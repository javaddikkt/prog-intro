package markup;

import java.util.List;

public class Emphasis extends Markup{
    public Emphasis(List<Markup> list) {
        super(list);
    }
    @Override
    String getSymbol() {
        return "*";
    }

    @Override
    String getBBSymbol() {
        return "i";
    }
}
