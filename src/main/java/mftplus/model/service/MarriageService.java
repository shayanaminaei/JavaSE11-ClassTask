package mftplus.model.service;


import lombok.Getter;
import mftplus.model.entity.Marriage;
import mftplus.model.repository.MarriageRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MarriageService implements Service<Marriage, Integer> {

    @Getter
    private static MarriageService marriageService = new MarriageService();


    public MarriageService() {

    }

    @Override
    public void save(Marriage marriage) throws Exception {
        try(MarriageRepository marriageRepository = new MarriageRepository() ) {
            if(marriage.getMarriageDate().isBefore(LocalDate.of(2015,1,1))){
                marriageRepository.save(marriage);
            }else {
                throw new Exception("Marriage date is not acceptable!!");
            }
        }

    }

    @Override
    public void edit(Marriage marriage) throws Exception {
        try(MarriageRepository marriageRepository = new MarriageRepository() ) {
            marriageRepository.edit(marriage);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try(MarriageRepository marriageRepository = new MarriageRepository() ) {
            marriageRepository.delete(id);
        }

    }

    @Override
    public List<Marriage> findAll() throws Exception {
        try(MarriageRepository marriageRepository = new MarriageRepository() ) {
            return marriageRepository.findAll();
        }
    }

    @Override
    public Marriage findById(Integer id) throws Exception {
        try(MarriageRepository marriageRepository = new MarriageRepository() ) {
            return marriageRepository.findById(id);
        }
    }
}