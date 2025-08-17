package mftplus.model.service;

import mftplus.model.entity.Marriage;
import mftplus.model.repository.MarriageRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MarriageService {

    private final MarriageRepository repo = new MarriageRepository();

    // CREATE
    public Marriage create(Marriage m) {
        validate(m, false);
        return repo.save(m);
    }

    // READ by id
    public Optional<Marriage> get(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id is invalid");
        }
        return repo.findById(id);
    }

    // READ by person
    public List<Marriage> listByPerson(int personId) {
        if (personId <= 0) {
            throw new IllegalArgumentException("personId is invalid");
        }
        return repo.findByPersonId(personId);
    }

    // UPDATE
    public Marriage edit(Marriage m) {
        if (m == null || m.getMarriageId() <= 0) {
            throw new IllegalArgumentException("marriageId is invalid for update");
        }
        validate(m, true);
        return repo.update(m);
    }

    // DELETE
    public boolean remove(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id is invalid");
        }
        return repo.deleteById(id);
    }

    // --- Helpers ---
    private void validate(Marriage m, boolean isUpdate) {
        if (m == null) throw new IllegalArgumentException("marriage is null");

        if (m.getPersonId() <= 0)
            throw new IllegalArgumentException("personId is invalid");

        if (m.getName() == null || m.getName().trim().isEmpty())
            throw new IllegalArgumentException("name is required");

        if (m.getFamily() == null || m.getFamily().trim().isEmpty())
            throw new IllegalArgumentException("family is required");

        if (m.getMarriageDate() == null)
            throw new IllegalArgumentException("marriageDate is required");

        if (m.getMarriageDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("marriageDate cannot be in the future");

        if (m.getChildren() < 0)
            throw new IllegalArgumentException("children cannot be negative");
    }
}