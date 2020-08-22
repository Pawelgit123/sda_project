package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.AppUserDao;
import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.AppUser;

import java.util.Scanner;


public class UserManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("add")) {
            handleAddUser();
        } else if (words[1].equalsIgnoreCase("list")) {
            printUserList();
        }
    }

    private void handleAddUser() {
        System.out.println("Adding...: (login) (password) ");
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.nextLine();
        String[] words = command.split(" ");

        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        if (!appUserDao.existsUserWithLogin(words[0])) {
            AppUser appUser = AppUser.builder()
                    .login(words[0])
                    .password(words[1])
                    .build();

            appUserEntityDao.saveOrUpdate(appUser);
            System.out.println("User saved: " + appUser.getId());
        } else {
            System.err.println("User cannot be saved. Login already exists.");
        }
    }

    public void printUserList() {
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        appUserEntityDao
                .findAll(AppUser.class)
                .forEach(p -> System.out.println("User: "+ p.getLogin()));
        System.out.println();
    }

}
