import menu.MainMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Start {

    public static void main(String[] args) {
        System.out.println("------------ Морской бой ------------");

        MainMenu mainMenu = new MainMenu();
        mainMenu.menuEntry();
    }
}