package mftplus.model.tools;

import mftplus.model.entity.Marriage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MarriageMapper {

    public static Marriage map(ResultSet rs) throws SQLException {
        return Marriage.builder()
                .marriageId(rs.getInt("id"))
                .personId(rs.getInt("person_id"))
                .name(rs.getString("name"))
                .family(rs.getString("family"))
                .marriageDate(rs.getObject("marriage_date", LocalDate.class))
                .isAlive(rs.getBoolean("is_alive"))
                .children(rs.getInt("children"))
                .build();
    }
}