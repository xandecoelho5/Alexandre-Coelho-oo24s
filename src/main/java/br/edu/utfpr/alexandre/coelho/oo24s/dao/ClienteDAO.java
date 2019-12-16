package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import java.util.List;
import javax.persistence.Query;

public class ClienteDAO extends GenericDao<Cliente, Long>{

    public ClienteDAO() {
        super(Cliente.class);
    }
    
    public List<Cliente> getClientesComReserva(){
        Query query = em.createNativeQuery("select * from cliente where id in (select cliente_id from reserva where aberta = 'T')", Cliente.class);
        return query.getResultList();
    }
    
    public List<Cliente> getClientesSemReserva() {
        Query query = em.createNativeQuery("select * from cliente where id not in (select cliente_id from reserva)", Cliente.class);
        return query.getResultList();
    }
}
