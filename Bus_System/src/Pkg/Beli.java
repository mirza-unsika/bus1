package Pkg;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Beli {
    private JFrame frmBusSystemBy;
    private ImageIcon imageIcon;
    private JTable table;

    private static final double TRANSJAKARTA_PRICE = 5000;
    private static final double KOPAJA_PRICE = 4500;
    private static final double METROMINI_PRICE = 7000;
    private static final double SINARJAYA_PRICE = 6000;
    private static final double PAHALAKENCANA_PRICE = 4000;
    private JTextField searchField_1;
    private JTextField textField;
    private int jumlahPembelian = 0;

    private boolean ticketPrinted = false;  // Track if the ticket has been printed
    private List<PembelianTiket> daftarPembelian = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Beli window = new Beli();
                    window.frmBusSystemBy.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showFrame() {
        frmBusSystemBy.setVisible(true);
    }

    public Beli() {
        initialize();
    }

    public JFrame getFrame() {
        return frmBusSystemBy;
    }

    private void initialize() {
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 860, 454);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\ASUS\\Downloads\\R.jpg";
        imageIcon = new ImageIcon(imagePath);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(new Color(0, 0, 0));
        frmBusSystemBy.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(294, 0, 552, 318);
        panel_1.setBackground(new Color(255, 255, 255, 30));

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(255, 255, 255, 255));
        btnBack.setBounds(10, 380, 83, 37);
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.getFrame().setVisible(true);
                frmBusSystemBy.dispose();
            }
        });

        JButton btnCetakTiket = new JButton("Cetak Tiket");
        btnCetakTiket.setBounds(731, 381, 105, 37);
        btnCetakTiket.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCetakTiket.setBackground(new Color(255, 255, 255, 255));
        btnCetakTiket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jumlahPembelian > 0 && !ticketPrinted) {
                    // frmBusSystemBy.setVisible(false); // Jangan lakukan ini

                    // Tampilkan frame CetakTiket
                    CetakTiket cetakTiket = new CetakTiket();
                    cetakTiket.getFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Belum ada pembelian tiket atau tiket sudah dicetak.");
                }
            }
        });
        
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 46, 468, 222);
        panel_1.add(scrollPane);
        scrollPane.setBackground(new Color(255, 255, 255, 255));

        // Menggunakan kelas model tabel kustom
        table = new JTable();
        table.setModel(new NonEditableTableModel(
                new Object[][]{{null, null, null, null, null}},
                new String[]{"Nama", "NIK", "Pilih Bus", "Tujuan", "Kursi"}
        ));
        scrollPane.setViewportView(table);
        panel.setLayout(null);
        panel.add(btnBack);
        panel.add(btnCetakTiket);
        panel.add(panel_1);
        searchField_1 = new JTextField();
        searchField_1.setBounds(96, 47, 156, 23);
        panel.add(searchField_1);
        searchField_1.setColumns(10);
        searchField_1.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblNewLabel = new JLabel("Nama");
        lblNewLabel.setBounds(10, 47, 83, 19);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Transjakarta", "Kopaja", "Metromini", "Sinar Jaya", "Pahala Kencana"});
        comboBox.setBounds(96, 155, 156, 23);
        panel.add(comboBox);
        comboBox.setBackground(new Color(255, 255, 255, 255));

        JLabel lblPilihBus = new JLabel("Pilih bus");
        lblPilihBus.setBounds(10, 155, 83, 19);
        panel.add(lblPilihBus);
        lblPilihBus.setForeground(new Color(255, 255, 255));
        lblPilihBus.setFont(new Font("Tahoma", Font.BOLD, 15));

        JComboBox<String> comboBox_1 = new JComboBox<>(new String[]{"Blok M-Kelapa Gading", "Jakarta-Depok", "Bekasi-Tangerang", "Bandung-Solo"});
        comboBox_1.setBounds(96, 216, 141, 25);
        panel.add(comboBox_1);
        comboBox_1.setBackground(new Color(255, 255, 255, 255));

        JLabel lblKursi = new JLabel("Kursi");
        lblKursi.setBounds(10, 275, 83, 19);
        panel.add(lblKursi);
        lblKursi.setForeground(new Color(255, 255, 255));
        lblKursi.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblTujuan = new JLabel("Tujuan");
        lblTujuan.setBounds(10, 217, 83, 19);
        panel.add(lblTujuan);
        lblTujuan.setForeground(new Color(255, 255, 255));
        lblTujuan.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btnNewButton = new JButton("Beli");
        btnNewButton.setBounds(344, 380, 129, 37);
        panel.add(btnNewButton);
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(new Color(255, 255, 255,255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel lblNik = new JLabel("NIK");
        lblNik.setForeground(Color.WHITE);
        lblNik.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNik.setBounds(10,102, 83, 19);
        panel.add(lblNik);

        JComboBox<String> comboBox_2 = new JComboBox<>(new String[]{"A11","A12","A13","A14", "A15"});
        comboBox_2.setBounds(96, 276, 141, 21);
        panel.add(comboBox_2);
        comboBox_2.setBackground(new Color(255, 255, 255, 255));
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setColumns(10);
        textField.setBounds(96, 104, 156, 23);
        panel.add(textField);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan nilai dari input
                String nama = searchField_1.getText();
                String nik = textField.getText();
                String namaBus = (String) comboBox.getSelectedItem();
                String tujuan = (String) comboBox_1.getSelectedItem();
                String kursi = (String) comboBox_2.getSelectedItem();

                // Melakukan validasi
                if (nik.length() !=16) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus terdiri dari 16 digit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return; // Jangan melanjutkan proses jika NIK tidak valid
                } else if (nama.isEmpty() && nik.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama dan NIK harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (nik.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!isValidName(nama)) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama harus berisi huruf saja.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!isValidNIK(nik)) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus berisi angka saja.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (namaBus.isEmpty() || tujuan.isEmpty() || kursi.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Mohon isi semua kolom");
                } else {
                    // Menampilkan konfirmasi pembelian dengan harga transparan
                    double hargaTiket = getHargaTiket(namaBus);
                    String konfirmasiBeli = "Anda akan membeli tiket:\n"
                            + "Nama Penumpang: " + nama + "\n"
                            + "NIK: " + nik + "\n"
                            + "Nama Bus: " + namaBus + "\n"
                            + "Tujuan: " + tujuan + "\n"
                            + "Kursi: " + kursi + "\n"
                            + "Harga Tiket: " + hargaTiket + "\n\n"
                            + "Silakan masukkan uang Anda.";

                    String inputUangStr = JOptionPane.showInputDialog(frmBusSystemBy, konfirmasiBeli);

                    try {
                        // Mengubah input uang menjadi nilai numerik
                        double inputUang = Double.parseDouble(inputUangStr);

                        // Memeriksa apakah uang yang dimasukkan cukup
                        if (inputUang >= hargaTiket) {
                            // Lakukan aksi jika pembelian berhasil
                            String infoBeli = "Pembelian berhasil!\n"
                                    + "Nama Penumpang: " + nama + "\n"
                                    + "NIK: " + nik + "\n"
                                    + "Nama Bus: " + namaBus + "\n"
                                    + "Tujuan: " + tujuan + "\n"
                                    + "Kursi: " + kursi + "\n"
                                    + "Harga Tiket: " + hargaTiket + "\n"
                                    + "Uang Anda: " + inputUang + "\n"
                                    + "Kembalian: " + (inputUang - hargaTiket);
                            JOptionPane.showMessageDialog(frmBusSystemBy, infoBeli);

                            // Menambahkan informasi pembelian ke dalam tabel
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.addRow(new Object[]{nama, nik, namaBus, tujuan, kursi});

                            // Menambah jumlah pembelian
                            jumlahPembelian++;

                            // Menandai tiket belum dicetak
                            ticketPrinted = false;
                        } else {
                            // Lakukan aksi jika uang tidak cukup
                            JOptionPane.showMessageDialog(frmBusSystemBy, "Uang tidak cukup. Pembelian gagal.");
                        }
                    } catch (NumberFormatException ex) {
                        // Handle kesalahan jika input uang tidak valid
                        JOptionPane.showMessageDialog(frmBusSystemBy, "Masukkan jumlah uang yang valid.");
                    }
                    
                }
                
            }
        });

        comboBox_1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comboBox_1.hidePopup();
            }
        });

        comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comboBox.hidePopup();
            }
        });
    }

    private double getHargaTiket(String namaBus) {
        switch (namaBus) {
            case "Transjakarta":
                return TRANSJAKARTA_PRICE;
            case "Kopaja":
                return KOPAJA_PRICE;
            case "Metromini":
                return METROMINI_PRICE;
            case "Sinar Jaya":
                return SINARJAYA_PRICE;
            case "Pahala Kencana":
                return PAHALAKENCANA_PRICE;
            default:
                return 0.0;
        }
    }

    private void saveTicketToText() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Tiket sebagai Teks");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        int userSelection = fileChooser.showSaveDialog(frmBusSystemBy);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                // Mendapatkan teks dari tabel untuk tiket
                StringBuilder ticketText = new StringBuilder();

                // Loop melalui daftar pembelian
                for (PembelianTiket pembelian : daftarPembelian) {
                    ticketText.append("Nama Penumpang: ").append(pembelian.getNama()).append("\n");
                    ticketText.append("NIK: ").append(pembelian.getNik()).append("\n");
                    ticketText.append("Nama Bus: ").append(pembelian.getNamaBus()).append("\n");
                    ticketText.append("Tujuan: ").append(pembelian.getTujuan()).append("\n");
                    ticketText.append("Kursi: ").append(pembelian.getKursi()).append("\n");
                    ticketText.append("Harga Tiket: ").append(pembelian.getHargaTiket()).append("\n\n");
                }

                // Menyimpan teks tiket ke dalam file
                writer.write(ticketText.toString());
                JOptionPane.showMessageDialog(frmBusSystemBy, "Tiket berhasil disimpan sebagai teks.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                // Set ticketPrinted to true
                ticketPrinted = true;

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frmBusSystemBy, "Gagal menyimpan tiket sebagai teks.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            // Handle the case where the user cancels the operation
            ticketPrinted = false;
            JOptionPane.showMessageDialog(frmBusSystemBy, "Pencetakan tiket dibatalkan.");
        }
    }

    private boolean isValidName(String name) {
        // Memeriksa apakah nama mengandung karakter huruf atau spasi
        return name.matches("[a-zA-Z ]+");
    }


    private boolean isValidNIK(String nik) {
        return nik.matches("^[0-9]+$");
    }

    // Kelas model tabel yang tidak dapat diedit
    private class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    // Kelas untuk merepresentasikan pembelian tiket
    private class PembelianTiket {
        private String nama;
        private String nik;
        private String namaBus;
        private String tujuan;
        private String kursi;
        private double hargaTiket;

        public PembelianTiket(String nama, String nik, String namaBus, String tujuan, String kursi, double hargaTiket) {
            this.nama = nama;
            this.nik = nik;
            this.namaBus = namaBus;
            this.tujuan = tujuan;
            this.kursi = kursi;
            this.hargaTiket = hargaTiket;
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

        public double getHargaTiket() {
            return hargaTiket;
        }
    }
}

