/**
 * NOME: Luana Kuntz e Amanda Weschenfelder
 * TURMA: INF4AT
 * DATA: 16/02/2022
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.ItensOperacao;
import modelDominio.Produto;

public class ItensOperacaoDAO {

    private final Connection con;

    public ItensOperacaoDAO() {
        this.con = Conector.getConnection();
    }

    public int inserir(ArrayList<ItensOperacao> itensOperacao, int idOperacao) { //INSERIR
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idItensOperacao = 0;
        try {
            try {
                for (int x = 0; x < itensOperacao.size(); x++) {
                    ItensOperacao itemOperacao = itensOperacao.get(x);
                    
                    con.setAutoCommit(false);
                    String sql = "INSERT INTO ItensOperacao (idProduto, qtdProdutos, valorItemProduto) VALUES (?,?,?,?);";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(1, idOperacao);
                    stmt.setInt(2, itemOperacao.getIdProduto().getIdProduto());
                    stmt.setInt(3, itemOperacao.getQtdProduto());
                    stmt.setFloat(4, itemOperacao.getValorItemProduto());

                    stmt.execute();
                    con.commit();                                       
                }                                
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
    
    

    public ArrayList<ItensOperacao> getListaItensOperacao(int idItensOperacao) {
        Statement stmt;
        ArrayList<ItensOperacao> listaItensOperacao = new ArrayList<>();
        try {
            stmt = con.createStatement();
            Produto produto = new Produto();
            try (ResultSet res = stmt.executeQuery("SELECT idProduto, qtdProdutos, valorItemProduto FROM ItensOperacao WHERE ItensOperacao.idItensOperacao = "+idItensOperacao+";")) {
                while (res.next()) {
                    try {
                        int idProduto = res.getInt("idProduto");
                        ResultSet result = stmt.executeQuery("SELECT upper(Produto.nome) AS nomeMaiusculo FROM Produto WHERE Produto.idProduto = "+idProduto+";");
                        while (result.next()) {
                            produto.setNome(res.getString("nomeMaiusculo"));
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getErrorCode() + "" + e.getMessage());
                        return null;
                    }
                    ItensOperacao itensOperacao = new ItensOperacao(produto, res.getInt("qtdProdutos"), res.getFloat("valorItemProduto"));                    
                    listaItensOperacao.add(itensOperacao);
                }
            }
            stmt.close();
            con.close();
            System.out.println("DAO lista: "+listaItensOperacao);
            return listaItensOperacao;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "" + e.getMessage());
            return null;
        }
    }
}
