package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String GET_USER_BY_CAR_PARAMETERS = """
            FROM User
            WHERE car.model = :model AND car.series = :series
            """;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCarParameters(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery(GET_USER_BY_CAR_PARAMETERS, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }

}
