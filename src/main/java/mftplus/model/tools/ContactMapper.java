package mftplus.model.tools;

import mftplus.model.entity.Contact;
import mftplus.model.entity.enums.ContactTitle;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper {
    public Contact contactMapper(ResultSet resultSet) throws SQLException {
        return Contact
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .title(ContactTitle.valueOf(resultSet.getString("contact_title")))
                .contactId(resultSet.getString("contact_id"))
                .contactType(resultSet.getString("contact_type"))
                .description(resultSet.getString("description"))
                .status(resultSet.getBoolean("status"))
                .build();
    }
}
