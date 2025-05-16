package Data;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import Model.*;

public class ReservaDAO {
    private Connection conexao;

    public ReservaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Reserva reserva) {
        try{
            String sql = "INSERT INTO reserva (id, codigo_reserva, cpf_cliente, numero_quarto, data_reserva, data_checkin, data_checkout, status) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, reserva.getCodigoReserva());
            stmt.setString(2, reserva.getCliente().getCpf());
            stmt.setInt(3, reserva.getQuarto().getNumero());
            stmt.setDate(4, Date.valueOf(reserva.getDataReserva()));
            stmt.setDate(5, reserva.getDataCheckin() != null ? Date.valueOf(reserva.getDataCheckin()) : null);
            stmt.setDate(6, reserva.getDataCheckout() != null ? Date.valueOf(reserva.getDataCheckout()) : null);
            stmt.setString(7, reserva.getStatus().toString());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public void atualizar(Reserva reserva) {
        try{
            String sql = "UPDATE reserva SET cpf_cliente=?, numero_quarto=?, data_reserva=?, data_checkin=?, data_checkout=?, status=? WHERE codigo_reserva=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, reserva.getCliente().getCpf());
            stmt.setInt(2, reserva.getQuarto().getNumero());
            stmt.setDate(3, Date.valueOf(reserva.getDataReserva()));
            stmt.setDate(4, reserva.getDataCheckin() != null ? Date.valueOf(reserva.getDataCheckin()) : null);
            stmt.setDate(5, reserva.getDataCheckout() != null ? Date.valueOf(reserva.getDataCheckout()) : null);
            stmt.setString(6, reserva.getStatus().toString());
            stmt.setInt(7, reserva.getCodigoReserva());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public void excluir(int codigoReserva) {
        try{
            String sql = "DELETE FROM reserva WHERE codigo_reserva = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoReserva);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public Reserva buscarPorCodigo(int codigoReserva) {
        try{
            String sql = "SELECT * FROM reserva WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoReserva);
            ResultSet rs = stmt.executeQuery();
            Reserva reserva = null;
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setCodigoReserva(rs.getInt("id"));
                reserva.setCliente(new ClienteDAO(conexao).buscarPorCpf(rs.getString("id_cliente")));
                reserva.setQuarto(new QuartoDAO(conexao).buscarPorNumero(rs.getInt("id_quarto")));
                reserva.setDataReserva(rs.getDate("data_reserva").toLocalDate());
                reserva.setDataCheckin(rs.getDate("data_checkin") != null ? rs.getDate("data_checkin").toLocalDate() : null);
                reserva.setDataCheckout(rs.getDate("data_checkout") != null ? rs.getDate("data_checkout").toLocalDate() : null);
                reserva.setStatus(Reserva.Status.valueOf(rs.getString("status_reserva")));
            }
            rs.close();
            stmt.close();
            return reserva;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public List<Reserva> listarTodos() {
        try{
            String sql = "SELECT * FROM reserva";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Reserva> reservas = new ArrayList<>();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setCodigoReserva(rs.getInt("id"));
                reserva.setCliente(new ClienteDAO(conexao).buscarPorCpf(rs.getString("id_cliente")));
                reserva.setQuarto(new QuartoDAO(conexao).buscarPorNumero(rs.getInt("id_quarto")));
                reserva.setDataReserva(rs.getDate("data_reserva").toLocalDate());
                reserva.setDataCheckin(rs.getDate("data_checkin") != null ? rs.getDate("data_checkin").toLocalDate() : null);
                reserva.setDataCheckout(rs.getDate("data_checkout") != null ? rs.getDate("data_checkout").toLocalDate() : null);
                reserva.setStatus(Reserva.Status.valueOf(rs.getString("status_reserva")));
                reservas.add(reserva);
            }
            rs.close();
            stmt.close();
            return reservas;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }
}
