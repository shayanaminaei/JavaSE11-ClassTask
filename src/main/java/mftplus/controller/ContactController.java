package mftplus.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.Contact;
import mftplus.model.entity.enums.ContactTitle;
import mftplus.model.service.ContactService;

import java.util.List;

@Log4j
public class ContactController {
    @Getter
    private static ContactController controller = new ContactController();

    private ContactController() {
    }

    public void save(int personId, ContactTitle title, String contactId, String contactType, String description, boolean status) throws Exception {
        try {
            Contact contact =
                    Contact
                            .builder()
                            .personId(personId)
                            .title(title)
                            .contactId(contactId)
                            .contactType(contactType)
                            .description(description)
                            .status(status)
                            .build();
            ContactService.getService().save(contact);
            log.info("Contact Saved Successful");
        } catch (Exception e) {
            log.error("Contact Saved failed" + e.getMessage());
        }
    }

    public void edit(int id, int personId, ContactTitle title, String contactId, String contactType, String description, boolean status) throws Exception {
        try {
            Contact contact =
                    Contact
                            .builder()
                            .id(id)
                            .personId(personId)
                            .title(title)
                            .contactId(contactId)
                            .contactType(contactType)
                            .description(description)
                            .status(status)
                            .build();
            ContactService.getService().edit(contact);
            log.info("Contact Edited Successful");
        } catch (Exception e) {
            log.error("Contact Edit Failed" + e.getMessage());
        }
    }

    public void delete(Integer id) throws Exception {
        try {
            ContactService.getService().delete(id);
            log.info("Contact Deleted Successful");
        } catch (Exception e) {
            log.error("Contact Deleted Failed" + e.getMessage());
        }
    }

    public List<Contact> findAll() throws Exception {
        try {
            List<Contact> contactList = ContactService.getService().findAll();
            log.info("Contacts FindAll");
            return contactList;
        } catch (Exception e) {
            log.error("Contacts FindAll Failed" + e.getMessage());
            return null;
        }
    }

    public  Contact findById(Integer id) throws Exception {
        try {
            Contact contact = ContactService.getService().findById(id);
            log.info("Contact FindById : " + id);
            return contact;
        } catch (Exception e) {
            log.error("Contact FindById " + id + " Failed" + e.getMessage());
            return null;
        }
    }
}
