/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 16/02/2022
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Produto;

public class ProdutoDAO {
    
    private final Connection con;

    public ProdutoDAO() {
        this.con = Conector.getConnection();
    }

    public int inserir(Produto produto) { //INSERIR
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "INSERT INTO Produto (nome, precoCusto, valorVenda, estoque, tipoProduto) VALUES (?, ?, ?, ?, ?);";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setFloat(2, produto.getPrecoCusto());
                stmt.setFloat(3, produto.getValorVenda());
                stmt.setInt(4, produto.getEstoque());
                stmt.setInt(5, produto.getTipoProduto());
                
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

    public int atualizar(Produto produto) { // ATUALIZAR
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false); // aceita poder voltar no commit                
                String sql = "UPDATE Produto SET precoCusto = ?, valorVenda = ?, estoque = estoque + ? WHERE idProduto = ?;";                
                stmt = con.prepareStatement(sql);
                stmt.setFloat(1, produto.getPrecoCusto());
                stmt.setFloat(2, produto.getValorVenda());
                stmt.setInt(3, produto.getEstoque());
                stmt.setInt(4, produto.getIdProduto());

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
    
    public int atualizarProducao(Produto produto) { // ATUALIZAR
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false); // aceita poder voltar no commit                
                String sql = "UPDATE Produto SET estoque = estoque + ? WHERE idProduto = ?;";                
                stmt = con.prepareStatement(sql);
                stmt.setFloat(1, produto.getEstoque());
                stmt.setFloat(2, produto.getIdProduto());                

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
    
    public int excluir(Produto produto) {
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "DELETE FROM Produto WHERE idProduto = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, produto.getIdProduto());
                System.out.println(stmt);
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
    
    public ArrayList<Produto> getListaProdutosProducao() {  
        Statement stmt;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT upper(Produto.nome) AS nomeMaiusculo, Produto.* FROM Produto WHERE Produto.tipoProduto = 1;")) {
                while (res.next()) {
                    Produto produto = new Produto(res.getInt("idProduto"), res.getString("nomeMaiusculo"), res.getFloat("precoCusto"), res.getFloat("valorVenda"), res.getInt("estoque"));
                    listaProdutos.add(produto);
                }
            }
            stmt.close();
            con.close();
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Produto> getListaProdutosVenda() {  
        Statement stmt;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT upper(Produto.nome) AS nomeMaiusculo, Produto.* FROM Produto;")) {
                while (res.next()) {
                    Produto produto = new Produto(res.getInt("idProduto"), res.getString("nomeMaiusculo"), res.getFloat("precoCusto"), res.getFloat("valorVenda"), res.getInt("estoque"));
                    listaProdutos.add(produto);
                }
            }
            stmt.close();
            con.close();
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }

    public ArrayList<Produto> getListaProdutos() { // CONSULTAR LISTA 
        Statement stmt;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT upper(Produto.nome) AS nomeMaiusculo, Produto.* FROM Produto;")) {
                while (res.next()) {
                    Produto produto = new Produto(res.getInt("idProduto"), res.getString("nomeMaiusculo"), res.getFloat("precoCusto"), res.getFloat("valorVenda"), res.getInt("estoque"));
                    listaProdutos.add(produto);
                }
            }
            stmt.close();
            con.close();
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
       
    public ArrayList<Produto> getListaProdutosNome(String nome) {
        Statement stmt;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT idProduto, upper(Produto.nome) AS nomeMaiusculo FROM Produto WHERE Produto.nome LIKE '%"+nome+"%';")) {
                while (res.next()) {
                    Produto produto = new Produto(res.getInt("idProduto"), res.getString("nomeMaiusculo"));
                    listaProdutos.add(produto);
                }
            }
            stmt.close();
            con.close();
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Produto> getListaProdutosId(int idProduto) {
        Statement stmt;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT idProduto, upper(Produto.nome) AS nomeMaiusculo FROM Produto WHERE Produto.idProduto LIKE '%"+idProduto+"%';")) {
                while (res.next()) {
                    Produto produto = new Produto(res.getInt("idProduto"), res.getString("nome"));
                    listaProdutos.add(produto);
                }
            }
            stmt.close();
            con.close();
            return listaProdutos;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
    
    public Produto getProdutoId(int idProduto) {
        Statement stmt;
        try {
            Produto produto = new Produto();
            stmt = con.createStatement();
            try ( ResultSet res = stmt.executeQuery("SELECT idProduto, upper(Produto.nome) AS nomeMaiusculo, valorVenda FROM Produto WHERE Produto.idProduto LIKE '%"+idProduto+"%';")) { 
                while (res.next()) {
                    produto = new Produto(res.getInt("idProduto"), res.getString("nomeMaiusculo"), res.getFloat("valorVenda"));
                }
            }
            stmt.close();
            con.close();
            return produto;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
}
