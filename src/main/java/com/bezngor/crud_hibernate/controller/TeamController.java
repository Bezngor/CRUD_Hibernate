package com.bezngor.crud_hibernate.controller;

import com.bezngor.crud_hibernate.model.Developer;
import com.bezngor.crud_hibernate.model.Team;
import com.bezngor.crud_hibernate.model.TeamStatus;
import com.bezngor.crud_hibernate.repository.TeamRepository;

import java.util.List;

public class TeamController {
    private TeamRepository teamRepo;

    public TeamController(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public Team create(String name, TeamStatus status, List<Developer> devs) {
        return teamRepo.save(new Team(name, status, devs));
    }

    public Team update(Integer id, String name, TeamStatus status, List<Developer> devs) {
        return teamRepo.update(new Team(id, name, status, devs));
    }

    public List<Team> getAll() {
        return teamRepo.getAll();
    }

    public Team getById(Integer id) {
        return teamRepo.getById(id);
    }

    public void deleteById(Integer id) {
        teamRepo.deleteById(id);
    }
}
