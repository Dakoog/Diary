package diary;

import entity.Post;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class DAO{

    // dodanie nowego użytkownika pamiętnika
    public void save(User user) {
        SessionFactory sessionFactory =HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);

        transaction.commit();
        session.close();

    }
    // pobranie wpisanego już użytkownika
    public User getUser(String login) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("login"), login));

        Query<User> query = session.createQuery(cq);

          User user = query.getSingleResult();

        transaction.commit();
        session.close();
        return user;
    }

    public List<Post> getPosts(Long userId) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        // pobieramy najpierw użytkownika, a potem jego id
        cq.select(root).where(cb.equal(root.get("user").get("id"), userId)).orderBy(cb.asc(root.get("postDate")));

        Query<Post> query = session.createQuery(cq);
        List<Post> posts = query.getResultList();


        transaction.commit();
        session.close();
        return posts;

    }

    public void newPost(Long userId, String title, String text) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(userId);
        Post post = new Post(null, user, title, new Date(), text);
        session.persist(post);

        transaction.commit();
        session.close();

    }
}
