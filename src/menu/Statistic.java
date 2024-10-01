package menu;

import util.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static util.Input.inputReader;

public class Statistic {
    public static long countOfGames = 0;
    public static long countOfWins = 0;
    public static long countOfLoses = 0;
    private MainMenu mainMenu = new MainMenu();

    public void showStatisticMenu() {
        // (countOfGames * countOfWins)/100 - формула процента
        float percent = ((float) (countOfGames * countOfWins)/100);

        if (countOfGames * countOfWins < 100)
            percent *= 100;

        System.out.println("Количество игр: " + countOfGames + " Победы: " + countOfWins +
                " Поражения: " + countOfLoses + " Процент побед: " + ((int)percent) + "%\n");

        System.out.println("1. Назад");

        try {
            String action = "";
            System.out.print("Ввод: ");
            action = inputReader.readLine();

            switch (action) {
                case "1":
                    System.out.println("##################################################");

                    mainMenu.menuEntry();

                    break;
                default:
                    System.out.println("Ошбика ввода, попробуйте ещё раз\n");
                    showStatisticMenu();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
