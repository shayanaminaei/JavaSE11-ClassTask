package mftplus.model.repository;

import mftplus.model.entity.Marriage;
import mftplus.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarriageRepository {

    // CREATE
    public Marriage save(Marriage m) {
        final String sql = "INSERT INTO marriages(person_id, name, family, marriage_date, is_alive, children) " +
                "VALUES (?,?,?,?,?,?) RETURNING id";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, m.getPersonId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getFamily());
            ps.setDate(4, Date.valueOf(m.getMarriageDate()));
            ps.setBoolean(5, m.isAlive());
            ps.setInt(6, m.getChildren());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m.setMarriageId(rs.getInt(1));
                }
            }
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("save marriage failed", e);
        }
    }

    // READ by id
    public Optional<Marriage> findById(int id) {
        final String sql = "SELECT id, person_id, name, family, marriage_date, is_alive, children " +
                "FROM marriages WHERE id = ?";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("findById failed", e);
        }
    }

    // READ by person
    public List<Marriage> findByPersonId(int personId) {
        final String sql = "SELECT id, person_id, name, family, marriage_date, is_alive, children " +
                "FROM marriages WHERE person_id = ? ORDER BY id";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, personId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Marriage> out = new ArrayList<>();
                while (rs.next()) out.add(map(rs));
                return out;
            }
        } catch (SQLException e) {
            throw new RuntimeException("findByPersonId failed", e);
        }
    }

    // READ all (اختیاری ولی مفید)
    public List<Marriage> findAll() {
        final String sql = "SELECT id, person_id, name, family, marriage_date, is_alive, children " +
                "FROM marriages ORDER BY id";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Marriage> out = new ArrayList<>();
            while (rs.next()) out.add(map(rs));
            return out;
        } catch (SQLException e) {
            throw new RuntimeException("findAll failed", e);
        }
    }

    // UPDATE
    public Marriage update(Marriage m) {
        if (m.getMarriageId() == 0) throw new IllegalArgumentException("marriageId is 0 (missing)");
        final String sql = "UPDATE marriages " +
                "SET person_id = ?, name = ?, family = ?, marriage_date = ?, is_alive = ?, children = ? " +
                "WHERE id = ?";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, m.getPersonId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getFamily());
            ps.setDate(4, Date.valueOf(m.getMarriageDate()));
            ps.setBoolean(5, m.isAlive());
            ps.setInt(6, m.getChildren());
            ps.setInt(7, m.getMarriageId());

            ps.executeUpdate();
            return m;
        } catch (SQLException e) {
            throw new RuntimeException("update failed", e);
        }
    }

    // DELETE
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM marriages WHERE id = ?";
        try (Connection c = ConnectionProvider.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("deleteById failed", e);
        }
    }

    // مپ‌کردن ردیف دیتابیس به آبجکت Marriage
    private Marriage map(ResultSet rs) throws SQLException {
        return Marriage.builder()
                .marriageId(rs.getInt("id"))
                .personId(rs.getInt("person_id"))
                .name(rs.getString("name"))
                .family(rs.getString("family"))
                .marriageDate(rs.getDate("marriage_date").toLocalDate())
                .isAlive(rs.getBoolean("is_alive"))
                .children(rs.getInt("children"))
                .build();
    }
}