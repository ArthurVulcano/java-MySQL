package SistemaUsuario;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class Sistema {



    public void cadastrarUsuario(Usuario usuario) throws Exception {

        if (cpfExiste(usuario.getCpf())) {

            System.out.println("CPF já cadastrado!");
            return;

        }

        if (usuario.getSenha() == null) {
            System.out.println("Usuário inválido!");
            return;
        }

        Connection connection = Conexao.conectar();

        String sql = """
            INSERT INTO Usuario(nome, cpf, senha)
            VALUES (?, ?, ?)
           """;



        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getCpf());
        stmt.setString(3, HashSenha.gerarHash(usuario.getSenha()));
        stmt.executeUpdate();


        stmt.close();
        connection.close();

        System.out.println("Usuário cadastrado!");
}



    public void listarUsuarios() throws Exception{
        Connection connection = Conexao.conectar();
        String sql = "SELECT nome, cpf FROM Usuario";
        PreparedStatement stmt = connection.prepareStatement(sql);

        var resultado = stmt.executeQuery();
        while (resultado.next()) {

            System.out.println("== Usuário Cadastrado ==");
            System.out.println("Nome: " + resultado.getString("Nome"));
            System.out.println("CPF: " + resultado.getString("cpf"));
            System.out.println("----------------");
        }
        resultado.close();
        stmt.close();
        connection.close();

    }

    public boolean cpfExiste(String cpf) throws Exception {

        Connection connection = Conexao.conectar();

        String sql = """
            SELECT cpf \s
            FROM Usuario
            WHERE cpf = ?
            \s""";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, cpf);

        var resultado = stmt.executeQuery();

        boolean existe = resultado.next();

        resultado.close();
        stmt.close();
        connection.close();

        return existe;
    }


    public void login() throws Exception {
        Scanner sm = new Scanner(System.in);

        int tentativas = 3;

        while (tentativas > 0) {
            System.out.print("Digite seu CPF: ");
            String cpf = sm.nextLine();

            System.out.print("Digite sua senha: ");
            String senha = sm.nextLine();

            String senhaHash = HashSenha.gerarHash(senha);

            Connection connection = Conexao.conectar();

            String sql = """
                SELECT Nome
                FROM Usuario
                WHERE cpf = ? AND senha = ?
                """;

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cpf);
            stmt.setString(2, senhaHash);

            var resultado = stmt.executeQuery();

            if (resultado.next()) {

                System.out.println(
                        "Login realizado! Bem-vindo: "
                                + resultado.getString("Nome")
                );

                resultado.close();
                stmt.close();
                connection.close();

                return;
            }
            tentativas--;

            System.out.println(
                    "CPF ou senha incorretos!"
            );
            System.out.println(
                    "Tentativas restantes: " + tentativas
            );

            resultado.close();
            stmt.close();
            connection.close();
        }

        System.out.println("Usuário bloqueado por excesso de tentativas!");
    }
}