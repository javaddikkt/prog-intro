package markup;

import java.util.List;

public class Strikeout extends Markup{
    public Strikeout (List<Markup> list) {
        super(list);
    }
    @Override
    String getSymbol() {
        return "~";
    }

    @Override
    String getBBSymbol() {
        return "s";
    }
}
