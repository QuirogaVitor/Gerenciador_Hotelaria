package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/gerenciamento_hotel_lp3";
    private static final String USER = "postgres";
    private static final String PASSWORD = "teste";

    private Connection conexao;

    public DAOFactory(){
        try {
            this.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException ex) {
            throw new RuntimeException("Erro criando a conexão", ex);
        }
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return new FuncionarioDAO(conexao);
    }

    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(conexao);
    }

    public QuartoDAO getQuartoDAO() {
        return new QuartoDAO(conexao);
    }

    public ReservaDAO getReservaDAO() {
        return new ReservaDAO(conexao);
    }

    public UsuarioFuncionarioDAO getUsuarioFuncionarioDAO(){
        return new UsuarioFuncionarioDAO(conexao);
    }

    public UsuarioClienteDAO getUsuarioClienteDAO(){
        return new UsuarioClienteDAO(conexao);
    }


    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
