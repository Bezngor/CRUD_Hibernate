package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.SkillRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {

    @Override
    public List<Skill> getAll() {
        List skills = new ArrayList<Skill>();

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            skills = session.createQuery("from Skill").list();
        } catch (Exception e) {
            System.out.println("Ошибка 'getAll'" + e);
        }
        return skills;
    }

    @Override
    public Skill getById(Integer id) {
        Skill skill = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            skill = session.get(Skill.class, id);
        } catch (Exception e) {
            System.out.println("Ошибка 'getById'" + e);
        }
        return skill;
    }

    @Override
    public Skill save(Skill skill) {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            Skill skill = session.get(Skill.class, id);
            session.delete(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении" + e);
        }
    }
}
