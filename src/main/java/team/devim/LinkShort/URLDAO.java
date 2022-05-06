package team.devim.LinkShort;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class URLDAO {

    public void add(IncomingURL incomingURL) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(incomingURL);
        tx1.commit();
        session.close();
    }

    public void update(IncomingURL incomingURL) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(incomingURL);
        tx1.commit();
        session.close();
    }

    public void delete(IncomingURL incomingURL) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(incomingURL);
        tx1.commit();
        session.close();
    }

    public IncomingURL findByURL(String URL) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(IncomingURL.class, URL);
    }

    public IncomingURL findByKey(String key) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From IncomingURL where short_url=:key", IncomingURL.class)
                .setParameter("key", key);
        IncomingURL url = (IncomingURL) query.getSingleResult();
        return url;
    }


}
