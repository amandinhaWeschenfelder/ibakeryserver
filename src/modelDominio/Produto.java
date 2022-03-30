/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package modelDominio;

import java.io.Serializable;

public class Produto implements Serializable {
    private int idProduto;
    private String nome;
    private float precoCusto;
    private float valorVenda;
    private int estoque;
    private int tipoProduto;
    private static final long serialVersionUID = 321;

    public Produto(String nome, float precoCusto, float valorVenda, int estoque, int tipoProduto) { // ADICIONAR PRODUTO
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.valorVenda = valorVenda;
        this.estoque = estoque;
        this.tipoProduto = tipoProduto;
    }
    
    public Produto(String nome, int estoque) {
        this.nome = nome;
        this.estoque = estoque;
    } 
    
    public Produto(int idProduto, int estoque) {
        this.idProduto = idProduto;
        this.estoque = estoque;
    }
    
    public Produto() {
    }
    
    public Produto(int idProduto, String nome, float valorVenda) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valorVenda = valorVenda;
    }

    public Produto(int idProduto, float precoCusto, float valorVenda, int estoque) { // ATUALIZAR PRODUTO
        this.idProduto = idProduto;
        this.precoCusto = precoCusto;
        this.valorVenda = valorVenda;
        this.estoque = estoque;
    }

    public Produto(int idProduto, String nome, float precoCusto, float valorVenda, int estoque) { // TELA formAtualizaProduto
        this.idProduto = idProduto;
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.valorVenda = valorVenda;
        this.estoque = estoque;
    }        
    
    public Produto(int idProduto, String nome) { // CONSULTAR LISTA
        this.idProduto = idProduto;
        this.nome = nome;
    }
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(int tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    @Override
    public String toString() {
        return "("+idProduto+") "+nome;
    }
        
}
