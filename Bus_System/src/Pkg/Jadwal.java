package Pkg;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jadwal {
    JFrame frmBusSystemBy;
    private ImageIcon imageIcon;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Jadwal window = new Jadwal();
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

    public Jadwal() {
        initialize();
    }

    public JFrame getFrame() {
        return frmBusSystemBy;
    }

    private void initialize() {
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 777, 463);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ganti path gambar sesuai dengan lokasi gambar Anda
        String imagePath = "D:\\Download\\aINmBZ.png";
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
        panel_1.setBackground(new Color(255, 255, 255, 100));

        JTextField searchField = new JTextField();
        searchField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle search action
            }
        });

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(120)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                .addGap(118))
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                                .addContainerGap(524, Short.MAX_VALUE)
                                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addGap(14))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                .addGap(23)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                .addGap(51))
        );

        JLabel lblNewLabel_1 = new JLabel("Rute Jakarta-Surabaya");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTextArea txtrHariSenin = new JTextArea();
        txtrHariSenin.setText("Hari Senin - Sabtu:\r\n- 08:00 - Keberangkatan dari Terminal Bus Gambir, Jakarta\r\n- 11:30 - Istirahat dan Makan Siang di Rest Area Cikampek\r\n- 15:00 - Tiba di Terminal Bus Purabaya, Surabaya\r\n\n\nHari Minggu:\r\n"
                + "- 09:00 - Keberangkatan dari Terminal Bus Gambir, Jakarta\r\n"
                + "- 12:30 - Istirahat dan Makan Siang di Rest Area Cikampek\r\n"
                + "- 16:30 - Tiba di Terminal Bus Purabaya, Surabaya");

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              	MainMenu mainMenu = new MainMenu();
                mainMenu.getFrame().setVisible(true); 
                frmBusSystemBy.dispose();
                // Handle back action
            }
        });

        JButton btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle logout action
            }
        });

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
        			.addGap(33)
        			.addComponent(btnBack)
        			.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
        			.addComponent(btnLogout)
        			.addGap(130))
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(55)
        			.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(213, Short.MAX_VALUE))
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtrHariSenin, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(198, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(txtrHariSenin, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnLogout))
        			.addContainerGap())
        );
        panel_1.setLayout(gl_panel_1);
        panel.setLayout(gl_panel);
    }
}
