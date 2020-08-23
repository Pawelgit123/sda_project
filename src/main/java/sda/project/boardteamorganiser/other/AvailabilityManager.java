package sda.project.boardteamorganiser.other;

import sda.project.boardteamorganiser.database.EntityDao;
import sda.project.boardteamorganiser.model.Availability;

public class AvailabilityManager {

    public void handle(String[] words) {
        if (words[1].equalsIgnoreCase("list")) {
            printAvailabilityrList();}
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
                                        +p.getEvent()
                                + " [availability ID:] "
                                + p.getId()
                ));
        System.out.println();
    }
}
