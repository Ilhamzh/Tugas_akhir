/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sbtsp
 */


import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String password;
    private boolean isActive = false; 
    private List<Matkul> krs = new ArrayList<>();     
    private List<Nilai> transkrip = new ArrayList<>(); 

    public Mahasiswa(String nim, String nama, String password) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
    }


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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Matkul> getKrs() {
        return krs;
    }

    public List<Nilai> getTranskrip() {
        return transkrip;
    }
}

