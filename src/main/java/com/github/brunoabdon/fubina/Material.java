/*
 * Copyright (C) 2016 Bruno Abdon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.brunoabdon.fubina;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import br.nom.abdon.modelo.EntidadeBaseInt;

/**
 *
 * @author Bruno Abdon
 */
@Entity
public class Material extends EntidadeBaseInt{
    
    //ex: Kafka, Franz, 1883-1924 	
    @Column(length = 500, nullable = true, unique = false)
    private String entPrinc;
    
    //ex: 
    @Column(length = 1000, nullable = true, unique = false)
    private String titulo;
    
    //ex: São Paulo : Círculo do Livro, c1995. 
    @Column(length = 500, nullable = true, unique = false)
    private String imprenta;
    
    //parser depois?
    //ex: 279p. ; 17 cm. 
    @Column(length = 100, nullable = true, unique = false)
    private String descFisica;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> assuntos;
    
    public Integer getCodigoObra() {
        return super.getId();
    }

    public void setCodigoObra(Integer codigoObra){
        super.setId(codigoObra);
    }
    
    public String getEntPrinc() {
        return entPrinc;
    }

    public void setEntPrinc(String entPrinc) {
        this.entPrinc = entPrinc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImprenta() {
        return imprenta;
    }

    public void setImprenta(String imprenta) {
        this.imprenta = imprenta;
    }

    public String getDescFisica() {
        return descFisica;
    }

    public void setDescFisica(String descFisica) {
        this.descFisica = descFisica;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }
}