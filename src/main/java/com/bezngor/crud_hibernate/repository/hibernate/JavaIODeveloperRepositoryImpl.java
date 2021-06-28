package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.DeveloperRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public List<Developer> getAll() {
        List<Developer> devs = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            devs = session.createQuery("from Developer").list();
            for (Developer d : devs) {
                List<Skill> skills = new ArrayList<>();
                for (Skill s : d.getSkills()) {
                    skills.add(s);
                }
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
            session.beginTransaction();
            dev = session.get(Developer.class, id);
            List<Skill> skills = new ArrayList<>();
            for (Skill s : dev.getSkills()) {
                skills.add(s);
            }
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
}
