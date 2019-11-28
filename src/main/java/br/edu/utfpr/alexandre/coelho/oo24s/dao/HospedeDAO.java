package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Hospede;
import java.io.Serializable;

public class HospedeDAO extends GenericDao<Hospede, Long>{

    public HospedeDAO() {
        super(Hospede.class);
    }
    
}
