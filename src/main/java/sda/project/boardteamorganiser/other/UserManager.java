package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.AppUserDao;
import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.database.EventDao;
import sda.project.boardteamorganiser.model.AppUser;
import sda.project.boardteamorganiser.model.Availability;
import sda.project.boardteamorganiser.model.Event;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;


public class UserManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("add")) {
            handleAddUser();
        } else if (words[1].equalsIgnoreCase("list")) {
            printUserList();
        }else if (words[1].equalsIgnoreCase("time")) {
            userMakeAvailabilityToEvent();
        }else  if (words[1].equalsIgnoreCase("mytime"))
            userShowTime();
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

    private void userMakeAvailabilityToEvent(){

        EntityDao<Availability> availabilityEntityDao = new EntityDao<>();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        AppUserDao appUserDao = new AppUserDao();
        EventDao eventDao = new EventDao();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID użytkownika:");
        Long userId = scanner.nextLong();

        System.out.println("Podaj ID spotkania:");
        Long eventId = scanner.nextLong();

//        System.out.println("Podaj dzień: (dzień miesiąc rok)");
        System.out.println("### zapisuję datę dzisiejszą ###");
        LocalDate date = LocalDate.now();

//        String dateText = scanner.nextLine();
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY MM dd");
//        LocalDate date = LocalDate.parse(dateText,dateFormat);

        System.out.println("Podaj liczbę wolnych godzin:");
        int hours = scanner.nextInt();

        Optional<AppUser> optionalUser = appUserEntityDao.findById(AppUser.class, userId);
        Optional<Event> optionalEvent = eventEntityDao.findById(Event.class, eventId);

        if(appUserDao.existsUserWithId(userId) && eventDao.existsEventWithId(eventId)) {
            AppUser appUser = optionalUser.get();
            Event event = optionalEvent.get();

            Availability availability = new Availability(date, hours);
            availabilityEntityDao.saveOrUpdate(availability);

            appUser.getAvailabilitySet().add(availability);
            appUserEntityDao.saveOrUpdate(appUser);

            event.getAvailabilitySet().add(availability);
            eventEntityDao.saveOrUpdate(event);

        }else {
            System.err.print("Podane błędne ID użytkownika lub spotkania");
        }
    }

    private void userShowTime(){
        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        EntityDao<Availability> availabilityEntityDao = new EntityDao<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID użytkownika:");
        Long userId = scanner.nextLong();
        Optional<AppUser> optionalUser = appUserEntityDao.findById(AppUser.class, userId);
        AppUser appUser = optionalUser.get();

        if(appUserDao.existsUserWithId(userId)){
            availabilityEntityDao.findAll(Availability.class)
                    .stream()
                    .filter(p ->p.getAppUser().equals(appUser))
                    .forEach(System.out::println);

        } else {
            System.err.print("Niepoprawny użytkownik!");
        }
    }

}
