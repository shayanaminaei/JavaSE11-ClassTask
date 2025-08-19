package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Contact;
import mftplus.model.repository.ContactRepository;

import java.util.List;


public class ContactService implements Service<Contact, Integer> {
    @Getter
    private static ContactService service =  new ContactService();

    private ContactService() {
    }

    @Override
    public void save(Contact contact) throws Exception {
        try (ContactRepository contactRepository = new ContactRepository()) {
            contactRepository.save(contact);
        }
    }

    @Override
    public void edit(Contact contact) throws Exception {
        try (ContactRepository contactRepository = new ContactRepository()) {
            contactRepository.edit(contact);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ContactRepository contactRepository = new ContactRepository()) {
            contactRepository.delete(id);
        }
    }

    @Override
    public List<Contact> findAll() throws Exception {
        try (ContactRepository contactRepository = new ContactRepository()) {
            return contactRepository.findAll();
        }
    }

    @Override
    public Contact findById(Integer id) throws Exception {
        try (ContactRepository contactRepository = new ContactRepository()) {
            return contactRepository.findById(id);
        }
    }
}
