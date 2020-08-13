package sda.project.boardteamorganiser.database;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory ourSessionFactory;

    // blok statyczny - fragment kodu który wykonuje się 1 raz przy starcie klasy
    static {
        try {
            System.out.println("Konfiguruję hibernate.");

            /// ta klasa domyślnie odwołuje się do pliku konfiguracyjnego hibernate w formacie podanym w resources
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        }catch (HibernateException he){
            System.err.println(he.getMessage());
            throw he;
        }
    }

    // wygenerowany getter
    public static SessionFactory getOurSessionFactory() {
        return ourSessionFactory;
    }
}

