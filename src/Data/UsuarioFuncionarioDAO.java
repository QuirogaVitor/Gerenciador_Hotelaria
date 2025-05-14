package Data;

import java.sql.*;

import Model.Funcionarios.Funcionario;
import Model.Usuario.*;

public class UsuarioFuncionarioDAO {
    private Connection conexao;

    public UsuarioFuncionarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(UsuarioFuncionario usuarioFuncionario){
        try {
            String sql = "INSERT INTO usuario_funcionario (id, usuario, senha, id_funcionario) VALUES (DEFAULT, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuarioFuncionario.getUsuario());
            stmt.setString(2, usuarioFuncionario.getSenha());
            stmt.setInt(3, usuarioFuncionario.getFuncionario().getFuncionarioId());
            stmt.executeUpdate();
            stmt.close();
        }
        catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public UsuarioFuncionario buscarPorId(int id){
        try{
            String sql = "SELECT * FROM usuario_funcionario WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            UsuarioFuncionario usuarioFuncionario = null;
            if (rs.next()) {
                usuarioFuncionario = new UsuarioFuncionario();
                usuarioFuncionario.setId(rs.getInt("id"));
                usuarioFuncionario.setUsuario(rs.getString("usuario"));
                usuarioFuncionario.setSenha(rs.getString("senha"));
                // Carregar o Funcionario relacionado
                Funcionario funcionario = new FuncionarioDAO(conexao).buscarPorId(rs.getInt("id_funcionario"));
                usuarioFuncionario.setFuncionario(funcionario);
            }
            rs.close();
            stmt.close();
            return usuarioFuncionario;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public UsuarioFuncionario buscarPorUsuario(String usuario){
        try{
            String sql = "SELECT * FROM usuario_funcionario WHERE usuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            UsuarioFuncionario usuarioFuncionario = null;
            if (rs.next()) {
                usuarioFuncionario = new UsuarioFuncionario();
                usuarioFuncionario.setId(rs.getInt("id"));
                usuarioFuncionario.setUsuario(rs.getString("usuario"));
                usuarioFuncionario.setSenha(rs.getString("senha"));
                // Carregar o Funcionario relacionado
                Funcionario funcionario = new FuncionarioDAO(conexao).buscarPorId(rs.getInt("id_funcionario"));
                usuarioFuncionario.setFuncionario(funcionario);
            }
            rs.close();
            stmt.close();
            return usuarioFuncionario;
        }catch(SQLException ex) {
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public void atualizar(UsuarioFuncionario usuarioFuncionario){
        try{
            String sql = "UPDATE usuario_funcionario SET usuario=?, senha=?, id_funcionario=? WHERE id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuarioFuncionario.getUsuario());
            stmt.setString(2, usuarioFuncionario.getSenha());
            stmt.setInt(3, usuarioFuncionario.getFuncionario().getFuncionarioId());
            stmt.setInt(4, usuarioFuncionario.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void excluir(int id){
        try{
            String sql = "DELETE FROM usuario_funcionario WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }
}
