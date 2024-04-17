package markup;

import java.util.List;

public abstract class Markup {
    List<Markup> list;
    String string;

    public Markup (List<Markup> list) {
        this.list = list;
    }
    public Markup (String string) {
        this.string = string;
    }

    abstract String getSymbol();

    abstract String getBBSymbol();

    public void toMarkdown(StringBuilder sb) {
        sb.append(getSymbol());
        for (Markup obj : list) {
            obj.toMarkdown(sb);
        }
        sb.append(getSymbol());
    }

    public void toBBCode(StringBuilder sb) {
        String sym = getBBSymbol();
        sb.append("[");
        sb.append(sym);
        sb.append("]");
        for (Markup obj : list) {
            obj.toBBCode(sb);
        }
        sb.append("[/");
        sb.append(sym);
        sb.append("]");
    }


}
