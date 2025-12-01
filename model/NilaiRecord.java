/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sbtsp
 */
public class NilaiRecord {
    private String nim;
    private String kodeMatkul;
    private String namaMatkul;
    private int sks;
    private String nilai;
    private double ak;

    public NilaiRecord(String nim, String kodeMatkul, String namaMatkul,
                       int sks, String nilai, double ak) {
        this.nim = nim;
        this.kodeMatkul = kodeMatkul;
        this.namaMatkul = namaMatkul;
        this.sks = sks;
        this.nilai = nilai;
        this.ak = ak;
    }

    public String getNim() { return nim; }
    public String getKodeMatkul() { return kodeMatkul; }
    public String getNamaMatkul() { return namaMatkul; }
    public int getSks() { return sks; }
    public String getNilai() { return nilai; }
    public double getAk() { return ak; }

    public void setNilai(String nilai) { this.nilai = nilai; }
    public void setAk(double ak) { this.ak = ak; }
}


