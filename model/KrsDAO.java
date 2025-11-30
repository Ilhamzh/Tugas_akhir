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
import java.util.List;


public class KrsDAO {

  
    public List<Object[]> getKrsByNim(String nim) {
        List<Object[]> result = new ArrayList<>();

        String sql =
                "SELECT m.kode_matkul, m.nama_matkul, d.nama AS nama_dosen, " +
                "       m.hari, m.jam, m.sks " +
                "FROM KRS k " +
                "JOIN Matkul m ON k.kode_matkul = m.kode_matkul " +
                "JOIN Dosen d ON m.nid_dosen = d.nid " +
                "WHERE k.nim = ? " +
                "ORDER BY m.kode_matkul";

        Connection conn = DBConnection.getConnection();
        if (conn == null) return result;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String kode      = rs.getString("kode_matkul");
                    String namaMatkul= rs.getString("nama_matkul");
                    String namaDosen = rs.getString("nama_dosen");
                    String hari      = rs.getString("hari");
                    String jam       = rs.getString("jam");
                    int sks          = rs.getInt("sks");

               
                    result.add(new Object[]{ kode, namaMatkul, namaDosen, hari, jam, "-", sks });
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data KRS: " + e.getMessage());
        } finally {
            DBConnection.closeConnection(conn);
        }

        return result;
    }
}

