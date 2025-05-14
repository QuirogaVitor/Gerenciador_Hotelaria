package DAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public void cadastrarUsuario(String nome, String senha, String cpf, String telefone, String dataNascimento, String tipo, String nomeUsuario) {
        String sql = "INSERT INTO usuarios (nome, cpf, telefone, data_nasc, senha, tipo, nome_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String senhaCriptografada = criptografarSenha(senha);

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, dataNascimento);
            stmt.setString(5, senhaCriptografada); 
            stmt.setString(6, tipo);
            stmt.setString(7, nomeUsuario);

            

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro na criptografia da senha");
        }
    }

    public String validarLogin(String nomeUsuario, String senha){

        String sql = "SELECT tipo FROM usuarios WHERE nome_usuario = ? AND senha = ?";

        try(Connection conn = ConexaoBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){

            String stringCriptografada = criptografarSenha(senha);
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, stringCriptografada);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                
                return rs.getString("tipo");
                
            }else{
                return null;
            }

        }catch (Exception e){

            System.out.println("Erro ao validar login: " + e.getMessage());
            return null;

        }
    }
}
