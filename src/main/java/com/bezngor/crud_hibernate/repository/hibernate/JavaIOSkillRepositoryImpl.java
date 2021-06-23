package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.SkillRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Skill> getAll() {

        return null;
    }

    @Override
    public Skill getById(Integer integer) {
        return null;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
