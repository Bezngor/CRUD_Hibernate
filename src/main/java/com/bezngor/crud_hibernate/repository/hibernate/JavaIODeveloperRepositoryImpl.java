package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.DeveloperRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.*;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public List<Developer> getAll() {
        List<Developer> devs = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List listDev = session.createQuery("from Developer d left join fetch d.skills").list();
            Set<Developer> setDev = new HashSet<>(listDev);
            for (Developer d : setDev) {
                devs.add(d);
            }
        } catch (Exception e) {
            System.out.println("Ошибка 'getAll'" + e);
        }
        return devs;
    }

    @Override
    public Developer getById(Integer id) {
        Developer dev = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            dev = (Developer) session.createQuery(
                    "select distinct d from Developer d left join fetch d.skills where d.id =: dev_id")
                    .setParameter("dev_id", id)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Ошибка 'getById'" + e);
        }
        return dev;
    }

    @Override
    public Developer save(Developer dev) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(dev);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return dev;
    }

    @Override
    public Developer update(Developer dev) {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.update(dev);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return dev;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            Developer dev = session.get(Developer.class, id);
            session.delete(dev);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении" + e);
        }
    }

    public static Skill getSkillById(Integer id) {
        Skill skill = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            skill = session.get(Skill.class, id);
        }
        return skill;
    }

}
