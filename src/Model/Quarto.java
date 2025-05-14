package Model;

public class Quarto {
    private int id;
    private int numero;
    private Tipo tipo;
    private Status status;

    public enum Tipo {
        LUXO, STANDARD, SIMPLES;
    }

    public enum Status {
        LIVRE, RESERVADO, OCUPADO;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Construtores
    public Quarto() {}

    public Quarto(int numero, Tipo tipo, Status status) {
        this.numero = numero;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quarto [numero=" + numero + ", tipo=" + tipo + ", status=" + status + "]";
    }
}
