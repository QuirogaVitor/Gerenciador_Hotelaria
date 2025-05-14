package Data;

import java.sql.*;
import java.util.*;
import Model.*;

public class QuartoDAO {
    private Connection conexao;

    public QuartoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Quarto quarto) {
        try{
            String sql = "INSERT INTO quarto (id, numero, tipo, status) VALUES (DEFAULT, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, quarto.getNumero());
            stmt.setString(2, quarto.getTipo().name()); // Salva o tipo do quarto como string
            stmt.setString(3, quarto.getStatus().name()); // Salva o status do quarto como string
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public void atualizar(Quarto quarto) {
        try{
            String sql = "UPDATE quarto SET tipo=?, status=? WHERE numero=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, quarto.getTipo().name());
            stmt.setString(2, quarto.getStatus().name());
            stmt.setInt(3, quarto.getNumero());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void excluir(int numero) {
        try{
            String sql = "DELETE FROM quarto WHERE numero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numero);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public Quarto buscarPorNumero(int numero) {
        try{
            String sql = "SELECT * FROM quarto WHERE numero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            Quarto q = null;
            if (rs.next()) {
                q = new Quarto();
                q.setNumero(rs.getInt("numero"));
                q.setTipo(Quarto.Tipo.valueOf(rs.getString("tipo")));  // Converte string para enum
                q.setStatus(Quarto.Status.valueOf(rs.getString("status")));  // Converte string para enum
            }
            rs.close();
            stmt.close();
            return q;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public List<Quarto> listarTodos() {
        try{
            String sql = "SELECT * FROM quarto";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Quarto> lista = new ArrayList<>();
            while (rs.next()) {
                Quarto q = new Quarto();
                q.setNumero(rs.getInt("numero"));
                q.setTipo(Quarto.Tipo.valueOf(rs.getString("tipo")));
                q.setStatus(Quarto.Status.valueOf(rs.getString("status")));
                lista.add(q);
            }
            rs.close();
            stmt.close();
            return lista;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }
}
