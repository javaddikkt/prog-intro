package markup;

public class Text extends Markup {
    public Text(String string) {
        super(string);
    }

    @Override
    String getSymbol() {
        return "";
    }

    @Override
    String getBBSymbol() {
        return null;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(string);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(string);
    }
}
