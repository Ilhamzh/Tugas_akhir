/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sbtsp
 */

// model/BimbinganDAO.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BimbinganDAO {

    private Connection conn;

    public BimbinganDAO() {
        conn = DBConnection.getConnection();
    }

    // ======================= INSERT PESAN =========================
    public void kirimPesan(String nim, String nid, String pesan) {
        if (conn == null) {
            JOptionPane.showMessageDialog(null,
                    "Koneksi database null!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO bimbingan (nim, nid, pesan) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            ps.setString(2, nid);     // ✅ sesuai kolom: nid
            ps.setString(3, pesan);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Gagal menyimpan pesan bimbingan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ======================= AMBIL PESAN DOSEN =========================
    public List<Object[]> getPesanByDosen(String nid) {
        List<Object[]> list = new ArrayList<>();
        if (conn == null) return list;

        String sql = "SELECT nim, pesan, waktu FROM bimbingan WHERE nid = ? ORDER BY waktu DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nid);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nim   = rs.getString("nim");
                    String pesan = rs.getString("pesan");
                    String waktu = rs.getString("waktu");

                    list.add(new Object[]{ nim, pesan, waktu });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Gagal mengambil pesan bimbingan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }
}
