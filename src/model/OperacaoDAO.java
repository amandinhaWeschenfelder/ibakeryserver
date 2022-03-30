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
import modelDominio.Operacao;

public class OperacaoDAO {

    private final Connection con;

    public OperacaoDAO() {
        this.con = Conector.getConnection();
    }

    public int inserirEncomenda(Operacao operacao) { //INSERIR ENCOMENDA
        PreparedStatement stmt = null;
        try {
            try {
                con.setAutoCommit(false);
                String sql = "INSERT INTO Operacao (dataRetirada, valorTotal, idsuario, tipoOperacao) VALUES (?,?,?,?);";
                stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1,new java.sql.Date(operacao.getDataRetirada().getTime()));
                stmt.setFloat(2, operacao.getValorTotal());
                stmt.setInt(3, operacao.getIdUsuario().getIdUsuario());
                stmt.setInt(4, operacao.getTipoOperacao());

                stmt.execute();
            
                con.commit();
                
                sql = "select LAST_INSERT_ID();";
                
                stmt = con.prepareStatement(sql);
                ResultSet res = stmt.executeQuery();
                if (res.next()) {
                    int idOperacao = res.getInt(1);
                    ItensOperacaoDAO itensOperacao = new ItensOperacaoDAO();
                    itensOperacao.inserir(operacao.getItensOperacao(), idOperacao);
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
    
    
}
