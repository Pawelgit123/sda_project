package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.AppUserDao;
import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.AppUser;
import sda.project.boardteamorganiser.model.Event;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;


public class UserManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("add")) {
            handleAddUser();
        } else if (words[1].equalsIgnoreCase("list")) {
            printUserList();
        }else  if (words[1].equalsIgnoreCase("mytime")){
            userShowAvailability();
        }else if (words[1].equalsIgnoreCase("myevents")){
            userShowEvents();
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

    private void printUserList() {
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        appUserEntityDao
                .findAll(AppUser.class)
                .forEach(p -> System.out.println("User: "+ p.getLogin() + " ID: " + p.getId()));
        System.out.println();
    }

    private void userShowAvailability(){
        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID użytkownika:");
        Long userId = scanner.nextLong();
        Optional<AppUser> optionalUser = appUserEntityDao.findById(AppUser.class, userId);
        AppUser appUser = optionalUser.get();

        if(appUserDao.existsUserWithId(userId)){
            System.out.println(appUser.getAvailabilitySet().toString());

        } else {
            System.err.print("Niepoprawny użytkownik!");
        }
    }

    private void userShowEvents(){
        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID użytkownika:");
        Long userId = scanner.nextLong();
        Optional<AppUser> optionalUser = appUserEntityDao.findById(AppUser.class, userId);
        AppUser appUser = optionalUser.get();

        if(appUserDao.existsUserWithId(userId)){
            Set<Event> eventSet = appUser.getEventSet();
            System.out.println();
            System.out.println("Lista eventow dla " +appUser.getLogin() + " [Host]");
            eventSet.forEach(p -> System.out.println(
                    p.getTitle()
                    + " [place:] "
                    + p.getPlace()
                    + " [ID:] "
                    + p.getId()));

            System.out.println();

        } else {
            System.err.print("Niepoprawny użytkownik!");
        }

    }

}
