package Model;

public class Quarto {
    private int id;
    private int numero;
    private Status status;

    public enum Status {
        VAZIO, RESERVADO, OCUPADO;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Construtores
    public Quarto() {}

    public Quarto(int numero, Status status) {
        this.numero = numero;
        this.status = status;
    }

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero; 
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quarto [numero=" + numero + ", status=" + status + "]";
    }
}
