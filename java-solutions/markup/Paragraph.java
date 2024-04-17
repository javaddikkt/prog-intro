package markup;

import java.util.List;

public class Paragraph extends Markup {
    public Paragraph(List<Markup> list) {
        super(list);
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
    public void toBBCode(StringBuilder sb) {
        for (Markup obj : list) {
            obj.toBBCode(sb);
        }
    }
}
