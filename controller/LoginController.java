/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author sbtsp
 */
/*
 * Controller untuk proses login
 */

import interfaces.Admin;
import interfaces.Dosen;
import interfaces.Home;
import interfaces.Login;
import java.util.Map;
import javax.swing.JOptionPane;
import model.UserDAO;

public class LoginController {

    private final Login loginView;
    private final UserDAO userDAO;

    public LoginController(Login loginView) {
        this.loginView = loginView;
        this.userDAO = new UserDAO();
    }

    /**
     * Dipanggil dari tombol Login di form Login.
     */
    public void handleLogin(String id, String password) {
        try {
            Map<String, String> userData = userDAO.authenticate(id, password);

            if (userData != null) {
                String role = userData.get("role");
                String nama = userData.get("nama");

                JOptionPane.showMessageDialog(
                        loginView,
                        "Login berhasil sebagai " + role,
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Buka form sesuai role
                if ("Mahasiswa".equals(role)) {
                    // id = NIM
                    Home homeView = new Home(id, nama);
                    homeView.setLocationRelativeTo(null);
                    homeView.setVisible(true);
                } else if ("Dosen".equals(role)) {
                    Dosen dosenView = new Dosen();
                    dosenView.setLocationRelativeTo(null);
                    dosenView.setVisible(true);
                } else if ("Admin".equals(role)) {
                    Admin adminView = new Admin();
                    adminView.setLocationRelativeTo(null);
                    adminView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                            loginView,
                            "Role tidak dikenal: " + role,
                            "Error Login",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Tutup form login setelah berhasil
                loginView.dispose();

            } else {
                JOptionPane.showMessageDialog(
                        loginView,
                        "ID atau password salah.",
                        "Error Login",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    loginView,
                    "Terjadi kesalahan saat login: " + e.getMessage(),
                    "Error Login",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
