package SistemaUsuario;

import java.sql.Connection;

public class MainConection {

    public static void main(String[] args) {

        try {

            Conexao conexao = new Conexao();

            Connection connection = conexao.conectar();

            System.out.println("Conectado ao MySQL com sucesso!");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}