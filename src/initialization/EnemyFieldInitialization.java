package initialization;

public class EnemyFieldInitialization implements Field{
    private static int[][] field = null;

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    // у врага будет другой showField, крч потом изменю
    @Override
    public String showField() {
        StringBuilder sb = new StringBuilder();
        sb.append("   А Б В Г Д Е Ж З И К\n");

        for (int i = 0; i < field.length; i++) {
            sb.append((i + 1));
            if (i + 1 < 10)
                sb.append(" ");
            for (int k = 0; k < field.length; k++) {
                sb.append("|");
                if (field[k][i] == 2)
                    sb.append("X");
                else if (field[k][i] == 3)
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("|\n");
        }

        return sb.toString();
    }

    @Override
    public void createField() {
        FieldInitialization fieldInitialization = new FieldInitialization();

        fieldInitialization.randomField();
        field = FieldInitialization.getField();
    }
}
