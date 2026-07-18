package SistemaUsuario;

import java.util.Scanner;

public class Interfacer {

    static void main() throws Exception {

        Scanner sm = new Scanner(System.in);

        Sistema sistema = new Sistema();

        int op = 0;

        while (op != 4) {

            System.out.println("\n1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Login");
            System.out.println("4 - Sair");

            System.out.print("Escolha: ");
            op = sm.nextInt();
            sm.nextLine();


            if (op == 1) {

                Usuario usuario = new Usuario();

                System.out.print("Nome: ");

                usuario.setNome(sm.nextLine());

                System.out.print("CPF: ");

                while (!usuario.setCpf(sm.nextLine())){

                    System.out.println("Digite um cpf valido");
                }



                System.out.print("Senha: ");

                while (!usuario.setSenha(sm.nextLine())) {

                    System.out.print("Digite uma senha válida novamente: ");

                }

                sistema.cadastrarUsuario(usuario);


            } else if (op == 2) {

                sistema.listarUsuarios();


            } else if (op == 3) {

                sistema.login();


            } else if (op == 4) {

                System.out.println("Saindo!");

            } else {

                System.out.println("Opção inválida!");

            }

        }

        sm.close();
    }
}
