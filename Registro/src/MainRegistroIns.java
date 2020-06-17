import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;



import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class MainRegistroIns {

    private JFrame frmRegistroElettronico;
    private JTextField txtCaricaVoto;
    private JTextField textFieldIntestazione;
    private JTextField txtCaricaAvviso;
    private JTextField textField_3;
    private JTextField votoMatricola;
    private JTextField votoData;
    private JButton btnNewButton;
    private JTextField avvisoTesto;
    private JTextField avvisoData;

    private Insegnante ins = new Insegnante();
    private DefaultListModel listModel = new DefaultListModel();
    private JLabel lblClasse;
    private JLabel lblData;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    MainRegistroIns window = new MainRegistroIns(args);

                    window.frmRegistroElettronico.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainRegistroIns(String[] args) {
        initialize(args);
    }

   // metodo che richiama inserisciVoto di insegnante e gli passa i valori inseriti dall'utente 
   // nella TextField e nelle ComboBox
    
    private void caricaVoto(Connection connessione, JTextField tf1, JTextField tf2, JComboBox cb1, JComboBox cb2) {

        if (ins.inserisciVoto(connessione, tf2.getText(), Integer.parseInt((String) cb1.getSelectedItem()), ins.cp, Integer.parseInt(tf1.getText()), Integer.parseInt((String) cb2.getSelectedItem())) < 1)
            JOptionPane.showMessageDialog(null, "INSERIMENTO NON RIUSCITO", "PROBLEMA VOTO", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO!", "VOTO CARICATO", JOptionPane.WARNING_MESSAGE);

    }
    
    // metodo che richiama inserisciAvviso di insegnante e gli passa i valori inseriti dall'utente
    // nelle TextField e nella ComboBox
    
    private void caricaAvviso(Connection connessione, JTextField tf1, JTextField tf2, JComboBox cb1) {


        if (ins.inserisciAvviso(connessione, tf2.getText(), ins.cp, Integer.parseInt((String) cb1.getSelectedItem()), tf1.getText()) < 1)
            JOptionPane.showMessageDialog(null, "INSERIMENTO NON RIUSCITO", "PROBLEMA VOTO", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "INSERIMENTO RIUSCITO!", "VOTO CARICATO", JOptionPane.WARNING_MESSAGE);




    }
    
    // metodo per settare la label di intestazione iniziale della schermata
    
    private void setIntestazione(Connection connessione, JTextField intestazione) {

        String i = ins.nome + " " + ins.cognome + "                 CP. " + ins.cp;
        intestazione.setText(i);

    }


    private void lezioniOggi(Connection connessione, JList lez) {

    	// viene popolata la lista di lezioni usando il metodo lezioniOggi di insegnante

        List < Lezione > l = ins.lezioniOggi(connessione);

        lez.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        lez.setBackground(SystemColor.controlShadow);
        String lezioneTesto;
        for (int i = 0; i < l.size(); i++) {
            lezioneTesto = "Ora: " + String.valueOf(l.get(i).ora) + " Aula: " + l.get(i).locazione + " Materia:" + l.get(i).materia;
            listModel.addElement(lezioneTesto);
        }

    }
    private void initialize(String[] args) {

    	//si usa l'email passata negli argomenti del main da login.java per interrogare il db
    	//e ottenere tutte le informazioni sull'insegnante.
        try {
            SQLConnection sqlconn = new SQLConnection();
            Connection conn = sqlconn.getConnection();

            PreparedStatement ps = conn.prepareCall("call getInsegnanteFromEmail(?);");
            ps.setString(1, args[0]);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                ins.codiceFiscale = rs.getString("codiceFiscale");
                ins.cognome = rs.getString("cognome");
                ins.nome = rs.getString("nome");
                ins.email = rs.getString("email");
                ins.password = rs.getString("password");
                ins.cp = rs.getInt("cp");
                ins.telefono = rs.getInt("telefono");

            }
            conn.close();

        } catch (SQLException e3) {
            System.err.format("SQL State: %s\n%s", e3.getSQLState(), e3.getMessage());
        }

        frmRegistroElettronico = new JFrame();
        frmRegistroElettronico.setResizable(false);
        frmRegistroElettronico.getContentPane().setForeground(Color.DARK_GRAY);
        frmRegistroElettronico.setTitle("REGISTRO ELETTRONICO - INSEGNANTE");
        frmRegistroElettronico.getContentPane().setBackground(Color.DARK_GRAY);
        frmRegistroElettronico.setBackground(Color.DARK_GRAY);
        frmRegistroElettronico.setBounds(100, 100, 624, 444);
        frmRegistroElettronico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRegistroElettronico.getContentPane().setLayout(null);

        txtCaricaAvviso = new JTextField();
        txtCaricaAvviso.setHorizontalAlignment(SwingConstants.CENTER);
        txtCaricaAvviso.setText("CARICA AVVISO");
        txtCaricaAvviso.setForeground(Color.ORANGE);
        txtCaricaAvviso.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtCaricaAvviso.setEditable(false);
        txtCaricaAvviso.setColumns(10);
        txtCaricaAvviso.setBackground(Color.DARK_GRAY);
        txtCaricaAvviso.setBounds(18, 271, 247, 34);
        frmRegistroElettronico.getContentPane().add(txtCaricaAvviso);


        txtCaricaVoto = new JTextField();
        txtCaricaVoto.setEditable(false);
        txtCaricaVoto.setHorizontalAlignment(SwingConstants.CENTER);
        txtCaricaVoto.setText("CARICA VOTO");
        txtCaricaVoto.setForeground(Color.ORANGE);



        txtCaricaVoto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtCaricaVoto.setColumns(10);
        txtCaricaVoto.setBackground(Color.DARK_GRAY);
        txtCaricaVoto.setBounds(18, 131, 247, 34);
        frmRegistroElettronico.getContentPane().add(txtCaricaVoto);

        textFieldIntestazione = new JTextField();
        textFieldIntestazione.setText(" NOME COGNOME    CP:  00");
        textFieldIntestazione.setForeground(new Color(255, 248, 220));
        textFieldIntestazione.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 24));
        textFieldIntestazione.setEditable(false);
        textFieldIntestazione.setColumns(10);
        textFieldIntestazione.setBackground(new Color(95, 158, 160));
        textFieldIntestazione.setBounds(18, 30, 399, 68);
        frmRegistroElettronico.getContentPane().add(textFieldIntestazione);

        JButton button = new JButton("LOG OUT");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                login.main(null);
                frmRegistroElettronico.dispose();
            }
        });
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        button.setBackground(new Color(255, 99, 71));
        button.setBounds(447, 30, 142, 68);
        frmRegistroElettronico.getContentPane().add(button);

        textField_3 = new JTextField();
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setText("LEZIONI DI OGGI:");
        textField_3.setForeground(Color.ORANGE);
        textField_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBackground(Color.DARK_GRAY);
        textField_3.setBounds(295, 131, 294, 34);
        frmRegistroElettronico.getContentPane().add(textField_3);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.controlDkShadow);
        panel.setBounds(295, 175, 294, 130);
        frmRegistroElettronico.getContentPane().add(panel);
        JList list = new JList(listModel);
        panel.add(list);

        votoMatricola = new JTextField();
        votoMatricola.setBackground(Color.LIGHT_GRAY);
        votoMatricola.setBounds(18, 208, 39, 28);
        frmRegistroElettronico.getContentPane().add(votoMatricola);
        votoMatricola.setColumns(10);

        JComboBox votoOra = new JComboBox();
        votoOra.setModel(new DefaultComboBoxModel(new String[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
        }));
        votoOra.setBackground(Color.WHITE);
        votoOra.setBounds(179, 207, 39, 28);
        frmRegistroElettronico.getContentPane().add(votoOra);

        votoData = new JTextField();
        votoData.setBackground(Color.LIGHT_GRAY);
        votoData.setColumns(10);
        votoData.setBounds(60, 208, 111, 28);
        frmRegistroElettronico.getContentPane().add(votoData);

        JComboBox votoRisultato = new JComboBox();
        votoRisultato.setModel(new DefaultComboBoxModel(new String[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        }));
        votoRisultato.setBounds(226, 207, 39, 29);
        frmRegistroElettronico.getContentPane().add(votoRisultato);

        btnNewButton = new JButton("CARICA");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// si usa il metodo caricaVoto per provare ad inserire il voto
            	// nel db al click su "CARICA"
                try {

                    SQLConnection sqlconn = new SQLConnection();
                    Connection conn = sqlconn.getConnection();

                    caricaVoto(conn, votoMatricola, votoData, votoOra, votoRisultato);
                    conn.close();
                } catch (SQLException e2) {
                    System.err.format("SQL State: %s\n%s", e2.getSQLState(), e2.getMessage());
                }


            }
        });
        btnNewButton.setBackground(Color.ORANGE);
        btnNewButton.setBounds(18, 240, 247, 21);
        frmRegistroElettronico.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel(" Matr.                 Data                   Ora      Voto");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(18, 185, 247, 13);
        frmRegistroElettronico.getContentPane().add(lblNewLabel);

        avvisoTesto = new JTextField();
        avvisoTesto.setColumns(10);
        avvisoTesto.setBackground(Color.LIGHT_GRAY);
        avvisoTesto.setBounds(18, 338, 427, 59);
        frmRegistroElettronico.getContentPane().add(avvisoTesto);

        JComboBox avvisoClasse = new JComboBox();
        avvisoClasse.setModel(new DefaultComboBoxModel(new String[] {
            "4",
            "9",
            "10",
            "22"
        }));
        avvisoClasse.setBounds(453, 338, 39, 29);
        frmRegistroElettronico.getContentPane().add(avvisoClasse);

        avvisoData = new JTextField();
        avvisoData.setColumns(10);
        avvisoData.setBackground(Color.LIGHT_GRAY);
        avvisoData.setBounds(495, 338, 94, 30);
        frmRegistroElettronico.getContentPane().add(avvisoData);

        JButton button_1 = new JButton("CARICA");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// si usa il metodo caricaAvviso per provare ad inserire l'avviso
            	// nel db al click su "CARICA"
            	
                try {
                    SQLConnection sqlconn = new SQLConnection();
                    Connection conn = sqlconn.getConnection();

                    caricaAvviso(conn, avvisoTesto, avvisoData, avvisoClasse);
                    conn.close();
                } catch (SQLException e1) {
                    System.err.format("SQL State: %s\n%s", e1.getSQLState(), e1.getMessage());
                }

            }
        });
        button_1.setBackground(Color.ORANGE);
        button_1.setBounds(453, 377, 136, 21);
        frmRegistroElettronico.getContentPane().add(button_1);

        JLabel lblTestoDataClasse = new JLabel("Testo");
        lblTestoDataClasse.setForeground(Color.WHITE);
        lblTestoDataClasse.setBounds(18, 315, 85, 13);
        frmRegistroElettronico.getContentPane().add(lblTestoDataClasse);


        lblClasse = new JLabel("Classe");
        lblClasse.setForeground(Color.WHITE);
        lblClasse.setBounds(452, 315, 85, 13);
        frmRegistroElettronico.getContentPane().add(lblClasse);

        lblData = new JLabel("Data");
        lblData.setForeground(Color.WHITE);
        lblData.setBounds(504, 315, 85, 13);
        frmRegistroElettronico.getContentPane().add(lblData);

        // si aggiorna l'intestazione usando il metodo lezioniOggi
        try {
            SQLConnection sqlconn = new SQLConnection();
            Connection conn = sqlconn.getConnection();
            setIntestazione(conn, textFieldIntestazione);
            lezioniOggi(conn, list);
            conn.close();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }
}