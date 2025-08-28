package mftplus.model.repository;

import mftplus.model.entity.Medical;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.MedicalMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MedicalRepository implements Repository <Medical, Integer> ,AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private MedicalMapper medicalMapper = new MedicalMapper();

    public MedicalRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Medical medical) throws Exception {
        medical.setId(ConnectionProvider.getProvider().getNextId("medical_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into MEDICALS (id, person_id, disease, medicine, doctor, visit_date, status) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, medical.getId());
        preparedStatement.setInt(2, medical.getPersonId());
        preparedStatement.setString(3, medical.getDisease());
        preparedStatement.setString(4, medical.getMedicine());
        preparedStatement.setString(5, medical.getDoctor().name());
        preparedStatement.setDate(6, Date.valueOf(medical.getVisitDate()));
        preparedStatement.setBoolean(7, medical.isStatus());
        preparedStatement.execute();

    }

    @Override
    public void edit(Medical medical) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update medicals set person_id=? , disease=? , doctor=?, visit_date=?, status=? where id=?"
        );
        preparedStatement.setString(1, medical.getDisease());
        preparedStatement.setString(2, medical.getMedicine());
        preparedStatement.setString(3, medical.getDoctor().name());
        preparedStatement.setDate(4, Date.valueOf(medical.getVisitDate()));
        preparedStatement.setBoolean(5, medical.isStatus());
        preparedStatement.setInt(6, medical.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from medicals where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Medical> findAll() throws Exception {
        List<Medical> medicals = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from medicals");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Medical medical = new Medical();
            medicals.add(medical);
        }
        return medicals;
    }

    @Override
    public Medical findById(Integer id) throws Exception {
        Medical medical = null;

        preparedStatement = connection.prepareStatement("select * from medicals where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            medical = medicalMapper.medicalMapper(resultSet);
        }
        return medical;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
