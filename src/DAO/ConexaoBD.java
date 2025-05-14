package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConexaoBD {
        private static final String url = "jdbc:postgresql://localhost:5432/postgres";
        private static final String usuario = "postgres";
        private static final String senha = "teste";

    public static Connection conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, usuario, senha);

        }catch(ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage());
            
        }catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}


