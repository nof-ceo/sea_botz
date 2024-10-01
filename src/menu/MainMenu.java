package menu;

import initialization.EnemyFieldInitialization;
import initialization.Field;
import initialization.UserFieldInitialization;

import java.io.IOException;
import java.util.Random;

import static util.Input.inputReader;

public class MainMenu {
    private static Statistic statistic = new Statistic();
    private static UserMenu userMenu = new UserMenu();
    private Field userField = new UserFieldInitialization();
    private Field enemyField = new EnemyFieldInitialization();
    private Game game;
    public void menuEntry() {
        System.out.println("Для навигации в меню используйте ввод (например, для выбора вкладки статистики введите 1)\n");

        System.out.println("1. Статистика");
        System.out.println("2. Расположение кораблей");
        System.out.println("3. Начать матч");

        try {
            String action = "";
            System.out.print("Ввод: ");
            action = inputReader.readLine();

            switch (action) {
                case "1":
                    System.out.println("##################################################");

                    statistic.showStatisticMenu();

                    break;
                case "2":
                    System.out.println("##################################################");

                    userMenu.userMenu();
                    break;
                case "3":
                    System.out.println("##################################################");

                    if (UserFieldInitialization.isDefault)
                        userField.createField();

                    enemyField.createField();

                    game = new Game(userField.getField());
                    game.startGame(new Random().nextInt(2));

                    userMenu.userMenu();

                    break;
                default:
                    System.out.println("Ошбика ввода, попробуйте ещё раз\n");
                    menuEntry();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
