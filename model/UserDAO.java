/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sbtsp
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

 
    public Map<String, String> authenticate(String id, String password) {
        Map<String, String> userData = null;
        String role = null;
        String nama = null;
        String sql = "SELECT role FROM User WHERE id = ? AND password = ?";

        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                    userData = new HashMap<>();
                    userData.put("role", role);

                    // Ambil nama berdasarkan role
                    if ("Mahasiswa".equals(role)) {
                        nama = getMahasiswaName(id, conn);
                    } else if ("Dosen".equals(role)) {
                        nama = getDosenName(id, conn);
                    } else if ("Admin".equals(role)) {
                        nama = "Admin Sistem";
                    }
                    userData.put("nama", nama);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat autentikasi: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }
        return userData;
    }


 
    public boolean addMahasiswa(Mahasiswa mhs) {
        String sqlMahasiswa = "INSERT INTO Mahasiswa (nim, nama, password) VALUES (?, ?, ?)";
        String sqlUser      = "INSERT INTO User (id, password, role) VALUES (?, ?, 'Mahasiswa')";

        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtMhs = conn.prepareStatement(sqlMahasiswa);
                 PreparedStatement stmtUser = conn.prepareStatement(sqlUser)) {

                // Insert ke Mahasiswa
                stmtMhs.setString(1, mhs.getNim());
                stmtMhs.setString(2, mhs.getNama());
                stmtMhs.setString(3, mhs.getPassword());
                stmtMhs.executeUpdate();

                // Insert ke User
                stmtUser.setString(1, mhs.getNim());
                stmtUser.setString(2, mhs.getPassword());
                stmtUser.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal menambah mahasiswa: " + e.getMessage());
            try { conn.rollback(); } catch (SQLException ignored) {}
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
            DBConnection.closeConnection(conn);
        }
        return false;
    }

  
    public boolean deleteMahasiswa(String nim) {
        String sqlDeleteUser = "DELETE FROM User WHERE id = ?";
        String sqlDeleteMhs  = "DELETE FROM Mahasiswa WHERE nim = ?";

        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtUser = conn.prepareStatement(sqlDeleteUser);
                 PreparedStatement stmtMhs = conn.prepareStatement(sqlDeleteMhs)) {

                // Hapus dari User
                stmtUser.setString(1, nim);
                stmtUser.executeUpdate();

                // Hapus dari Mahasiswa
                stmtMhs.setString(1, nim);
                int affected = stmtMhs.executeUpdate();

                conn.commit();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Gagal menghapus mahasiswa: " + e.getMessage());
            try { conn.rollback(); } catch (SQLException ignored) {}
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
            DBConnection.closeConnection(conn);
        }
        return false;
    }

 
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT nim, nama, password FROM Mahasiswa ORDER BY nim";

        Connection conn = DBConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nim = rs.getString("nim");
                String nama = rs.getString("nama");
                String password = rs.getString("password");
                list.add(new Mahasiswa(nim, nama, password));
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data mahasiswa: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }
        return list;
    }

    private String getMahasiswaName(String nim, Connection conn) throws SQLException {
        String sql = "SELECT nama FROM Mahasiswa WHERE nim = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("nama") : "Mahasiswa Tidak Ditemukan";
            }
        }
    }

    // Ambil nama dosen dari tabel Dosen
    private String getDosenName(String nid, Connection conn) throws SQLException {
        String sql = "SELECT nama FROM Dosen WHERE nid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nid);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("nama") : "Dosen Tidak Ditemukan";
            }
        }
    }
}

