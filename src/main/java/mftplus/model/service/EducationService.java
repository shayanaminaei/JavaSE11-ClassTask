package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Education;
import mftplus.model.repository.EducationRepository;

import java.util.List;

public class EducationService implements Service<Education, Integer> {

    @Getter
    private static EducationService service = new EducationService();
    private EducationService() {}

    @Override
    public void save(Education education) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.save(education);
        }
    }

    @Override
    public void edit(Education education) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.edit(education);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            educationRepository.delete(id);
        }
    }

    @Override
    public List<Education> findAll() throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findAll();
        }
    }

    @Override
    public Education findById(Integer id) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findById(id);
        }
    }

    public List<Education> findByUniversityAndGrade(String university, String educationGrade) throws Exception {
        try (EducationRepository educationRepository = new EducationRepository()) {
            return educationRepository.findByUniversityAndGrade(university, educationGrade);
        }
    }
}
