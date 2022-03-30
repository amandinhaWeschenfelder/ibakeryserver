/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Usuario;

public class UsuarioDAO {
    
    private final Connection con;

    public UsuarioDAO() {
        this.con = Conector.getConnection();
    }

    public int inserir(Usuario usuario) { //INSERIR 
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "INSERT INTO Usuario (nome, sobrenome, telefone, email, senha, cnpj, cpf, tipoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSobrenome());
                stmt.setString(3, usuario.getTelefone());
                stmt.setString(4, usuario.getEmail());
                stmt.setString(5, usuario.getSenha());
                stmt.setString(6, usuario.getCnpj());
                stmt.setString(7, usuario.getCpf());
                stmt.setInt(8, usuario.getTipoUsuario());                
                
                stmt.execute();
                con.commit();
                return -1;
            } catch (SQLException e) {
                try {
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    return ex.getErrorCode();
                }
            }
        } finally {
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                return e.getErrorCode();
            }
        }
    }
    
    public Usuario validaLogin(String email, String senha) {  
        String sql = "SELECT * FROM Usuario where email = ? AND senha = ?";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        
         try{
            conn = Conector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,senha);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setCnpj(resultSet.getString("cnpj"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setTipoUsuario(resultSet.getInt("tipoUsuario"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return usuario;
    }   

    public ArrayList<Usuario> getListaUsuarios() { // CONSULTAR LISTA
        Statement stmt;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT upper(Usuario.nome) AS nomeMaiusculo, Usuario.* FROM Usuario WHERE Usuario.tipoUsuario = 3;")) {
                while (res.next()) {
                    Usuario usuario = new Usuario(res.getInt("idUsuario"), res.getString("nomeMaiusculo"), res.getString("sobrenome"), res.getString("telefone"), res.getString("email"), res.getString("senha"), res.getString("cnpj"), res.getString("cpf"), res.getInt("tipoUsuario"));
                    listaUsuarios.add(usuario);
                }
            }
            stmt.close();
            con.close();
            return listaUsuarios;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }

    public ArrayList<Usuario> getListaUsuariosNome(String nome) {
        Statement stmt;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT idUsuario, upper(nome) AS nomeMaiusculo FROM Usuario WHERE Usuario.nome LIKE '%"+nome+"%';")) {
                while (res.next()) {
                    Usuario usuario = new Usuario(res.getInt("idUsuario"), res.getString("nomeMaiusculo"));
                    listaUsuarios.add(usuario);
                }
            }
            stmt.close();
            con.close();
            return listaUsuarios;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Usuario> getListaUsuariosId(int idUsuario) {
        Statement stmt;
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT idUsuario, upper(nome) AS nomeMaiusculo FROM Usuario WHERE Usuario.idUsuario LIKE '%"+idUsuario+"%';")) {
                while (res.next()) {
                    Usuario usuario = new Usuario(res.getInt("idUsuario"), res.getString("nomeMaiusculo"));
                    listaUsuarios.add(usuario);
                }
            }
            stmt.close();
            con.close();
            return listaUsuarios;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
}