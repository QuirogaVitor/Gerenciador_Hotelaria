package Data;

import java.sql.*;
import java.util.*;
import Model.*;

public class ClienteDAO {
    private Connection conexao;

    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){
        try{
            String sql = "INSERT INTO cliente (id,nome, cpf, email, telefone, data_nascimento) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setDate(5, cliente.getDataNascimento());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public void atualizar(Cliente cliente) {
        try{
            String sql = "UPDATE cliente SET nome=?, email=?, telefone=?, data_nascimento=? WHERE cpf=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setDate(4, cliente.getDataNascimento());
            stmt.setString(5, cliente.getCpf());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void excluir(String cpf) {
        try{
            String sql = "DELETE FROM cliente WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        try{
            String sql = "SELECT * FROM cliente WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Cliente c = null;
            if (rs.next()) {
                c = new Cliente();
                c.setClienteId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
            }
            rs.close();
            stmt.close();
            return c;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public List<Cliente> listarTodos() {
        try{
            String sql = "SELECT * FROM cliente";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setDataNascimento(rs.getDate("data_nascimento"));
                c.setClienteId(rs.getInt("id"));
                lista.add(c);
            }
            rs.close();
            stmt.close();
            return lista;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }
}
