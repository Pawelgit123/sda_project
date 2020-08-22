package sda.project.boardteamorganiser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Initial version");

        Scanner scanner = new Scanner(System.in);




        String command;

        do {
            System.out.println("Wprowadz komende: ");
            command = scanner.nextLine();
            String[] words = command.split(" ");




        } while (!command.equalsIgnoreCase("quit"));
    }
}
