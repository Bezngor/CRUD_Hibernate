package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.model.Team;
import com.bezngor.crud_hibernate.repository.TeamRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class JavaIOTeamRepositoryImpl implements TeamRepository {
    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            teams = session.createQuery("from Team").list();
            for (Team t : teams) {
                List<Developer> devs = new ArrayList<>();
                for (Developer d : t.getDevs()) {
                    devs.add(d);
                    List<Skill> skills = new ArrayList<>();
                    for (Skill s : d.getSkills()) {
                        skills.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка 'getAll'" + e);
        }
        return teams;
    }

    @Override
    public Team getById(Integer id) {
        Team team = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            team = session.get(Team.class, id);
            List<Developer> devs = new ArrayList<>();
            for (Developer d : team.getDevs()) {
                devs.add(d);
                List<Skill> skills = new ArrayList<>();
                for (Skill s : d.getSkills()) {
                    skills.add(s);
                }
            }
        }
        return team;
    }

    @Override
    public Team save(Team team) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(team);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return team;
    }

    @Override
    public Team update(Team team) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.update(team);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при вставке" + e);
        }
        return team;
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            Team team = session.get(Team.class, id);
            session.delete(team);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка при удалении" + e);
        }
    }
}
