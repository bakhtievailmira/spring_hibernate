package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public  User findUser(String model, int series) {
      Query jpqlQuery = sessionFactory.getCurrentSession().createQuery("SELECT u FROM Car u WHERE u.model=:model and u.series = :series");
      jpqlQuery.setParameter("model", model);
      jpqlQuery.setParameter("series", series);
      Car car1 =(Car) jpqlQuery.getSingleResult();
      return (User) sessionFactory.getCurrentSession().createQuery("from User where car = " + car1.getId() ).getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
