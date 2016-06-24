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
package com.github.brunoabdon.fubina.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.github.brunoabdon.fubina.dal.MaterialDao;
import com.github.brunoabdon.fubina.sophia.client.SophiaWebClient;

import br.nom.abdon.dal.DalException;

import com.github.brunoabdon.fubina.FiltroMaterial;
import com.github.brunoabdon.fubina.Material;

/**
 *
 * @author Bruno Abdon
 */
public class FubinaSystem {

    private static final Logger LOGGER = 
        Logger.getLogger(FubinaSystem.class.getName());
    
    private final SophiaWebClient sophiaWebClient;
    private final MaterialDao materialDao;

    @PersistenceUnit(unitName = "fubina_peruni")
    protected EntityManagerFactory emf;
    
    public FubinaSystem() {
        this.sophiaWebClient = new SophiaWebClient();
        this.materialDao = new MaterialDao();
    }
    
    public Collection<Material> getMateriais(FiltroMaterial f) {
        
        final Collection<Material> materiais = new ArrayList<>();
        
        //os codigos que sao resultado da busca
        final Collection<Integer> codigosObras = 
            sophiaWebClient.getCodigosObras(f);
        
        final EntityManager entityManager = emf.createEntityManager();        
        
        try {
            
            //carrega os material desses codigos, que ja estiverem no cache
            final Collection<Material> materiaisCached = 
                materialDao.listar(entityManager, codigosObras);

            //a principio, esse é o resultado.
            materiais.addAll(materiaisCached);
            
            
            final Set<Integer> codigosObrasNotCached = 
                separaNotCached(materiaisCached, codigosObras);

            //pra cada um fora do cache
            for(Integer codigoObra : codigosObrasNotCached) {
                
                //consulta no sophiaweb
                final Material material = 
                    sophiaWebClient.getMaterial(codigoObra);

                //coloca no cache (ou falha silenciosamente
                this.criaSilenciosamente(entityManager, material);
                
                //adiciona na lista de resultados
                materiais.add(material);
            }
        } finally {
            entityManager.close();
        }
        return materiais;
    }

    private Set<Integer> separaNotCached(final Collection<Material> materiaisCached, final Collection<Integer> codigosObras) {
        //pega só os codigos dos que tavam no cache
        final Set<Integer> codigosCached =
                materiaisCached.stream()
                        .map(Material::getCodigoObra)
                        .collect(Collectors.toSet());
        //tira da lista original os que ja estavam no cache
        final Set<Integer> codigosObrasNotCached = asSet(codigosObras);
        codigosObrasNotCached.removeAll(codigosCached);
        return codigosObrasNotCached;
    }

    private void criaSilenciosamente(final EntityManager entityManager, final Material material) {
        try {
            materialDao.criar(entityManager, material);
        } catch (DalException ex) {
            LOGGER.log(
                Level.SEVERE, ex,
                () -> "não pude criar no cache: " + material);
        }
    }

    private Set<Integer> asSet(Collection<Integer> codigosObras) {
        return (codigosObras instanceof Set)
                ? (Set)codigosObras
                : new HashSet<>(codigosObras);
            
    }
}
