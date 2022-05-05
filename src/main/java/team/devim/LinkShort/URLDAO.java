package team.devim.LinkShort;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class URLDAO {

    public void add(IncomingURL incomingURL){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(incomingURL);
        tx1.commit();
        session.close();
    }

    public void update(IncomingURL incomingURL){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(incomingURL);
        tx1.commit();
        session.close();
    }

    public void delete(IncomingURL incomingURL){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(incomingURL);
        tx1.commit();
        session.close();
    }

    public String findByURL(String URL){
        System.out.println(URL);
        System.out.println();
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(IncomingURL.class, URL).getShort_url();
    }
}
