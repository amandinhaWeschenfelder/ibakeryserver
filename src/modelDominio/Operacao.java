/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Operacao implements Serializable {
    private int idOperacao;
    private Date dataRetirada;
    private float valorTotal;
    private Usuario idUsuario;
    private int tipoOperacao;
    private ArrayList<ItensOperacao> itensOperacao;
    private static final long serialVersionUID = 321;

    public Operacao(float valorTotal, Usuario idUsuario, int tipoOperacao) {
        this.valorTotal = valorTotal;
        this.idUsuario = idUsuario;
        this.tipoOperacao = tipoOperacao;
    }
    
    public Operacao(int idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Operacao(Date dataRetirada, float valorTotal, Usuario idUsuario, int tipoOperacao) {
        this.dataRetirada = dataRetirada;
        this.valorTotal = valorTotal;
        this.idUsuario = idUsuario;
        this.tipoOperacao = tipoOperacao;
    }
    
    public int getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(int idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<ItensOperacao> getItensOperacao() {
        return itensOperacao;
    }

    public void setItensOperacao(ArrayList<ItensOperacao> itensOperacao) {
        this.itensOperacao = itensOperacao;
    }       

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(int tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }
    
    
}
