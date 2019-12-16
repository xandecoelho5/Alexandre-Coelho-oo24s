package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import java.util.List;
import javax.persistence.Query;

public class HospedeDAO extends GenericDao<Hospede, Long>{

    public HospedeDAO() {
        super(Hospede.class);
    }
    
    public List<Hospede> getHSemReserva(){
        Query query = em.createNativeQuery("select * from hospede where id not in(select hospedes_id from reserva_hospedes)", Hospede.class);
        return query.getResultList();
    }
}
