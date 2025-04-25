package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Cliente;
import Model.Enums.StatusReserva;
import Model.Quarto;
import Model.Reserva;

public class ReservaDAO {

    public static Reserva buscarReserva(String filtro){
        String sql = "SELECT * FROM Reserva " + filtro + " LIMIT 1";
            // Implementar busca de reserva no banco
        try(Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){

                int idReserva = rs.getInt("id_reserva");
                int idCliente = rs.getInt("id_cliente");
                int idQuarto = rs.getInt("id_quarto");
                java.sql.Date dataCheckIn = rs.getDate("data_check_in");
                java.sql.Date dataCheckOut = rs.getDate("data_check_out");
                StatusReserva status = StatusReserva.valueOf(rs.getString("status"));

                return new Reserva(idReserva, idCliente, idQuarto, dataCheckIn, dataCheckOut, status  );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
