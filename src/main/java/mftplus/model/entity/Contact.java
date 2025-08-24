package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.ContactTitle;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter


public class Contact {
    private int id;
    private int personId;
    private ContactTitle title;
    private String contactId;
    private String contactType;
    private String description;
    private boolean status;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
