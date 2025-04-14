package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Cliente;
import Model.Quarto;
import Model.Reserva;

public class ReservaDAO {

    public static Reserva getReserva(String filtro){
        String sql = "SELECT * FROM Reserva " + filtro;
            // Implementar busca de reserva no banco
        try(Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            
            Reserva reserva = new Reserva(null, null)

        return reserva;
        }

    }
}
