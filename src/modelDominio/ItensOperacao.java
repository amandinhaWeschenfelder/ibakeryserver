/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package modelDominio;

import java.io.Serializable;

public class ItensOperacao implements Serializable {
    private int idItensOperacao;
    private Operacao idOperacao;
    private Produto idProduto;
    private int qtdProduto;
    private float valorItemProduto;
    private static final long serialVersionUID = 321;

    public ItensOperacao(Operacao idOperacao, Produto idProduto, int qtdProduto, float valorItemProduto) {
        this.idOperacao = idOperacao;
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
        this.valorItemProduto = valorItemProduto;
    }

    public ItensOperacao(Produto idProduto, int qtdProduto, float valorItemProduto) {
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
        this.valorItemProduto = valorItemProduto;
    }
         
    public int getIdItensOperacao() {
        return idItensOperacao;
    }

    public void setIdItensOperacao(int idItensOperacao) {
        this.idItensOperacao = idItensOperacao;
    }

    public Operacao getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(Operacao idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public float getValorItemProduto() {
        return valorItemProduto;
    }

    public void setValorItemProduto(float valorItemProduto) {
        this.valorItemProduto = valorItemProduto;
    }
    
    
}
