package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.AppUser;

public class MenuHelper {

    public static void printOptions(){
        System.out.println(" [user list] ");
        System.out.println(" [user add (nick) (login) (password)] ");

    }

    private static void printUserList(String[] words) {
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        appUserEntityDao
                .findAll(AppUser.class)
                .forEach(System.out::println);
    }

}
