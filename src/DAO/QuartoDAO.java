package DAO;

import Model.Cliente;
import Model.Enums.StatusQuarto;
import Model.Quarto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuartoDAO {

    public static Quarto buscarQuarto(String filtro) {
        String sql = "SELECT * FROM quarto " + filtro;

        try {
            Connection conn = ConexaoBD.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                int numero = rs.getInt("NUMERO");
                StatusQuarto status = StatusQuarto.valueOf(rs.getString("STATUS"));

                return new Quarto(id, numero, status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
