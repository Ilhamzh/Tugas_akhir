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
    
 
    public boolean addMahasiswa(String nim, String nama, String password) {
        Mahasiswa mhs = new Mahasiswa(nim, nama, password);
        return userDAO.addMahasiswa(mhs);
    }
    
 
    public boolean deleteMahasiswa(String nim) {
        return userDAO.deleteMahasiswa(nim);
    }
    
   
    public void populateTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); 
        
        List<Mahasiswa> listMahasiswa = userDAO.getAllMahasiswa();
        
        for (Mahasiswa mhs : listMahasiswa) {
            tableModel.addRow(new Object[]{
                mhs.getNim(),
                mhs.getNama(),
                mhs.getPassword()
            });
        }
    }

}
