import lombok.extern.log4j.Log4j;
import mftplus.controller.ContactController;
import mftplus.model.entity.Contact;
import mftplus.model.entity.enums.ContactTitle;
import mftplus.model.repository.ContactRepository;
import mftplus.model.service.ContactService;

@Log4j
public class ContactTest {
    public static void main(String[] args) throws Exception{
        log.info("Starting Contact Test");
        log.error("error saving");

        ContactController.getController().save(123, ContactTitle.Linkedin,"a b c","primary","first contact",true);
        ContactController.getController().edit(2,456,ContactTitle.Telegram,"d e f","Secondary","edited contact",false);
        ContactController.getController().delete(2);
        System.out.println(ContactController.getController().findAll());
        System.out.println(ContactController.getController().findById(1));


        Contact contact = Contact
                .builder()
                .personId(789)
                .title(ContactTitle.Email)
                .contactId("g h i")
                .contactType("primary")
                .description("second contact")
                .status(true)
                .build();

        ContactService.getService().save(contact);
        System.out.println(contact);

        try (ContactRepository contactRepository = new ContactRepository()) {
            contactRepository.save(contact);

            contactRepository.edit(contact);

            contactRepository.delete(2);

            contactRepository.findAll();

            System.out.println(contactRepository.findById(3));
        }
    }
}
