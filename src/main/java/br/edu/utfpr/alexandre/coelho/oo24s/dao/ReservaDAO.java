package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Query;

public class ReservaDAO extends GenericDao<Reserva, Long> {

    public ReservaDAO() {
        super(Reserva.class);
    }

    public Long getByNome(String nome) {
        Query query = em.createNativeQuery("SELECT r.id FROM Reserva r JOIN Cliente c ON(r.cliente_id = c.id) WHERE Nome = :nome");
        query.setParameter("nome", nome);
        BigInteger b1 = (BigInteger) query.getResultList().get(0);
        return b1.longValue();
    }

    public List<Object[]> getReservasMesData() {
        Query query = em.createNativeQuery("select extract(month from r.datareserva) mes, count(*) quantidade "
                + "from reserva r group by extract(month from r.datareserva)");
        return query.getResultList();
    }

    public List<Object[]> getValorDiariaMes() {
        Query query = em.createNativeQuery("select extract(month from datareserva) mes, sum(extract(day from (datasaida - dataentrada)*valordiaria)) total "
                + "from reserva group by extract(month from datareserva) order by extract(month from datareserva)");
        return query.getResultList();
    }
}
