package com.dac.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Pedido implements Serializable {

    @Id
    private int id;

    @OneToMany
    private List<Produto> produtos;
    
    @ManyToOne
    private Cliente cliente;
    
    private TransactionCard transaction;
    
    private double valor = 500;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(Produto produto) {
        this.produtos.add(produto);
    }

    public void remove(Produto produto) {
        this.produtos.remove(produto);
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TransactionCard getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionCard transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", produtos=" + produtos + ", cliente=" + cliente + ", transaction=" + transaction + ", valor=" + valor + '}';
    }
}
