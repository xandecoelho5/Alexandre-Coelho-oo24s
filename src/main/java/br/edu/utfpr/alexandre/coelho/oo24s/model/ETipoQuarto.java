package br.edu.utfpr.alexandre.coelho.oo24s.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum ETipoQuarto {
    ECONOMICO, SUPERIOR, LUXO;
    
    public ArrayList<ETipoQuarto> getTiposQuarto() {
        return new ArrayList<>(Arrays.asList(ETipoQuarto.values()));
    }
}


