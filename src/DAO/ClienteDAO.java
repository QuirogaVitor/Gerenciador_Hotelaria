package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Cliente;

public class ClienteDAO {

    public static Cliente buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM clientes WHERE CPF = ?";
        
        try (Connection conn = ConexaoBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                String dataNasc = rs.getString("DATA_NASC");
                String telefone = rs.getString("TELEFONE");
                String login = rs.getString("senha");

                return new Cliente(nome, dataNasc, telefone, cpf, login);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

