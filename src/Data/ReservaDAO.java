package Data;

import Model.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservaDAO {
    private Connection conexao;

    public ReservaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Reserva reserva) {
        try{
            String sql = "INSERT INTO reserva (id, id_cliente, id_quarto, data_reserva, data_checkin, data_checkout, status_reserva) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, reserva.getCodigoReserva());
            stmt.setInt(2, reserva.getCliente().getClienteId());
            stmt.setInt(3, reserva.getQuarto().getId());
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
            String sql = "UPDATE reserva SET id_cliente=?, id_quarto=?, data_reserva=?, data_checkin=?, data_checkout=?, status_reserva=? WHERE id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, reserva.getCliente().getClienteId());
            stmt.setInt(2, reserva.getQuarto().getId());
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
            String sql = "DELETE FROM reserva WHERE id = ?";
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
                reserva.setCliente(new ClienteDAO(conexao).buscarPorId(rs.getInt("id_cliente")));
                reserva.setQuarto(new QuartoDAO(conexao).bucarPorId(rs.getInt("id_quarto")));
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
                reserva.setCliente(new ClienteDAO(conexao).buscarPorId(rs.getInt("id_cliente")));
                reserva.setQuarto(new QuartoDAO(conexao).bucarPorId(rs.getInt("id_quarto")));
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

    public Reserva buscarAtivaPorNumeroQuarto(int numeroQuarto) {
        try{
            String sql = """
            SELECT r.* 
            FROM reserva r 
            INNER JOIN quarto q ON q.id = r.id_quarto
            WHERE q.numero = ?
            AND r.status_reserva = 'OCUPADO'
            LIMIT 1;
            """;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numeroQuarto);
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
}
