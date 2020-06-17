import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Studente {

    String codiceFiscale;
    String nome;
    String cognome;
    String email;
    String password;
    int matricola;
    int classe;

    public Studente() {};

    public Studente(String codiceFiscale, String nome, String cognome, String email, String password, int matricola, int classe) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.matricola = matricola;
        this.classe = classe;
    }

    public Risultato ultimoVoto(Connection connessione) {

        Risultato res = new Risultato("-", 0, 0, 0, 0);
        try {


            PreparedStatement ps = connessione.prepareCall("call ultimoVoto(?);");
            ps.setInt(1, this.matricola);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                res.data = rs.getString("data");
                res.ora = rs.getInt("ora");
                res.insegnante = rs.getInt("insegnante");
                res.studente = rs.getInt("studente");
                res.voto = rs.getInt("voto");


            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return res;

    }


    public List < Lezione > lezioniOggi(Connection connessione) {

        List < Lezione > res = new ArrayList < Lezione > ();

        try {


            PreparedStatement ps = connessione.prepareCall("call lezioneOggi(?);");
            ps.setInt(1, this.classe);
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

    public Assenza ultimaAssenza(Connection connessione) {

        Assenza res = new Assenza("-", "-", 0, 0, 0);

        try {


            PreparedStatement ps = connessione.prepareCall("call ultimaAssenza(?);");
            ps.setInt(1, this.matricola);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                res.status = rs.getString("status");
                res.data = rs.getString("data");
                res.ora = rs.getInt("ora");
                res.insegnante = rs.getInt("insegnante");
                res.studente = rs.getInt("studente");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return res;
    }

    public Avviso ultimoAvviso(Connection connessione) {

        Avviso res = new Avviso(0, "-", 0, 0, "-");



        try {


            PreparedStatement ps = connessione.prepareCall("call ultimoAvviso(?);");
            ps.setInt(1, this.classe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                res.numero = rs.getInt("numero");
                res.data = rs.getString("data");
                res.insegnante = rs.getInt("insegnante");
                res.classe = rs.getInt("classe");
                res.testo = rs.getString("testo");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return res;

    }




}
