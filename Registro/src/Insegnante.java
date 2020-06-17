import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Insegnante {

    String nome;
    String cognome;
    String email;
    String codiceFiscale;
    int cp;
    int telefono;
    String password;

    public Insegnante() {}

    public Insegnante(String nome, String cognome, String email, String codiceFiscale, int cp, int telefono, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.cp = cp;
        this.telefono = telefono;
        this.password = password;
    }

    private boolean checkData(String d) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(d);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public int inserisciVoto(Connection connessione, String data, int ora, int insegnante, int studente, int voto) {



        int result = -1;

        if (!checkData(data))
            return result;

        try {

            PreparedStatement ps = connessione.prepareCall("call inserisciVoto(?,?,?,?,?);");

            ps.setString(1, data);
            ps.setInt(2, ora);
            ps.setInt(3, insegnante);
            ps.setInt(4, studente);
            ps.setInt(5, voto);

            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return result;

    }


    public int inserisciAvviso(Connection connessione, String data, int insegnante, int classe, String testo) {

        int result = -1;


        if (!checkData(data))
            return result;

        try {


            PreparedStatement ps = connessione.prepareCall("call inserisciAvviso(?,?,?,?);");

            ps.setString(1, data);
            ps.setInt(2, insegnante);
            ps.setInt(3, classe);
            ps.setString(4, testo);

            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return result;

    }


    public List < Lezione > lezioniOggi(Connection connessione) {

        List < Lezione > res = new ArrayList < Lezione > ();

        try {


            PreparedStatement ps = connessione.prepareCall("call lezioneOggiInsegnante(?);");
            ps.setInt(1, this.cp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Lezione lez = new Lezione();
                lez.data = rs.getString("data");
                lez.ora = rs.getInt("ora");
                lez.locazione = rs.getInt("locazione");
                lez.materia = rs.getString("materia");
                lez.insegnante = rs.getInt("insegnante");
                lez.argomento = rs.getString("argomento");
                res.add(lez);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return res;
    }


}
