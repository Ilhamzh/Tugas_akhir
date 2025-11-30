/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Dosen {
    public String nid;
    public String nama;
    public String kodeDosen;
    public String password; 

    public Dosen(String nid, String nama, String kodeDosen) {
        this.nid = nid;
        this.nama = nama;
        this.kodeDosen = kodeDosen;
        this.password = nid; 
    }

}
