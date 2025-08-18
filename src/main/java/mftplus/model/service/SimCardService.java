package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.SimCard;
import mftplus.model.repository.SimCardRepository;

import java.util.List;

public class SimCardService implements Service<SimCard, Integer> {
    @Getter
    private static SimCardService service = new SimCardService();

    private SimCardService() {
    }

    @Override
    public void save(SimCard simCard) throws Exception {
        try (SimCardRepository simCardRepository = new SimCardRepository()) {
            simCardRepository.save(simCard);
        }

    }

    @Override
    public void edit(SimCard simCard) throws Exception {
        try (SimCardRepository simCardRepository = new SimCardRepository()) {
            simCardRepository.edit(simCard);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (SimCardRepository simCardRepository = new SimCardRepository()) {
            simCardRepository.delete(id);
        }
    }

    @Override
    public List<SimCard> findAll() throws Exception {
        try (SimCardRepository simCardRepository = new SimCardRepository()) {
            return simCardRepository.findAll();
        }
    }


    @Override
    public SimCard findById(Integer id) throws Exception {
        try (SimCardRepository simCardRepository = new SimCardRepository()) {
            return simCardRepository.findById(id);
        }
    }
}
