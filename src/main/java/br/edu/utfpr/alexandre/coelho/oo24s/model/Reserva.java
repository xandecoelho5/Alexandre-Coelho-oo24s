package br.edu.utfpr.alexandre.coelho.oo24s.model;

import br.edu.utfpr.alexandre.coelho.oo24s.util.BooleanConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Reserva implements AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "quarto_id", referencedColumnName = "id")
    private Quarto quarto;

    @Column(nullable = false)
    private LocalDate dataReserva;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column(length = 40, nullable = false)
    private String motivo;

    @Column(nullable = false)
    private Double valorDiaria;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "Reserva_Hospedes")
    private List<Hospede> hospedes;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, updatable = false)
    private Usuario usuario;
    
    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'T'")
    private Boolean aberta;

    @OneToMany(mappedBy = "reserva", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)   
    private List<ReservaProdutos> reservaProdutos;

    @Transient
    private Double valorTotal;

    public List<ReservaProdutos> getReservaProdutos() {
        return reservaProdutos;
    }

    public void setReservaProdutos(List<ReservaProdutos> reservaProdutos) {
        this.reservaProdutos = reservaProdutos;
    }

    public Double getValorTotal() {
        return reservaProdutos.stream().mapToDouble(vp -> vp.getValor() * vp.getQuantidade()).sum();              
    }

    public Boolean getAberta() {
        return aberta;
    }

    public void setAberta(Boolean aberta) {
        this.aberta = aberta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Hospede> hospedes) {
        this.hospedes = hospedes;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
