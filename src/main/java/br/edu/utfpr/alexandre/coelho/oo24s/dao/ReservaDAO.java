package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;

public class ReservaDAO extends GenericDao<Reserva, Long>{

    public ReservaDAO() {
        super(Reserva.class);
    }
}
