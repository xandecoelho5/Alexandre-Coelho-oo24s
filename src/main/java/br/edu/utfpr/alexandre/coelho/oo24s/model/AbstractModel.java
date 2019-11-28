package br.edu.utfpr.alexandre.coelho.oo24s.model;

import java.io.Serializable;

public interface AbstractModel <ID extends  Serializable> extends Serializable{    
    ID getId();
}
