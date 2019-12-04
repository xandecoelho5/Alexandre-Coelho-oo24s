package br.edu.utfpr.alexandre.coelho.oo24s.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quarto implements AbstractModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 6, nullable = false)
    private String numero;
    
    @Enumerated(EnumType.STRING)
    private ETipoQuarto tipo;
    
    @Column(nullable = false)
    private Integer qtdeCamas;
    
    @Column(nullable = false)
    private Integer qtdePessoas;
    
    @Column(nullable = false)
    private Double valorDiaria;
    
    @OneToMany(mappedBy = "quarto", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 1 reserva para varios hospedes 
    private List<Reserva> reservas;

    public Quarto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ETipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(ETipoQuarto tipo) {
        this.tipo = tipo;
    }

    public Integer getQtdeCamas() {
        return qtdeCamas;
    }

    public void setQtdeCamas(Integer qtdeCamas) {
        this.qtdeCamas = qtdeCamas;
    }

    public Integer getQtdePessoas() {
        return qtdePessoas;
    }

    public void setQtdePessoas(Integer qtdePessoas) {
        this.qtdePessoas = qtdePessoas;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quarto other = (Quarto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Quarto{" + "id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", qtdeCamas=" + qtdeCamas + ", qtdePessoas=" + qtdePessoas + ", valorDiaria=" + valorDiaria + '}';
    }
}
