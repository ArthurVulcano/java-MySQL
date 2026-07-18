package SistemaUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class Conexao {
    public static Connection conectar() throws Exception {
    Properties propriedades = new Properties();
    FileInputStream arquivo = new FileInputStream("");

    propriedades.load(arquivo);

    String url = propriedades.getProperty("url");
    String usuario = propriedades.getProperty("usuario");
    String senha = propriedades.getProperty("senha");

        return DriverManager.getConnection(
                url,
                usuario,
                senha
        );
    }
}