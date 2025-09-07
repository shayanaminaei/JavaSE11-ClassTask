package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Accounts;
import mftplus.model.repository.AccountsRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class AccountsService implements Service<Accounts, Integer> {
    @Getter
    private static AccountsService Service = new AccountsService();

    private AccountsService() {
    }

    @Override
    public void Save(Accounts Accounts) throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            if (Accounts.getRegisterDate().isBefore(LocalDate.of(2015, 1, 1))) {
                Repository.Save(Accounts);
            } else {
                throw new Exception("Register date is not acceptable!");
            }
        }
    }

    @Override
    public void Edit(Accounts Accounts) throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            Repository.Edit(Accounts);
        }
    }

    @Override
    public void Delete(Integer Id) throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            Repository.Delete(Id);
        }
    }

    @Override
    public List<Accounts> FindAll() throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            return Repository.FindAll();
        }
    }

    @Override
    public Accounts FindById(Integer Id) throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            return Repository.FindById(Id);
        }
    }

    public List<Accounts> FindByPersonId(Integer PersonId) throws Exception {
        try (AccountsRepository Repository = new AccountsRepository()) {
            return Repository.FindByPersonId(PersonId);
        }
    }

    @Override
    public void save(Accounts accounts) throws Exception {

    }

    @Override
    public void edit(Accounts accounts) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Accounts> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Accounts findById(Integer id) throws Exception {
        return null;
    }
}