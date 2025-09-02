package mftplus.model.tools;

import mftplus.model.entity.User;
import mftplus.model.service.PersonService;

import java.sql.ResultSet;

public class UserMapper {
    public User userMapper(ResultSet resultSet) throws Exception {
        return User
                .builder()
                .id(resultSet.getInt("id"))
                .person(PersonService.getService().findById(resultSet.getInt("person_id")))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .nickname(resultSet.getString("nick_name"))
                .locked(resultSet.getBoolean("locked"))
                .registerDate(resultSet.getDate("register_date").toLocalDate())
                .build();
    }
}
