package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Quarto;
import java.io.Serializable;

public class QuartoDAO extends GenericDao<Quarto, Long>{

    public QuartoDAO() {
        super(Quarto.class);
    }
}
