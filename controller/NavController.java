/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author sbtsp
 */
import interfaces.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Controller untuk navigasi antar form/menu.
 */
public class NavController {

    private JFrame currentView;   
    private String userRole;      
    private String nimUser;       
    private String namaUser;      

    /**
     * Konstruktor lengkap (dipakai dari Home setelah login Mahasiswa)
     */
    public NavController(JFrame currentView, String userRole, String nimUser, String namaUser) {
        this.currentView = currentView;
        this.userRole = userRole;
        this.nimUser = nimUser;
        this.namaUser = namaUser;
    }

    /**
     * Konstruktor sederhana (kalau tidak butuh NIM)
     */
    public NavController(JFrame currentView, String userRole) {
        this(currentView, userRole, null, null);
    }

    /**
     * Menutup view saat ini dan membuka view baru.
     */
    private void openView(JFrame targetView) {
        if (targetView == null) return;
        targetView.setLocationRelativeTo(null);
        targetView.setVisible(true);
        if (currentView != null) {
            currentView.dispose();
        }
    }

    /**
     * Dipanggil dari Home (dan form lain) ketika user klik menu di sidebar.
     * menuName diambil dari text pada JTextField (Home, Kartu Studi, dll).
     */
    public void navigate(String menuName) {
        try {
            if (userRole == null) {
                JOptionPane.showMessageDialog(currentView,
                        "User role belum di-set.",
                        "Error Navigasi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (userRole) {
                case "Mahasiswa":
                    handleMahasiswaNavigation(menuName);
                    break;
                case "Dosen":
                    handleDosenNavigation(menuName);
                    break;
                case "Admin":
                    handleAdminNavigation(menuName);
                    break;
                default:
                    JOptionPane.showMessageDialog(currentView,
                            "Role tidak dikenal: " + userRole,
                            "Error Navigasi",
                            JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView,
                    "Gagal memproses navigasi: " + e.getMessage(),
                    "Error Aplikasi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void handleMahasiswaNavigation(String menuName) {
        switch (menuName) {
            case "Home":
                openView(new Home(nimUser, namaUser));
                break;

            case "Registrasi Ulang":
                openView(new RegistrasiUlang(nimUser, namaUser));
                break;

            case "Registrasi Matkul":
                openView(new RegistrasiMatkul(nimUser, namaUser));
                break;

            case "Kartu Studi":
                openView(new KartuStudi(nimUser, namaUser));
                break;

            case "Jadwal Kuliah":
                openView(new JadwalKuliah(nimUser, namaUser));
                break;

            case "Hasil Studi":
                openView(new HasilStudi(nimUser, namaUser));
                break;

            case "Transkrip Nilai":
                openView(new TranskripNilai(nimUser, namaUser));
                break;

            case "Bimbingan":
                openView(new Bimbingan(nimUser, namaUser));
                break;

            case "LogOut":
                openView(new Login());
                break;

            default:
                JOptionPane.showMessageDialog(currentView,
                        "Menu tidak dikenal: " + menuName,
                        "Error Navigasi",
                        JOptionPane.WARNING_MESSAGE);
                break;
        }
    }


    private void handleDosenNavigation(String menuName) {
        switch (menuName) {
            case "Input Nilai":
                openView(new InputNilai());
                break;
            case "Jadwal Mengajar":
                openView(new JadwalMengajar());
                break;
            case "Bimbingan":
                openView(new Bimbingan(nimUser, namaUser));
                break;
            case "LogOut":
                openView(new Login());
                break;
            default:
                JOptionPane.showMessageDialog(currentView,
                        "Menu Dosen tidak dikenal: " + menuName,
                        "Error Navigasi",
                        JOptionPane.WARNING_MESSAGE);
        }
    }


    private void handleAdminNavigation(String menuName) {
        switch (menuName) {
            case "Home":
                openView(new Admin());
                break;
            case "Edit Mahasiswa":
                openView(new TambahMahasiswa());
                break;
            case "Edit Dosen":
                openView(new TambahDosen());
                break;
            case "Edit Matkul":
                openView(new TambahMatkul());
                break;
            case "LogOut":
                openView(new Login());
                break;
            default:
                JOptionPane.showMessageDialog(currentView,
                        "Menu Admin tidak dikenal: " + menuName,
                        "Error Navigasi",
                        JOptionPane.WARNING_MESSAGE);
        }
    }
}

