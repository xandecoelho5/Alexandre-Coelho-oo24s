package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.ReservaProdutos;
import java.util.List;
import javax.persistence.Query;

public class ReservaProdutosDAO extends GenericDao<ReservaProdutos, Long> {

    public ReservaProdutosDAO() {
        super(ReservaProdutos.class);
    }

    public ReservaProdutos getRPbyProdId(Long idp, Long idr) {
        Query query = em.createNativeQuery("select r.id from reservaprodutos r join produtos p on(r.produtos_id = p.id) "
                + "where (r.produtos_id = :idp) and (r.reserva_id = :idr)", ReservaProdutos.class);
        query.setParameter("idp", idp);
        query.setParameter("idr", idr);
        return (ReservaProdutos) query.getSingleResult();
    }

    public List<ReservaProdutos> getAllByReservaId(Long id) {
        Query query = em.createNativeQuery("select * from reservaprodutos where reserva_id = :id", ReservaProdutos.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Object[]> getValorProdutosMes() {
        Query query = em.createNativeQuery("select extract(month from datareserva) mes, coalesce(sum(valor * quantidade),0) total "
                + "from reservaprodutos right join reserva on(reserva.id = reservaprodutos.reserva_id) group by extract(month from datareserva) order by extract(month from datareserva)");
        return query.getResultList();
    }
}
