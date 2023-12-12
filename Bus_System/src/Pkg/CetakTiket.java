package Pkg;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CetakTiket {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private List<Penumpang> listPenumpang;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CetakTiket window = new CetakTiket();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CetakTiket() {
        listPenumpang = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 366, 239);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 135, 124);
        frame.getContentPane().add(panel);

        // Path ke gambar
        String imagePath = "C:\\Users\\ASUS\\Downloads\\R (1).jpg";

        // Menggunakan ImageIcon untuk menampilkan gambar pada JLabel
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Mendapatkan lebar dan tinggi panel
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        // Menyesuaikan ukuran gambar sesuai dengan ukuran panel
        Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // Membuat label dan menetapkan gambar ke dalamnya
        JLabel imageLabel = new JLabel(scaledImageIcon);
        panel.add(imageLabel);

        JLabel lblNewLabel = new JLabel("Nama:");
        lblNewLabel.setBounds(155, 10, 50, 18);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblBus = new JLabel("Bus:");
        lblBus.setBounds(155, 66, 50, 18);
        frame.getContentPane().add(lblBus);

        JLabel lblTujuan = new JLabel("Tujuan:");
        lblTujuan.setBounds(155, 94, 50, 18);
        frame.getContentPane().add(lblTujuan);

        JLabel lblKursi = new JLabel("Kursi:");
        lblKursi.setBounds(155, 122, 50, 18);
        frame.getContentPane().add(lblKursi);

        JLabel lblNik = new JLabel("NIK:");
        lblNik.setBounds(155, 38, 50, 18);
        frame.getContentPane().add(lblNik);

        textField = new JTextField();
        textField.setBounds(202, 10, 140, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(202, 38, 140, 19);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(202, 66, 140, 19);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(202, 98, 140, 19);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(202, 127, 140, 19);
        frame.getContentPane().add(textField_4);

        JButton btnNewButton = new JButton("Cetak");
        btnNewButton.setBounds(135, 171, 85, 21);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            // Mendapatkan nilai dari input
            String nama = textField.getText();
            String nik = textField_1.getText();
            String namaBus = textField_2.getText();
            String tujuan = textField_3.getText();
            String kursi = textField_4.getText();

            // Melakukan validasi
            if (isValidData(nama, nik, namaBus, tujuan, kursi)) {
                // Menambahkan penumpang ke dalam list
                Penumpang penumpang = new Penumpang(nama, nik, namaBus, tujuan, kursi);
                listPenumpang.add(penumpang);

                // Menampilkan informasi penumpang
                System.out.println("Data Penumpang:");
                for (Penumpang p : listPenumpang) {
                    System.out.println("Nama: " + p.getNama());
                    System.out.println("NIK: " + p.getNik());
                    System.out.println("Bus: " + p.getNamaBus());
                    System.out.println("Tujuan: " + p.getTujuan());
                    System.out.println("Kursi: " + p.getKursi());
                    System.out.println("----------------------");
                }

                // Meminta pengguna memilih direktori penyimpanan
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Pilih Lokasi Penyimpanan");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();

                    // Membuat file dengan nama "CetakTiket.png" di direktori yang dipilih pengguna
                    File outputFile = new File(selectedDirectory, "CetakTiket.png");

                    // Menyimpan gambar
                    saveImage(scaledImage, outputFile);
                } else {
                    System.out.println("Penyimpanan dibatalkan.");
                }

                // Reset input fields
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
            }
        });
    }

    private void setTextFieldValues(Penumpang penumpang) {
        textField.setText(penumpang.getNama());
        textField_1.setText(penumpang.getNik());
        textField_2.setText(penumpang.getNamaBus());
        textField_3.setText(penumpang.getTujuan());
        textField_4.setText(penumpang.getKursi());
    }

    private boolean isValidData(String nama, String nik, String namaBus, String tujuan, String kursi) {
        return !nama.isEmpty() && !nik.isEmpty() && !namaBus.isEmpty() && !tujuan.isEmpty() && !kursi.isEmpty();
    }

    private void saveImage(Image image, File outputFile) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(SwingFXUtils.toFXImage(image, null), null), "png", outputFile);
            System.out.println("Gambar berhasil disimpan di: " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class Penumpang {
        private String nama;
        private String nik;
        private String namaBus;
        private String tujuan;
        private String kursi;

        public Penumpang(String nama, String nik, String namaBus, String tujuan, String kursi) {
            this.nama = nama;
            this.nik = nik;
            this.namaBus = namaBus;
            this.tujuan = tujuan;
            this.kursi = kursi;
        }

        public String getNama() {
            return nama;
        }

        public String getNik() {
            return nik;
        }

        public String getNamaBus() {
            return namaBus;
        }

        public String getTujuan() {
            return tujuan;
        }

        public String getKursi() {
            return kursi;
        }

        public void showFrame() {
            frame.setVisible(true);
        }
    }
}
