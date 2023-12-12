package Pkg;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {
    public JFrame frmBusSystemBy;
    public JTextField textField_1;

    public JFrame getFrame() {
        return frmBusSystemBy;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register window = new Register();
                    window.frmBusSystemBy.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Register() {
        initialize();
    }

    public void initialize() {
        JTextField textField = new JTextField();
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 464, 479);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 450, 300);
        frmBusSystemBy.getContentPane().add(layeredPane);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(2, 2, 100));
        panel1.setBounds(0, 0, 450, 442);
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);
        panel1.setLayout(null);

        String imagePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\bg\\HTC-Edge-Launcher-square-l (1).jpg";

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 5));
        lblNewLabel.setBounds(0, 0, 450, 442);

        try {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ImageIO.read(new File(imagePath)))
                    .getImage().getScaledInstance(panel1.getWidth(), panel1.getHeight(), Image.SCALE_SMOOTH));
            lblNewLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel1.add(lblNewLabel);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(25, 25, 25, 128));
        panel2.setBounds(80, 30, 294, 342);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);
        panel2.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Register");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(10, 11, 274, 34);
        panel2.add(lblNewLabel_1);

        textField.setFont(new Font("Dialog", Font.BOLD, 16));
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(25, 25, 25, 128));
        textField.setBounds(10, 99, 274, 27);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setBorder(BorderFactory.createEmptyBorder()); // Add this line
        panel2.add(textField);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 13));
        lblEmail.setBounds(10, 68, 82, 21);
        panel2.add(lblEmail);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Dialog", Font.BOLD, 16));
        textField_1.setForeground(new Color(255, 255, 255));
        textField_1.setBackground(new Color(25, 25, 25, 128));
        textField_1.setBounds(10, 167, 274, 27);
        textField_1.setBorder(BorderFactory.createEmptyBorder());
        textField_1.setBorder(BorderFactory.createEmptyBorder()); // Add this line
        panel2.add(textField_1);

        JLabel label = new JLabel("Username");
        label.setFont(new Font("Dialog", Font.BOLD, 13));
        label.setBounds(10, 136, 82, 21);
        label.setForeground(new Color(255, 255, 255));
        panel2.add(label);

        JLabel label_1 = new JLabel("Password");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dialog", Font.BOLD, 13));
        label_1.setBounds(10, 204, 82, 21);
        panel2.add(label_1);

        JPasswordField textField_2 = new JPasswordField();
        textField_2.setForeground(Color.WHITE);
        textField_2.setFont(new Font("Dialog", Font.BOLD, 16));
        textField_2.setBackground(new Color(25, 25, 25, 128));
        textField_2.setBounds(10, 235, 274, 27);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        textField_2.setBorder(BorderFactory.createEmptyBorder()); // Add this line
        panel2.add(textField_2);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                if (email.isEmpty() || textField_1.getText().isEmpty() || String.valueOf(textField_2.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Email, username, dan password harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Email tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Logika registrasi tanpa OTP
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Registrasi berhasil!");
                }
            }
        });


        btnRegister.setBackground(new Color(0, 128, 0));
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFont(new Font("Dialog", Font.BOLD, 15));
        btnRegister.setBounds(86, 288, 115, 44);
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setFocusable(false);
        btnRegister.setBorder(BorderFactory.createEmptyBorder()); // Add this line
        panel2.add(btnRegister);

        JLabel lblNewLabel_2 = new JLabel("Sudah ada akun? Login");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(90, 263, 115, 27);
        lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                disposeAndOpenLogin(frmBusSystemBy);
            }
        });

        panel2.add(lblNewLabel_2);

        frmBusSystemBy.setVisible(true);
    }

    private static void disposeAndOpenLogin(JFrame frameToDispose) {
        frameToDispose.dispose();
        Login login = new Login();	
        login.getFrame().setVisible(true);
    }
}

