package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.SimCard;
import mftplus.model.repository.SimCardsRepository;

import java.util.List;

public class SimCardService implements Service<SimCard, Integer> {
    @Getter
    private static SimCardService service = new SimCardService();

    private SimCardService() {
    }

    public static SimCardService getInstance() {
        return service;
    }

    @Override
    public void save(SimCard simCard) throws Exception {
        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {
            simCardsRepository.save(simCard);
        }

    }

    @Override
    public void edit(SimCard simCard) throws Exception {
        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {
            simCardsRepository.edit(simCard);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {
            simCardsRepository.delete(id);
        }
    }

    @Override
    public List<SimCard> findAll() throws Exception {
        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {
            return simCardsRepository.findAll();
        }
    }


    @Override
    public SimCard findById(Integer id) throws Exception {
        try (SimCardsRepository simCardsRepository = new SimCardsRepository()) {
            return simCardsRepository.findById(id);
        }
    }
}
