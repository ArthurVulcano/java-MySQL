package SistemaUsuario;
public class Usuario {

    private String cpf;
    private String senha;
    private String nome;

    public boolean setCpf(String cpf) {


        if(!cpf.matches("\\d{11}")) {
            return false;


        }
        this.cpf = cpf;


        return true;
    }


    public void setNome(String nome) {
        if ( !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            System.out.println("Nome invalido!");
            return;
        }
        this.nome = nome;
    }


    public boolean setSenha(String senha) {

        if (!senha.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
            return false;
        }

        this.senha = senha;
        return true;
    }

    //getters feitos para verificação de Usuario no login! favor não mudar...
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

}