package test.com.bezngor.crud_hibernate.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bezngor.crud_hibernate.controller.SkillController;
import com.bezngor.crud_hibernate.model.Skill;
import com.bezngor.crud_hibernate.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkillControllerTest {
    private SkillRepository skillRepository;
    private SkillController skillController;
    private Skill skill;

    @BeforeEach
    void setUp() {
        skillRepository = mock(SkillRepository.class);
        skillController = new SkillController(skillRepository);
        skill = new Skill("Java");
    }

    @Test
    public void testCreateTrue() {
        when(skillRepository.save(any())).thenReturn(skill);
        assertSame(skill, skillController.create("Java"));
        verify(skillRepository).save(any());
        assertTrue(skillController.getAll().isEmpty());
    }

    @Test
    public void testCreateFalse() {
        when(skillRepository.save(any())).thenReturn(skill);
        assertFalse(skillController.create(any()) == null);
    }

    @Test
    public void testUpdateTrue() {
        when(skillRepository.update(any())).thenReturn(skill);
        assertSame(skill, skillController.update(1, "Java"));
        verify(skillRepository).update(any());
        assertTrue(skillController.getAll().isEmpty());
    }

    @Test
    public void testUpdateFalse() {
        when(skillRepository.update(any())).thenReturn(skill);
        assertFalse(skillController.update(1, any()) == null);
    }

    @Test
    public void testGetAllTrue() {
        ArrayList<Skill> skillList = new ArrayList<>();
        when(skillRepository.getAll()).thenReturn(skillList);
        List<Skill> actualAll = skillController.getAll();
        assertSame(skillList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(skillRepository).getAll();
    }

    @Test
    public void testGetAllFalse() {
        ArrayList<Skill> skillList = new ArrayList<>();
        when(skillRepository.getAll()).thenReturn(skillList);
        assertFalse(skillController.getAll() == null);
    }

    @Test
    public void testGetByIdTrue() {
        when(skillRepository.getById(any())).thenReturn(skill);
        assertSame(skill, skillController.getById(1));
        verify(skillRepository).getById(any());
        assertTrue(skillController.getAll().isEmpty());
    }

    @Test
    public void testGetByIdFalse() {
        when(skillRepository.getById(any())).thenReturn(skill);
        assertFalse(skillController.getById(1)  ==  null);
    }

    @Test
    public void testDeleteByIdTrue() {
        doNothing().when(skillRepository).deleteById(any());
        skillController.deleteById(1);
        verify(skillRepository).deleteById(any());
        assertTrue(skillController.getAll().isEmpty());
    }
}