package Data;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import Model.*;
import Model.Enums.Cargo;
import Model.Funcionarios.*;

public class FuncionarioDAO {
    private Connection conexao;

    public FuncionarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Funcionario funcionario) {

        String sql = "INSERT INTO funcionario (id, nome, cpf, email, telefone, data_nascimento, tipo) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setDate(5, funcionario.getDataNascimento());
            stmt.setInt(6, funcionario.getCargo().getId());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException ex){
                throw new RuntimeException("Erro SQL", ex);
            }
        }

    public void atualizar(Funcionario funcionario) {
        try{
            String sql = "UPDATE funcionario SET nome=?, email=?, telefone=?, data_nascimento=?, tipo=? WHERE cpf=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setDate(5, funcionario.getDataNascimento());
            stmt.setInt(6, funcionario.getCargo().getId());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void excluir(String cpf) {
        try{
            String sql = "DELETE FROM funcionario WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public Funcionario buscarPorCpf(String cpf) {
        try{
            String sql = "SELECT * FROM funcionario WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Funcionario f = null;
            if (rs.next()) {
                f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNascimento(Date.valueOf(rs.getDate("data_nascimento").toLocalDate()));
                f.setCargo(Cargo.fromId(rs.getInt("tipo")));
                f.setFuncionarioId(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            return f;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public List<Funcionario> listarTodos() {
        try{
            String sql = "SELECT * FROM funcionario";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> lista = new ArrayList<>();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNascimento(Date.valueOf(rs.getDate("data_nascimento").toLocalDate()));
                f.setCargo(Cargo.fromId(rs.getInt("tipo")));
                f.setFuncionarioId(rs.getInt("id"));
                lista.add(f);
            }
            rs.close();
            stmt.close();
            return lista;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public Funcionario buscarPorId(int idFuncionario) {
        try{
            String sql = "SELECT * FROM funcionario WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            Funcionario f = null;
            if (rs.next()) {
                f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setDataNascimento(Date.valueOf(rs.getDate("data_nascimento").toLocalDate()));
                f.setCargo(Cargo.fromId(rs.getInt("tipo")));
                f.setFuncionarioId(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            return f;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }
}
