package br.edu.utfpr.alexandre.coelho.oo24s.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReservaProdutos implements AbstractModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private int quantidade;
    
    @Column(nullable = false)
    private Double valor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produtos_id", referencedColumnName = "id")//, nullable = true
    private Produtos produtos;
    
    @ManyToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    private Reserva reserva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "ReservaProdutos{" + "id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + ", produtos=" + produtos + ", reserva=" + reserva + '}';
    } 
    
}
