import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JPasswordField;
public class login extends JFrame {

    private JPanel contentPane;
    private JTextField emailTextBox;
    private JPasswordField passwordTextBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
                    frame.setUndecorated(true);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //METODO PER IL CONTROLLO DELLA STRUTTURA DI UN INDIRIZZO EMAIL
    private boolean checkEmail(String e) {
        boolean r = true;
        try {
            InternetAddress email = new InternetAddress(e);
            email.validate();
        } catch (AddressException exc) {
            r = false;
        }
        return r;
    }
    public login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("LOGIN");
        setBackground(Color.LIGHT_GRAY);
        setBounds(100, 100, 337, 342);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        emailTextBox = new JTextField();
        emailTextBox.setToolTipText("");
        emailTextBox.setBounds(8, 98, 315, 41);
        contentPane.add(emailTextBox);
        emailTextBox.setColumns(10);

        JLabel lblNewLabel = new JLabel("REGISTRO ELETTRONICO");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
        lblNewLabel.setBounds(69, 23, 240, 45);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("EMAIL");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(8, 75, 43, 13);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(8, 149, 112, 13);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton = new JButton("LOG IN ");
        
        /* AL CLICK SU LOGIN --------------------------------------------
         * Viene richiamato il metodo per il controllo della mail
         * Viene interrogato il db per cercare la presenza di uno studente con quelle credenziali
         * Se presente viene richiamato il main di MainRegistro
         * Se non presente si interroga nuovamente il db cercando un insegnante con quelle credenziali
         * Se presente viene richiamato il main di MainRegistroIns
         * Altrimenti: messaggio d'errore
         *
         */
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String email = emailTextBox.getText();
                String password = passwordTextBox.getText();
                if (!checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email non valida!");
                } else {

                    try {
                        SQLConnection sqlconn = new SQLConnection();
                        Connection conn = sqlconn.getConnection();

                        PreparedStatement ps = conn.prepareCall("call credenzialiStudente(?,?);");

                        ps.setString(1, email);
                        ps.setString(2, password);

                        ResultSet rs = ps.executeQuery();


                        if (rs.next()) {
                            String arg[] = {
                                email,
                                password
                            };
                            MainRegistro.main(arg);
                            dispose();
                        } else {
                            ps = conn.prepareCall("call credenzialiInsegnante(?,?);");
                            ps.setString(1, email);
                            ps.setString(2, password);
                            rs = ps.executeQuery();

                            if (rs.next()) {

                                String arg[] = {
                                    email,
                                    password
                                };
                                MainRegistroIns.main(arg);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Credenziali errate!");
                                emailTextBox.setText("");
                                passwordTextBox.setText("");

                            }

                        }
                        conn.close();

                    } catch (SQLException e1) {
                        System.err.format("SQL State: %s\n%s", e1.getSQLState(), e1.getMessage());
                    }


                }
            }
        });
        btnNewButton.setBackground(SystemColor.controlShadow);
        btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
        btnNewButton.setBounds(8, 227, 315, 36);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("ESCI");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        btnNewButton_1.setBackground(SystemColor.controlShadow);
        btnNewButton_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 12));
        btnNewButton_1.setBounds(8, 274, 315, 31);
        contentPane.add(btnNewButton_1);

        passwordTextBox = new JPasswordField();
        passwordTextBox.setBounds(8, 172, 315, 41);
        contentPane.add(passwordTextBox);
    }
}