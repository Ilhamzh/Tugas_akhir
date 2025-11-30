/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class Matkul {
}
class Nilai {
}

public class Mahasiswa {
    private String nim;
    private String nama;
    private String password;
    // isActive merefleksikan status_registrasi di DB
    private boolean isActive = false; 
    
    // Tipe List harus diimpor atau didefinisikan
    private List<Matkul> krs = new ArrayList<>();       
    private List<Nilai> transkrip = new ArrayList<>(); 

    public Mahasiswa(String nim, String nama, String password) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
    }

    // Constructor lengkap (untuk memuat data dari DB)
    public Mahasiswa(String nim, String nama, String password, boolean isActive) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
        this.isActive = isActive;
    }

    // --- Getter & Setter ---
    
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Mengganti isActive() menjadi getStatusRegistrasi() agar lebih jelas
    public boolean getStatusRegistrasi() {
        return isActive;
    }

    public void setStatusRegistrasi(boolean active) {
        this.isActive = active;
    }
    
    public List<Matkul> getKrs() {
        return krs;
    }

    public List<Nilai> getTranskrip() {
        return transkrip;
    }
}