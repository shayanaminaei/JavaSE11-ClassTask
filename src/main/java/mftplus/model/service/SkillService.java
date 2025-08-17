package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Skill;
import mftplus.model.repository.SkillRepository;

import java.util.Collections;
import java.util.List;

public class SkillService implements Service<Skill,Integer> {
    @Getter
    private static SkillService service = new SkillService();

    private SkillService() {
    }

    @Override
    public void save(Skill skill) throws Exception {
        try (SkillRepository skillRepository = new SkillRepository()) {
            skillRepository.save(skill);
        }
    }
    @Override
    public void edit(Skill skill) throws Exception {
        try (SkillRepository skillRepository = new SkillRepository()) {
            skillRepository.edit(skill);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (SkillRepository skillRepository = new SkillRepository()) {
            skillRepository.delete(id);
        }
    }

    @Override
    public List<Skill> findAll() throws Exception {
        try (SkillRepository skillRepository = new SkillRepository()) {
            return skillRepository.findAll();
        }
    }

    @Override
    public Skill findById(Integer id) throws Exception {
        try (SkillRepository skillRepository = new SkillRepository()) {
            return skillRepository.findById(id);
        }
    }
}
