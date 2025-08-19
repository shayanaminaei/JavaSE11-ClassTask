package mftplus.model.tools;

import mftplus.model.entity.Medical;
import mftplus.model.entity.enums.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalMapper {
    public Medical medicalMapper(ResultSet resultSet) throws SQLException {
        return Medical
                .builder()
                .id(resultSet.getInt("id"))
                .personId(resultSet.getInt("person_id"))
                .disease(resultSet.getString("disease"))
                .medicine(resultSet.getString("medicine"))
                .doctor(Doctor.valueOf(resultSet.getString("doctor")))
                .visitDate(resultSet.getDate("visit_date").toLocalDate())
                .status(resultSet.getBoolean("status"))
                .build();
    }
}
