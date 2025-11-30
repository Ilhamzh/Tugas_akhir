package controller;


import interfaces.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class NavController {

    private JFrame currentView;     // Frame yang sedang aktif
    private String userRole;        // "Mahasiswa", "Dosen", "Admin"
    private String userId;          // NIM/NID/ID Admin
    private String namaUser;        // Nama pengguna

    /**
     * Konstruktor lengkap (dipakai dari Home setelah login)
     */
    public NavController(JFrame currentView, String userRole, String userId, String namaUser) {
        this.currentView = currentView;
        this.userRole = userRole;
        this.userId = userId;
        this.namaUser = namaUser;
    }

    /**
     * Konstruktor sederhana
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
                    "Gagal memproses navigasi: " + e.getMessage() + "\nPastikan semua frame memiliki konstruktor (String id, String nama) dan terimpor.",
                    "FATAL Error Navigasi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // ====================== MAHASISWA ======================

    private void handleMahasiswaNavigation(String menuName) {
        switch (menuName) {
            case "Home":
                openView(new Home(userId, namaUser));
                break;
            case "Registrasi Ulang":
                openView(new RegistrasiUlang(userId, namaUser)); 
                break;
            case "Registrasi Matkul":
                openView(new RegistrasiMatkul(userId, namaUser));
                break;
            case "Kartu Studi":
                openView(new KartuStudi(userId, namaUser)); 
                break;
            case "Jadwal Kuliah":
                openView(new JadwalKuliah(userId, namaUser)); 
                break;
            case "Hasil Studi":
                openView(new HasilStudi(userId, namaUser)); 
                break;
            case "Transkrip Nilai":
                openView(new TranskripNilai(userId, namaUser)); 
                break;
            case "Bimbingan":
                openView(new Bimbingan(userId, namaUser)); 
                break;
            case "LogOut":
                openView(new Login());
                break;
            default:
                JOptionPane.showMessageDialog(currentView, "Menu Mahasiswa tidak dikenal: " + menuName, "Error Navigasi", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    // ======================== DOSEN ========================

    private void handleDosenNavigation(String menuName) {
        switch (menuName) {
            case "Home":
                openView(new Dosen(userId, namaUser)); 
                break;
            case "Input Nilai": // Teks tombol dari form Dosen
                openView(new InputNilai(userId, namaUser)); 
                break;
            case "Jadwal Mengajar":
                openView(new JadwalMengajar(userId, namaUser)); 
                break;
            case "Bimbingan MHS": // Teks tombol dari form Dosen
                // ❗ PERBAIKAN: Mengganti BimbinganMhs dengan Bimbingan karena BimbinganMhs belum ada
                openView(new Bimbingan(userId, namaUser)); 
                break;
            case "Lupa Password":
                openView(new GantiPassword(userId, namaUser));
                break;
            case "LogOut":
                openView(new Login());
                break;
            default:
                JOptionPane.showMessageDialog(currentView, "Menu Dosen tidak dikenal: " + menuName, "Error Navigasi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ======================== ADMIN ========================

    private void handleAdminNavigation(String menuName) {
        switch (menuName) {
            case "Home":
                openView(new Admin(userId, namaUser)); 
                break;
            case "Edit Mahasiswa":
                openView(new TambahMahasiswa(userId, namaUser)); 
                break;
            case "Edit Dosen":
                openView(new TambahDosen(userId, namaUser)); 
                break;
            case "Edit Matkul":
                openView(new TambahMatkul(userId, namaUser)); 
                break;
            case "Lupa Password":
                openView(new GantiPasswordAdmin(userId, namaUser)); 
                break;
            case "LogOut":
                openView(new Login());
                break;
            default:
                JOptionPane.showMessageDialog(currentView, "Menu Admin tidak dikenal: " + menuName, "Error Navigasi", JOptionPane.ERROR_MESSAGE);
        }
    }
}