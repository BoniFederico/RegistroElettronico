public class Assenza {

    String status;
    String data;
    int ora;
    int insegnante;
    int studente;

    public Assenza() {}

    public Assenza(String status, String data, int ora, int insegnante, int studente) {

        this.status = status;
        this.data = data;
        this.ora = ora;
        this.insegnante = insegnante;
        this.studente = studente;
    }
}
