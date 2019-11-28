package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Reserva;
import java.io.Serializable;

public class ReservaDAO extends GenericDao<Reserva, Long>{

    public ReservaDAO() {
        super(Reserva.class);
    }
}
