package com.bezngor.crud_hibernate.utils;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.model.TeamStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed.");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Skill getSkillById(Integer id) {
        Skill skill = null;

        try(Session session = getSessionFactory().openSession())
        {
            skill = session.get(Skill.class, id);
        }
        return skill;
    }

    public static Developer getDeveloperById(Integer id) {
        Developer developer = null;

        try (Session session = getSessionFactory().openSession())
        {
            developer = session.get(Developer.class,id);
        }
        return developer;
    }

    public static TeamStatus getStatusTeam(String statusId) {
        return Arrays.stream(TeamStatus.values())
                .filter(v -> v.getValue() == Integer.parseInt(statusId))
                .findFirst()
                .orElse(null);
    }
}
