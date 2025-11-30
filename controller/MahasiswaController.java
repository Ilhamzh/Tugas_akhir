/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Mahasiswa;
import model.UserDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author sbtsp
 */
public class MahasiswaController {
    
    private UserDAO userDAO;

    public MahasiswaController() {
        this.userDAO = new UserDAO();
    }
    
    /**
     * Menambahkan Mahasiswa baru.
     */
    public boolean addMahasiswa(String nim, String nama, String password) {
        Mahasiswa mhs = new Mahasiswa(nim, nama, password);
        return userDAO.addMahasiswa(mhs);
    }
    
    /**
     * Menghapus Mahasiswa.
     */
    public boolean deleteMahasiswa(String nim) {
        return userDAO.deleteMahasiswa(nim);
    }
    
    /**
     * Mengisi data Mahasiswa ke JTable pada View.
     * @param tableModel Model tabel dari JTable di TambahMahasiswa.java
     */
    public void populateTable(DefaultTableModel tableModel) {
        // Hapus data lama di tabel
        tableModel.setRowCount(0); 
        
        List<Mahasiswa> listMahasiswa = userDAO.getAllMahasiswa();
        
        for (Mahasiswa mhs : listMahasiswa) {
            // Urutan kolom di JTable: "NIM", "Nama Mahasiswa", "Password"
            tableModel.addRow(new Object[]{
                mhs.getNim(),
                mhs.getNama(),
                mhs.getPassword() // Menampilkan password (Hanya untuk Admin)
            });
        }
    }
}