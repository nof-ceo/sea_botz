package menu;

import initialization.UserFieldInitialization;

import java.io.IOException;

import static util.Input.inputReader;

public class UserMenu {
    private UserFieldInitialization userFieldInitialization = new UserFieldInitialization();
    private MainMenu mainMenu = new MainMenu();
    public void userMenu() {
        System.out.println(userFieldInitialization.showField());

        System.out.println("1. Ручное размещение (not implemented)");
        System.out.println("2. Случайное размещение");
        System.out.println("3. Назад");

        try {
            String action = "";
            System.out.print("Ввод: ");
            action = inputReader.readLine();

            switch (action) {
                case "1":
                    userMenu();
                    break;
                case "2":
                    userFieldInitialization.createField();
                    userMenu();
                    break;
                case "3":
                    System.out.println("##################################################");

                    mainMenu.menuEntry();

                    break;
                default:
                    System.out.println("⛔️Ошбика ввода, попробуйте ещё раз⛔️\n");
                    userMenu();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
