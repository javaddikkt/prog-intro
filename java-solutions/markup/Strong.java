package markup;

import java.util.List;

public class Strong extends Markup{
    public Strong (List<Markup> list) {
        super(list);
    }
    @Override
    String getSymbol() {
        return "__";
    }

    @Override
    String getBBSymbol() {
        return "b";
    }
}
