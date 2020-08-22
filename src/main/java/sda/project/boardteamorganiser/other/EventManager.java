package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.database.EventDao;
import sda.project.boardteamorganiser.model.Event;

import java.util.Scanner;

public class EventManager {
    private Scanner scanner = new Scanner(System.in);

    //dodawanie i listowanie eventów

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("create")) {
            makeNewEvent(words);
        } else if (words[1].equalsIgnoreCase("show")) {
            printEventrList();
        }
    }

    private void makeNewEvent(String[] words) {
        EventDao eventDao = new EventDao();
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        if (!eventDao.existsUserWithLogin(words[1])) {
            Event event = Event.builder()
                    .title(words[2])
                    .build();

            eventEntityDao.saveOrUpdate(event);
            System.out.println("Event created: " + event.getId());
        } else {
            System.err.println("User cannot be saved. Login already exists.");
        }
    }

    public void printEventrList() {
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        eventEntityDao
                .findAll(Event.class)
                .forEach(System.out::println);
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
