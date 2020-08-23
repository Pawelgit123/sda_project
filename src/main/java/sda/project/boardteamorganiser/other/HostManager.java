package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.AppUser;
import sda.project.boardteamorganiser.model.Event;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class HostManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("myevents")) {
            showMyHostedEvents();}
        }

    private void showMyHostedEvents(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj swoje ID:");
        Long userId = scanner.nextLong();

        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        Optional<AppUser> optionalAppUser = appUserEntityDao.findById(AppUser.class, userId);
        AppUser appUser = optionalAppUser.get();

        appUser.getEventSet().forEach(p -> System.out.println(
         "\n" + p.getTitle()
        + " [place:] " + p.getPlace()
        + " [confirmed?] " + p.getConfirmed()
        + "\n"
        + "[osoby:] " + Arrays.toString(p.getAvailabilitySet().toArray())));


//        + Arrays.toString(p.getAvailabilitySet().toArray()) + "\n"));


    }

    private void hostAcceptEvent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj swoje ID:");
        Long userId = scanner.nextLong();

        EntityDao<AppUser> appUserEntityDao = new EntityDao<>();
        Optional<AppUser> optionalAppUser = appUserEntityDao.findById(AppUser.class, userId);
        AppUser appUser = optionalAppUser.get();

        // sprawdzenie usera pyt któe spotkanie? lista spotkan etc

        System.out.println("Podaj ID spotkania:");
        Long eventId = scanner.nextLong();
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        Optional<Event> optionalEvent = eventEntityDao.findById(Event.class, eventId);
        Event event = optionalEvent.get();

        System.out.println("Dostęnność osób:");
        System.out.println(Arrays.toString(event.getAvailabilitySet().toArray()));

        // wybor availability po id usera ale z pelnym widokiem
        //posortowane datami


    }
}
