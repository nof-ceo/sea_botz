package initialization;

import java.util.Random;

public class UserFieldInitialization implements Field{

    // если null, значит игрок ещё не задал расположение кораблей
    private static int[][] field = new int[10][10];
    public static boolean isDefault = true;

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

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
                if (field[k][i] != 0)
                    sb.append("К");
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
