package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.AppUserDao;
import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.database.EventDao;
import sda.project.boardteamorganiser.model.AppUser;
import sda.project.boardteamorganiser.model.Event;

import java.util.Optional;
import java.util.Scanner;

public class EventManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("create")) {
            makeNewEvent();
        } else if (words[1].equalsIgnoreCase("list")) {
            printEventrList();
        } else if (words[1].equalsIgnoreCase("createh")){
            makeNewEventWithHost();
        } else if (words[1].equalsIgnoreCase("detail")){
            printDetailedEvent();
        }
    }

    private void makeNewEvent() {
        System.out.println("Creating...: (title) (place) ");
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.nextLine();
        String[] words = command.split(" ");

        EventDao eventDao = new EventDao();
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        if (!eventDao.existsEventWithTitle(words[0])) {
            Event event = Event.builder()
                    .title(words[0])
                    .place(words[1])
                    .build();

            eventEntityDao.saveOrUpdate(event);
            System.out.println("Event created: " + event.getId());
        } else {
            System.err.println("Event cannot be created. Event already exists.");
        }
    }

    private void makeNewEventWithHost() {
        System.out.println("Creating...: (title) (place) ");
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.nextLine();
        String[] words = command.split(" ");
        Scanner scanner1 = new Scanner(System.in);

        EventDao eventDao = new EventDao();
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        AppUserDao appUserDao = new AppUserDao();
        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();

        if (!eventDao.existsEventWithTitle(words[0])) {
            Event event = Event.builder()
                    .title(words[0])
                    .place(words[1])
                    .build();

            System.out.println("Podaj ID hosta:");
            Long userId = scanner1.nextLong();
            if (appUserDao.existsUserWithId(userId)){
                Optional<AppUser> optionalUser = appUserEntityDao.findById(AppUser.class, userId);
                AppUser appUser = optionalUser.get();
                event.setAppUser(appUser);
                appUser.getEventSet().add(event);
                appUserEntityDao.saveOrUpdate(appUser);
            }else {
                System.err.println("User not found. Event without host");
            }

            eventEntityDao.saveOrUpdate(event);
            System.out.println("Event created: " + event.getId());
        } else {
            System.err.println("Event cannot be created. Event already exists.");
        }
    }

    private void printEventrList() {
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        eventEntityDao
                .findAll(Event.class)
                .forEach(p -> System.out.println(
                        p.getTitle()
                        + " [place:] "
                        + p.getPlace()
                        + " [ID:] "
                        + p.getId()

                ));
        System.out.println();
    }

    private void printDetailedEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz ID spotkania:");
        Long eventId = scanner.nextLong();

        EntityDao<Event> eventEntityDao = new EntityDao<>();
        eventEntityDao
                .findById(Event.class,eventId)
                .ifPresent(p -> System.out.println(p.getTitle()
                        + " [place:] "
                        + p.getPlace()
                        + " [ID:] "
                        + p.getId()
                        + " [Host:] "
                        +p.getAppUser().getLogin()
                ));


        System.out.println();
    }

        //wymagania:
        // user (host)
        // event title

        //dodanie eventu do listy otwartych eventów (niepotwierdzonych)

        //builder ?

    public void showOpenEvents(){

    }

    public void showPendingEvents(){

        //lista eventów do których user dał availability i nie ma jeszcze confirmed

    }

    public void showRejectedEvents(){

        //lista eventów do których user dał availability, jest confirmed ale bez niego

    }

    public void showConfirmedEvents(){

        //lista eventów do których user dał availability, jest confirmed

    }

    public void showFinishedEvents(){

        //lista eventów do których user dał availability, jest confirmed i data minęła już

    }

}
