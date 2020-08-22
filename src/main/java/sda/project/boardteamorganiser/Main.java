package sda.project.boardteamorganiser;

import sda.project.boardteamorganiser.other.EventManager;
import sda.project.boardteamorganiser.other.UserManager;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Initial version");
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        EventManager eventManager = new EventManager();
        MenuHelper menuHelper = new MenuHelper();
        String command;

        do {
            System.out.println("BoardTeamOrganiser 0.1");
            menuHelper.printOptions();
            System.out.println("Wprowadz komende: ");
            command = scanner.nextLine();

            String[] words = command.split(" ");
            if(words[0].equalsIgnoreCase("user")){
                userManager.handle(words);
            }else if(words[0].equalsIgnoreCase("event")){
                eventManager.handle(words);
            }


            //opcje menu

            //sprawdzenie czy user istnieje
            //user stworzenie eventu
            //user sprawdzenie dostepnych eventów
            //user stworzenie availability do konkrentnego eventa
            //user (host) dodanie availability innych userów do eventu
            //user (host) akceptacja eventu z odpowiednimi availability
            //user otrzymanie info o potwierdznonym eventcie
            //user set active / set unactive
            //user pokaż availability hosta danego eventu

            //logowanie usera

        } while (!command.equalsIgnoreCase("quit"));

    }
}



