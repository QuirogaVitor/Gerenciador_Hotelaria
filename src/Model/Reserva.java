package Model;

import java.time.LocalDate;

public class Reserva {

    public enum Status {
        AGUARDANDO, OCUPADO, FINALIZADO
    }

    private int codigoReserva;
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataReserva;
    private LocalDate dataCheckin;
    private LocalDate dataCheckout;
    private Status status;

    // Construtores
    public Reserva() {}

    public Reserva(int codigoReserva, Cliente cliente, Quarto quarto, LocalDate dataReserva, LocalDate dataCheckin, LocalDate dataCheckout, Status status) {
        this.codigoReserva = codigoReserva;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataReserva = dataReserva;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.status = status;
    }

    // Getters e Setters
    public int getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(LocalDate dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
