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
package com.github.brunoabdon.fubina.dal;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import com.github.brunoabdon.fubina.Material;

import br.nom.abdon.dal.AbstractDao;

/**
 *
 * @author Bruno Abdon
 */
public class MaterialDao extends AbstractDao<Material,Integer>{

    private static final String MATERIAIS_POR_CODIGO_SQL = 
        "com.github.brunoabdon.fubina.MateriaisPorCodigos";
    
    public MaterialDao() {
        super(Material.class);
    }

    public List<Material> listar(
            final EntityManager em, 
            final Collection<Integer> codigosObras) {
        
        return 
            em.createNamedQuery(MATERIAIS_POR_CODIGO_SQL, Material.class)
                .setParameter("codigosObras", codigosObras)
                .getResultList();
    }
}
