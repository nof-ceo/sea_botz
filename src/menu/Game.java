package menu;

import initialization.EnemyFieldInitialization;
import initialization.Field;
import initialization.FieldInitialization;
import util.Input;
import util.Letters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * число 2 на поле - попадание
 * число 3 - мимо
 */
public class Game {
    private int[][] userField = new int[10][10];
    private int[][] enemyField = new int[10][10];
    private Field enemyFieldInitialization = new EnemyFieldInitialization();
    private int countOfUserCells = 20;
    private int countOfEnemyCells = 20;
    private static final Random RANDOM = new Random();

    public Game(int[][] userFieldOriginal) {

        enemyField = enemyFieldInitialization.getField();

        for(int i = 0; i < userFieldOriginal.length; i++)
            userField[i] = userFieldOriginal[i].clone();
    }
    public void startGame(int turn) {
        if (countOfUserCells == 0) {
            System.out.println("😭😭😭 Вы проиграли 😭😭😭\n");
            Statistic.countOfGames++;
            Statistic.countOfLoses++;
        } else if (countOfEnemyCells == 0) {
            System.out.println("Поздравляем!🥳 Вы победили! 🤩🤩\n");
            Statistic.countOfGames++;
            Statistic.countOfWins++;
        } else {

            if (turn == 1) {
                System.out.println("Ход противника");
                hitUserShip();
            } else {
                System.out.println();
                System.out.println(enemyFieldInitialization.showField());

                System.out.print("Ваш ход: ");

                try {

                    String position = Input.inputReader.readLine();

                    char letter = position.charAt(0);
                    if (!Letters.LETTERS.containsKey(letter))
                        throw new NumberFormatException();
                    else {
                        int x = Letters.LETTERS.get(letter);
                        int y = Integer.parseInt(position.substring(1)) - 1;

                        if (y < 0 || y > 9 || enemyField[x][y] > 1)
                            throw new Exception();

                        hitEnemyShip(x, y);
                    }
                } catch (Exception e) {
                    System.out.println("⛔️Неверные координаты, попробуйте ещё раз⛔️");
                    startGame(turn);
                }
            }

            // XOR для изменения хода 0->1->0
            turn ^= 1;

            startGame(turn);
        }
    }

    private void hitUserShip() {
        int x = -1;
        int y = -1;

        do {
            x = FieldInitialization.getX();
            y = FieldInitialization.getY();
        } while (userField[x][y] > 1);

        if (userField[x][y] == 0) {
            userField[x][y] = 3;
            System.out.println("Мимо");
        }
        else {
            userField[x][y] = 2;
            System.out.println("Попал");
            countOfUserCells--;
        }
    }

    private void hitEnemyShip(int x, int y) {
        if (enemyField[x][y] == 0) {
            enemyField[x][y] = 3;
            System.out.println("Мимо\n");
        }
        else {
            enemyField[x][y] = 2;
            System.out.println("Попал\n");
            countOfEnemyCells--;
        }
    }
}
