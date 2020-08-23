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

public class AvailabilityManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("list")) {
            printAvailabilityrList();
        }else if (words[1].equalsIgnoreCase("make")){
            userMakeAvailabilityToEvent();
        }
    }

    private void printAvailabilityrList() {
        EntityDao<Availability> eventEntityDao = new EntityDao<>();
        eventEntityDao
                .findAll(Availability.class)
                .forEach(p -> System.out.println(
                                p.getAppUser()
                                + " [date:] "
                                + p.getDate()
                                        + " Spotkanie: "
                                        + p.getEvent()
                                + " [availability ID:] "
                                + p.getId()
                ));
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

            availability.setAppUser(appUser);
            availability.setEvent(event);
            availabilityEntityDao.saveOrUpdate(availability);

        }else {
            System.err.print("Podane błędne ID użytkownika lub spotkania");
        }
    }


}
