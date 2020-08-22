package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.AppUserDao;
import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.AppUser;


public class UserManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("add")) {
            handleAddUser(words);
        } else if (words[1].equalsIgnoreCase("list")) {
            printUserList();
        }
    }

    private void handleAddUser(String[] words) {
        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        if (!appUserDao.existsUserWithLogin(words[2])) {
            AppUser appUser = AppUser.builder()
                    .login(words[2])
                    .password(words[3])
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
                .forEach(System.out::println);
    }

}
