package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.SkillRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {

    @Override
    public List<Skill> getAll() {
        Session session = null;
        List skills = new ArrayList<Skill>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            skills = session.createQuery("from Skill").list();
        } catch (Exception e) {
            System.out.println("Ошибка 'getAll'" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skills;
    }

    @Override
    public Skill getById(Integer id) {
        Session session = null;
        Skill skill = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            skill = session.get(Skill.class, id);
        } catch (Exception e) {
            System.out.println("Ошибка 'getById'" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Skill skill = session.get(Skill.class, id);
            session.delete(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
