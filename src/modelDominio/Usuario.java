/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String cnpj;
    private String cpf;
    private int tipoUsuario;
    private static final long serialVersionUID = 321;


    public Usuario(String nome, String sobrenome, String telefone, String email, String senha, String cnpj, String cpf, int tipoUsuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }    
    
    public Usuario(int idUsuario, String nome, String sobrenome, String telefone, String email, String senha, String cnpj, String cpf, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }        
    
    public Usuario() {        
    }
    
    public Usuario(int idUsuario, String nome) { // PARA PESQUISAR NA LISTA
        this.idUsuario = idUsuario;
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
