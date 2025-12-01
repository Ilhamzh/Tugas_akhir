/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author sbtsp
 */

import java.util.ArrayList;
import java.util.List;
import model.NilaiRecord;

public class NilaiService {

    // singleton
    private static NilaiService instance;

    // "database" sederhana di memory
    private final List<NilaiRecord> data = new ArrayList<>();

    private NilaiService() {
    }

    // ❗ PENTING: return type harus NilaiService, BUKAN Object
    public static NilaiService getInstance() {
        if (instance == null) {
            instance = new NilaiService();
        }
        return instance;
    }

    public void tambahNilai(NilaiRecord record) {
        // kalau sudah ada (nim + kodeMatkul), update saja
        for (NilaiRecord r : data) {
            if (r.getNim().equals(record.getNim()) &&
                r.getKodeMatkul().equals(record.getKodeMatkul())) {
                r.setNilai(record.getNilai());
                r.setAk(record.getAk());
                return;
            }
        }
        data.add(record);
    }

    public void updateNilai(NilaiRecord record) {
        tambahNilai(record); // logika sama dengan tambah
    }

    public void hapusNilai(String nim, String kodeMatkul) {
        data.removeIf(r ->
                r.getNim().equals(nim) &&
                r.getKodeMatkul().equals(kodeMatkul)
        );
    }

    // ❗ INI METHOD YANG DIPANGGIL DI HASILSTUDI
    public List<NilaiRecord> getNilaiByNim(String nim) {
        List<NilaiRecord> result = new ArrayList<>();
        for (NilaiRecord r : data) {
            if (r.getNim().equals(nim)) {
                result.add(r);
            }
        }
        return result;
    }
}
