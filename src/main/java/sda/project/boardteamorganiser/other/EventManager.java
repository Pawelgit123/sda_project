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
            makeNewEvent();
        } else if (words[1].equalsIgnoreCase("show")) {
            printEventrList();
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

    private void printEventrList() {
        EntityDao<Event> eventEntityDao = new EntityDao<>();
        eventEntityDao
                .findAll(Event.class)
                .forEach(p -> System.out.println(
                        p.getTitle()
                        + " [place:] "
                        + p.getPlace()
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
