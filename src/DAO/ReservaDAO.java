package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Cliente;
import Model.Quarto;
import Model.Reserva;

public class ReservaDAO {

    public static Reserva getReserva(String filtro){
        String sql = "SELECT * FROM Reserva " + filtro;
            // Implementar busca de reserva no banco
        try(Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){

                String idReserva = rs.getString("id_reserva");
                String idCliente = rs.getString("id_cliente");
                String idHotel = rs.getString("id_hotel");
                java.sql.Date dataCheckIn = rs.getDate("data_check_in");
                java.sql.Date dataCheckOut = rs.getDate("data_check_out");
                String status = rs.getString("status");

                Cliente cliente = ClienteDAO.buscarPorCPF(idCliente);

                Quarto quarto = new Quarto(0);

                return new Reserva(quarto, cliente);
            }

        
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
