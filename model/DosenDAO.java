/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sbtsp
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DosenDAO {

    private Connection conn;

    public DosenDAO() {
        conn = DBConnection.getConnection();
    }

    public List<Dosen> getAllDosen() {
        List<Dosen> list = new ArrayList<>();

        String sql = "SELECT nid, nama, kode_dosen FROM dosen";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dosen d = new Dosen(
                        rs.getString("nid"),
                        rs.getString("nama"),
                        rs.getString("kode_dosen")
                );
                list.add(d);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dosen: " + e.getMessage());
        }

        return list;
    }

    public void insertDosen(Dosen d) {
        String sql = "INSERT INTO dosen (nid, nama, kode_dosen, password) VALUES (?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.nid);
            ps.setString(2, d.nama);
            ps.setString(3, d.kodeDosen);
            ps.setString(4, d.jenisKelamin); 
            ps.setString(4, d.password);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah dosen: " + e.getMessage());
        }
    }

    public void deleteDosen(String nid) {
        String sql = "DELETE FROM dosen WHERE nid = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nid);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus dosen: " + e.getMessage());
        }
    }
}
