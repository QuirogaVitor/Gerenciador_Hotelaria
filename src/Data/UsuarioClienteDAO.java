package Data;
import Model.Cliente;
import Model.Usuario.UsuarioCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioClienteDAO {
    private Connection conexao;

    public UsuarioClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(UsuarioCliente usuarioCliente){
        try{
            String sql = "INSERT INTO usuario_cliente (id, usuario, senha, id_cliente) VALUES (DEFAULT, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuarioCliente.getUsuario());
            stmt.setString(2, usuarioCliente.getSenha());
            stmt.setInt(3, usuarioCliente.getCliente().getClienteId());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }
    }

    public UsuarioCliente buscarPorUsuario(String usuario){
        try{
            String sql = "SELECT * FROM usuario_cliente WHERE usuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            UsuarioCliente usuarioCliente = null;
            if (rs.next()) {
                usuarioCliente = new UsuarioCliente();
                usuarioCliente.setId(rs.getInt("id"));
                usuarioCliente.setUsuario(rs.getString("usuario"));
                usuarioCliente.setSenha(rs.getString("senha"));
                // Carregar o Cliente relacionado
                Cliente cliente = new ClienteDAO(conexao).buscarPorCpf(rs.getString("id_cliente"));
                usuarioCliente.setCliente(cliente);
            }
            rs.close();
            stmt.close();
            return usuarioCliente;
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void atualizar(UsuarioCliente usuarioCliente){
        try{
            String sql = "UPDATE usuario_cliente SET usuario=?, senha=?, id_cliente=? WHERE id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuarioCliente.getUsuario());
            stmt.setString(2, usuarioCliente.getSenha());
            stmt.setInt(3, usuarioCliente.getCliente().getClienteId());
            stmt.setInt(4, usuarioCliente.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }

    public void excluir(int id){
        try{
            String sql = "DELETE FROM usuario_cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            throw new RuntimeException("Erro SQL", ex);
        }

    }
}