package com.bezngor.crud_hibernate.repository.hibernate;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.model.Team;
import com.bezngor.crud_hibernate.model.TeamStatus;
import com.bezngor.crud_hibernate.repository.TeamRepository;
import com.bezngor.crud_hibernate.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.QueryHint;
import java.util.*;

public class JavaIOTeamRepositoryImpl implements TeamRepository {
    @Override
    public List<Team> getAll() {
        List teams = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            teams = session.createQuery("from Team t left join fetch t.devs").list();
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
            team = (Team)session.createQuery(
                    "from Team t left join fetch t.devs where t.id =: team_id")
                    .setParameter("team_id", id)
                    .uniqueResult();
            for (Developer d : team.getDevs()) {
                d.getSkills();
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


    public static Developer getDeveloperById(Integer id) {
        Developer developer = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
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
