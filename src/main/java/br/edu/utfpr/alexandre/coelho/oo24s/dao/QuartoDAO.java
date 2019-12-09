package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import java.util.List;
import javax.persistence.Query;

public class QuartoDAO extends GenericDao<Quarto, Long>{

    public QuartoDAO() {
        super(Quarto.class);
    }
    
    public List<Quarto> getQuartosLivres(){
        Query query = em.createNativeQuery("select * from quarto where id not in (select quarto_id from reserva)");
//        Query query = em.createQuery("SELECT id, numero, qtdeCamas, qtdePessoas, tipo, valorDiaria "
//                                   + "FROM Quarto WHERE id NOT IN (SELECT r.quarto FROM Reserva r)");
        return query.getResultList();
    }
}
