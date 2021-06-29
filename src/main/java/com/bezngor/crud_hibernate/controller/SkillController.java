package com.bezngor.crud_hibernate.controller;

import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.SkillRepository;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepo;

    public SkillController(SkillRepository skillRepo) {
        this.skillRepo = skillRepo;
    }

    public Skill create(String name) {
        return skillRepo.save(new Skill(name));
    }

    public Skill update(Integer id, String name) {
        return skillRepo.update(new Skill(id, name));
    }

    public List<Skill> getAll() {
        return skillRepo.getAll();
    }

    public Skill getById(Integer id) {
        return skillRepo.getById(id);
    }

    public void deleteById(Integer id) {
        skillRepo.deleteById(id);
    }
}
