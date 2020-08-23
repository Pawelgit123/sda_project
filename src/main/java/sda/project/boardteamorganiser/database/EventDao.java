package sda.project.boardteamorganiser.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.project.boardteamorganiser.model.Event;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public boolean existsEventWithTitle(String searchedTitle) {
        List<Event> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Event> criteriaQuery = cb.createQuery(Event.class);
            Root<Event> rootTable = criteriaQuery.from(Event.class);


            criteriaQuery
                    .select(rootTable) // select * from rootTable
                    .where(
                            cb.equal(rootTable.get("title"), searchedTitle )
                    );


            list.addAll(session.createQuery(criteriaQuery).list());

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return !list.isEmpty();
    }

    public boolean existsEventWithId(Long searchedId) {
        List<Event> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Event> criteriaQuery = cb.createQuery(Event.class);
            Root<Event> rootTable = criteriaQuery.from(Event.class);


            criteriaQuery
                    .select(rootTable) // select * from rootTable
                    .where(
                            cb.equal(rootTable.get("id"), searchedId )
                    );


            list.addAll(session.createQuery(criteriaQuery).list());

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return !list.isEmpty();
    }
}
