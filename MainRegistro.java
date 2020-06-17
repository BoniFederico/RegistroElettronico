import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.sql.*;
import java.util.List;
public class MainRegistro {

    private JFrame frmRegistroElettronico;
    private JTextField txtUltimoVoto;
    private JTextField textFieldVoto;
    private JTextField TextFieldVotoData;
    private JTextField txtLezioniDiOggi;
    private JPanel panel;
    private JList list;
    private JTextField txtUltimaAssenza;
    private JTextField textFieldAssenzaOra;
    private JTextField textFieldAvvisoData;
    private JTextField txtUltimoAvviso;
    private JTextField textFieldAvvisoTesto;
    private JTextField textFieldAssenzaData;
    private JTextField intestazione;
    private DefaultListModel listModel = new DefaultListModel();

    private Studente stud = new Studente();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainRegistro window = new MainRegistro(args);
                    window.frmRegistroElettronico.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainRegistro(String[] args) {
        initialize(args);
    }



    private void setVoto(Connection connessione, JTextField votoData, JTextField voto) {


        Risultato r = stud.ultimoVoto(connessione);
        votoData.setText(r.data);
        voto.setText(" " + String.valueOf(r.voto));


    }

    private void setAssenza(Connection connessione, JTextField assenzaData, JTextField assenzaOra) {


        Assenza s = stud.ultimaAssenza(connessione);
        assenzaData.setText(s.data);
        assenzaOra.setText(String.valueOf(s.ora) + "a ORA");

    }

    private void setAvviso(Connection connessione, JTextField avvisoData, JTextField avvisoTesto) {

        Avviso s = stud.ultimoAvviso(connessione);
        avvisoData.setText("DATA: " + s.data);
        avvisoTesto.setText(String.valueOf(s.testo));



    }
    private void setIntestazione(Connection connessione, JTextField intestazione) {

        String i = stud.nome + " " + stud.cognome + "                 MAT. " + stud.matricola + "   CLASSE: " + stud.classe;
        intestazione.setText(i);

    }

    private void lezioniOggi(Connection connessione, JList lez) {



        List < Lezione > l = stud.lezioniOggi(connessione);

        lez.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        lez.setBackground(SystemColor.controlShadow);
        String lezioneTesto;
        for (int i = 0; i < l.size(); i++) {
            lezioneTesto = "Ora: " + String.valueOf(l.get(i).ora) + " Argomento: " + l.get(i).argomento;
            listModel.addElement(lezioneTesto);
        }



    }
    private void initialize(String[] args) {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro?noAccessToProcedureBodies=true", "studente", "studentePss");
            PreparedStatement ps = conn.prepareCall("call getStudenteFromEmail(?);");
            ps.setString(1, args[0]);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                stud.codiceFiscale = rs.getString("codiceFiscale");
                stud.cognome = rs.getString("cognome");
                stud.nome = rs.getString("nome");
                stud.email = rs.getString("email");
                stud.password = rs.getString("password");
                stud.matricola = rs.getInt("matricola");
                stud.classe = rs.getInt("classe");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.format("SQL State: %s\n%s", e.getClass(), e.getMessage());
        }



        frmRegistroElettronico = new JFrame();
        frmRegistroElettronico.setTitle("REGISTRO ELETTRONICO - STUDENTE");
        frmRegistroElettronico.setResizable(false);
        frmRegistroElettronico.getContentPane().setBackground(Color.DARK_GRAY);
        frmRegistroElettronico.getContentPane().setLayout(null);

        textFieldVoto = new JTextField();
        textFieldVoto.setBackground(SystemColor.controlDkShadow);
        textFieldVoto.setEditable(false);
        textFieldVoto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 54));
        textFieldVoto.setText("7");
        textFieldVoto.setBounds(8, 115, 69, 85);
        frmRegistroElettronico.getContentPane().add(textFieldVoto);
        textFieldVoto.setColumns(10);

        TextFieldVotoData = new JTextField();
        TextFieldVotoData.setEditable(false);
        TextFieldVotoData.setBackground(SystemColor.controlDkShadow);
        TextFieldVotoData.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        TextFieldVotoData.setText("Ginnastica");
        TextFieldVotoData.setBounds(85, 159, 136, 41);
        frmRegistroElettronico.getContentPane().add(TextFieldVotoData);
        TextFieldVotoData.setColumns(10);

        txtUltimoVoto = new JTextField();
        txtUltimoVoto.setBounds(85, 115, 136, 34);
        frmRegistroElettronico.getContentPane().add(txtUltimoVoto);
        txtUltimoVoto.setBackground(Color.DARK_GRAY);
        txtUltimoVoto.setForeground(Color.ORANGE);
        txtUltimoVoto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtUltimoVoto.setText("ULTIMO VOTO");
        txtUltimoVoto.setEditable(false);
        txtUltimoVoto.setColumns(10);

        txtLezioniDiOggi = new JTextField();
        txtLezioniDiOggi.setText("LEZIONI DI OGGI:");
        txtLezioniDiOggi.setForeground(Color.ORANGE);
        txtLezioniDiOggi.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtLezioniDiOggi.setEditable(false);
        txtLezioniDiOggi.setColumns(10);
        txtLezioniDiOggi.setBackground(Color.DARK_GRAY);
        txtLezioniDiOggi.setBounds(267, 115, 294, 34);
        frmRegistroElettronico.getContentPane().add(txtLezioniDiOggi);

        panel = new JPanel();
        panel.setBackground(SystemColor.controlDkShadow);
        panel.setBounds(267, 159, 294, 113);
        frmRegistroElettronico.getContentPane().add(panel);

        list = new JList(listModel);
        panel.add(list);

        txtUltimaAssenza = new JTextField();
        txtUltimaAssenza.setText("ULTIMA ASSENZA");
        txtUltimaAssenza.setForeground(Color.ORANGE);
        txtUltimaAssenza.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtUltimaAssenza.setEditable(false);
        txtUltimaAssenza.setColumns(10);
        txtUltimaAssenza.setBackground(Color.DARK_GRAY);
        txtUltimaAssenza.setBounds(38, 251, 136, 34);
        frmRegistroElettronico.getContentPane().add(txtUltimaAssenza);

        textFieldAssenzaOra = new JTextField();
        textFieldAssenzaOra.setText("ORA:");
        textFieldAssenzaOra.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        textFieldAssenzaOra.setEditable(false);
        textFieldAssenzaOra.setColumns(10);
        textFieldAssenzaOra.setBackground(SystemColor.controlDkShadow);
        textFieldAssenzaOra.setBounds(38, 293, 136, 41);
        frmRegistroElettronico.getContentPane().add(textFieldAssenzaOra);

        textFieldAvvisoData = new JTextField();
        textFieldAvvisoData.setText("DATA:");
        textFieldAvvisoData.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        textFieldAvvisoData.setEditable(false);
        textFieldAvvisoData.setColumns(10);
        textFieldAvvisoData.setBackground(SystemColor.controlDkShadow);
        textFieldAvvisoData.setBounds(340, 296, 221, 34);
        frmRegistroElettronico.getContentPane().add(textFieldAvvisoData);

        txtUltimoAvviso = new JTextField();
        txtUltimoAvviso.setText("ULTIMO AVVISO");
        txtUltimoAvviso.setForeground(Color.ORANGE);
        txtUltimoAvviso.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        txtUltimoAvviso.setEditable(false);
        txtUltimoAvviso.setColumns(10);
        txtUltimoAvviso.setBackground(Color.DARK_GRAY);
        txtUltimoAvviso.setBounds(196, 296, 136, 34);
        frmRegistroElettronico.getContentPane().add(txtUltimoAvviso);

        textFieldAvvisoTesto = new JTextField();
        textFieldAvvisoTesto.setText("Testo dell'avviso..");
        textFieldAvvisoTesto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
        textFieldAvvisoTesto.setEditable(false);
        textFieldAvvisoTesto.setColumns(10);
        textFieldAvvisoTesto.setBackground(SystemColor.controlDkShadow);
        textFieldAvvisoTesto.setBounds(196, 340, 365, 58);
        frmRegistroElettronico.getContentPane().add(textFieldAvvisoTesto);

        textFieldAssenzaData = new JTextField();
        textFieldAssenzaData.setText("DATA:");
        textFieldAssenzaData.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        textFieldAssenzaData.setEditable(false);
        textFieldAssenzaData.setColumns(10);
        textFieldAssenzaData.setBackground(SystemColor.controlDkShadow);
        textFieldAssenzaData.setBounds(38, 344, 136, 41);
        frmRegistroElettronico.getContentPane().add(textFieldAssenzaData);

        intestazione = new JTextField();
        intestazione.setText("NOME COGNOME MAT./ --------------------------   CLASSE");
        intestazione.setForeground(new Color(255, 248, 220));
        intestazione.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
        intestazione.setEditable(false);
        intestazione.setColumns(10);
        intestazione.setBackground(new Color(95, 158, 160));
        intestazione.setBounds(8, 21, 399, 68);
        frmRegistroElettronico.getContentPane().add(intestazione);


        //AGGIORNO I DATI


        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro?noAccessToProcedureBodies=true", "studente", "studentePss");
            setVoto(conn, TextFieldVotoData, textFieldVoto);
            setAssenza(conn, textFieldAssenzaOra, textFieldAssenzaData);
            setAvviso(conn, textFieldAvvisoData, textFieldAvvisoTesto);
            lezioniOggi(conn, list);
            setIntestazione(conn, intestazione);
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.format("SQL State: %s\n%s", e.getClass(), e.getMessage());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }


        //-----------------------------------------
        JButton btnNewButton = new JButton("LOG OUT");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                login.main(null);
                frmRegistroElettronico.dispose();

            }

        });
        btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        btnNewButton.setBackground(Color.RED);
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBounds(419, 21, 142, 68);
        frmRegistroElettronico.getContentPane().add(btnNewButton);
        frmRegistroElettronico.setBackground(Color.DARK_GRAY);
        frmRegistroElettronico.setBounds(100, 100, 580, 458);
        frmRegistroElettronico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
