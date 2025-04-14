package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConexaoBD {
        private static final String url = "jdbc:mysql://localhost:3306/hotelaria?useSSL=false&serverTimezone=UTC";
        private static final String usuario = "root";
        private static final String senha = "";

    public static Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);

        }catch(ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado: " + e.getMessage());
            
        }catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}
