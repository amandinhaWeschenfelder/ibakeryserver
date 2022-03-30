/**
 * NOME: Luana Kuntz e Amanda Weschenfelder
 * TURMA: INF4AT
 * DATA: 23/02/2022
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.OperacaoDAO;
import model.ProdutoDAO;
import model.UsuarioDAO;
import model.ItensOperacaoDAO;
import modelDominio.ItensOperacao;
import modelDominio.Operacao;
import modelDominio.Produto;
import modelDominio.Usuario;

public class TrataCliente extends Thread {

    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final int idUnico;

    public TrataCliente(ObjectInputStream in, ObjectOutputStream out, int idUnico) {
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
        this.start();
    }

    public void run() {
        String comando;
        System.out.println("Esperando comandos do cliente!");
        try {
            comando = (String) in.readObject();
            while (!comando.equals("fim")) {
                System.out.println("Cliente " + idUnico + " enviou comando: " + comando);
                switch (comando) {
                    // USUÁRIO //                
                    case "InserirGerente": { // GERENTE
                        out.writeObject("ok");
                        Usuario gerente = (Usuario) in.readObject();
                        UsuarioDAO gerenteDao = new UsuarioDAO();
                        out.writeObject(gerenteDao.inserir(gerente));
                        break;
                    }
                    case "InserirUsuario": { // FUNCIONÁRIO OU CLIENTE
                        out.writeObject("ok");
                        Usuario usuario = (Usuario) in.readObject();
                        UsuarioDAO usuarioDao = new UsuarioDAO();
                        out.writeObject(usuarioDao.inserir(usuario));
                        break;
                    }                                        
                    case "UsuariosGetListaUsuarios": {
                        out.writeObject("ok");
                        UsuarioDAO usuarioDao = new UsuarioDAO();
                        ArrayList<Usuario> listaUsuarios = usuarioDao.getListaUsuarios();
                        out.writeObject(listaUsuarios);
                        break;
                    }
                    case "UsuariosGetListaUsuariosNome": {
                        out.writeObject("ok");
                        String nome = (String) in.readObject();
                        UsuarioDAO usuarioDao = new UsuarioDAO();
                        ArrayList<Usuario> listaUsuariosNome = usuarioDao.getListaUsuariosNome(nome);
                        out.writeObject(listaUsuariosNome);
                        break;
                    }
                    case "UsuariosGetListaUsuariosId": {
                        out.writeObject("ok");
                        int idUsuario = (int) in.readObject();
                        UsuarioDAO usuarioDao = new UsuarioDAO();
                        ArrayList<Usuario> listaUsuariosId = usuarioDao.getListaUsuariosId(idUsuario);
                        out.writeObject(listaUsuariosId);
                        break;
                    }
                    case "ValidaUsuario": {
                        out.writeObject("ok");
                        Usuario usuario = (Usuario) in.readObject();
                        UsuarioDAO usuarioDao = new UsuarioDAO();
                        out.writeObject(usuarioDao.validaLogin(usuario.getEmail(), usuario.getSenha()));
                        break;
                    }
                    // PRODUTO //                
                    case "InserirProduto": { 
                        out.writeObject("ok");                        
                        Produto produto = (Produto) in.readObject();
                        System.out.println(produto);
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        out.writeObject(produtoDao.inserir(produto));
                        break;
                    }
                    case "AtualizarProduto": { 
                        out.writeObject("ok");
                        Produto produto = (Produto) in.readObject();
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        out.writeObject(produtoDao.atualizar(produto));
                        break;
                    }
                    case "AtualizarProdutoProducao": { 
                        out.writeObject("ok");
                        Produto produto = (Produto) in.readObject();
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        out.writeObject(produtoDao.atualizarProducao(produto));
                        break;
                    }                    
                    case "ProdutoExcluir": {
                        out.writeObject("ok");
                        Produto produto = (Produto) in.readObject();
                        System.out.println(produto);
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        out.writeObject(produtoDao.excluir(produto));
                        break;
                    }
                    case "ProdutosGetListaProdutos": {
                        out.writeObject("ok");
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        ArrayList<Produto> listaProdutos = produtoDao.getListaProdutos();
                        out.writeObject(listaProdutos);
                        break;
                    }
                    case "ProdutosGetListaProdutosNome": {
                        out.writeObject("ok");
                        String nome = (String) in.readObject();
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        ArrayList<Produto> listaProdutosNome = produtoDao.getListaProdutosNome(nome);
                        out.writeObject(listaProdutosNome);
                        break;
                    }
                    case "ProdutosGetListaProdutosId": {
                        out.writeObject("ok");
                        int idProduto = (int) in.readObject();
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        ArrayList<Produto> listaProdutosId = produtoDao.getListaProdutosId(idProduto);
                        out.writeObject(listaProdutosId);
                        break;
                    }
                    case "ProdutosGetComboBoxProdutosProducao": {
                        out.writeObject("ok");
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        ArrayList<Produto> listaProdutos = produtoDao.getListaProdutosProducao();
                        out.writeObject(listaProdutos);
                        break;
                    }
                    case "ProdutosGetComboBoxProdutosVenda": {
                        out.writeObject("ok");
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        ArrayList<Produto> listaProdutos = produtoDao.getListaProdutosVenda();
                        out.writeObject(listaProdutos);
                        break;
                    }
                    case "InserirEncomenda": { 
                        out.writeObject("ok");
                        Operacao operacao = (Operacao) in.readObject();
                        OperacaoDAO operacaoDao = new OperacaoDAO();
                        out.writeObject(operacaoDao.inserirEncomenda(operacao));
                        break;
                    } 
//                    case "InserirVenda": { 
//                        out.writeObject("ok");
//                        Operacao operacao = (Operacao) in.readObject();
//                        OperacaoDAO operacaoDao = new OperacaoDAO();
//                        out.writeObject(operacaoDao.inserirCaixa(operacao));
//                        break;
//                    }    
//                    case "InserirItemOperacao": { 
//                        out.writeObject("ok");
//                        ItensOperacao itensOperacao = (ItensOperacao) in.readObject();
//                        ItensOperacaoDAO itensOperacaoDao = new ItensOperacaoDAO();
//                        out.writeObject(itensOperacaoDao.inserir(itensOperacao));
//                        break;
//                    }      
//                    case "ProdutoGetProduto": {
//                        out.writeObject("ok");
//                        Produto produto = (Produto) in.readObject();
//                        ProdutoDAO produtoDAO = new ProdutoDAO();
//                        out.writeObject(produtoDAO.getProdutoId(produto.getIdProduto()));
//                        break;
//                    }
//                    case "ItensOperacaoGetLista": {
//                        System.out.println("Recebi o pedido da lista!");
//                        out.writeObject("ok");
//                        int idItensOperacao = (int) in.readObject();
//                        ItensOperacaoDAO itensOperacaoDAO = new ItensOperacaoDAO();
//                        ArrayList<ItensOperacao> listaItensOperacao = itensOperacaoDAO.getListaItensOperacao(idItensOperacao);
//                        System.out.println("LIsta de itens a ser devolvida -> " + listaItensOperacao.size());
//                        out.writeObject(listaItensOperacao);
//                        break;
//                    }                      
                    default:
                        out.writeObject("nok");
                        break;
                }
                comando = (String) in.readObject();
            } // Fim while 
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao tratar cliente: " + e.getMessage());
        }

        try {
            System.out.println("Cliente " + idUnico + " finalizou a conexão");
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            System.out.println("Erro ao tratar clientes (fechamento):" + e.getMessage());
        }
    }
}
