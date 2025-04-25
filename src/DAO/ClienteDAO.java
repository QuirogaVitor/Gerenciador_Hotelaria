package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Cliente;

public class ClienteDAO {

    public static Cliente buscarCliente(String filtro) {
        String sql = "SELECT * FROM cliente " + filtro;
        
        try {
            Connection conn = ConexaoBD.conectar();

             PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String nome = rs.getString("NOME");
                    String dataNasc = rs.getString("DATA_NASC");
                    String telefone = rs.getString("TELEFONE");
                    String login = rs.getString("senha");
                    String cpf = rs.getString("CPF");

                    return new Cliente(nome, dataNasc, telefone, cpf, login);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

