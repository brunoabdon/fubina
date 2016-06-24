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
package com.github.brunoabdon.fubina.rest.server;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.github.brunoabdon.fubina.FiltroMaterial;
import com.github.brunoabdon.fubina.system.FubinaSystem;
import com.github.brunoabdon.fubina.Material;
import org.apache.commons.lang3.StringUtils;

import br.nom.abdon.rest.RestServiceUtils;

/**
 *
 * @author Bruno Abdon
 */
@Path("fubina")
@Produces({
    MediaType.APPLICATION_JSON,
    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
    "application/vnd.oasis.opendocument.spreadsheet"
})
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class FubinaService {
    
    private final FubinaSystem fubinaSystem;

    public FubinaService() {
        this.fubinaSystem = new FubinaSystem();
    }

    @POST
    public Response busca(
        final @Context Request request,
        final @Context HttpHeaders httpHeaders,
        final @FormParam("autor") String autor,
        final @FormParam("titulo") String titulo,
        final @FormParam("assunto") String assunto,
        final @FormParam("anoInicio") Integer anoInicio ,
        final @FormParam("anoFim") Integer anoFim){
        
        if(StringUtils.isBlank(autor)
            && StringUtils.isBlank(titulo)
            && StringUtils.isBlank(assunto)){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        final FiltroMaterial f = new FiltroMaterial();
        
        f.setAutor(autor);
        f.setAssunto(assunto);
        f.setTitulo(titulo);
        f.setAnoInicio(anoInicio);
        f.setAnoFim(anoFim);
        
        final Collection<Material> materiais = fubinaSystem.getMateriais(f);
       
        final GenericEntity<Collection<Material>> genericEntity =
                new GenericEntity<Collection<Material>>(materiais){};
        
        return RestServiceUtils
                .buildResponse(
                    request, 
                    httpHeaders, 
                    genericEntity);
        
    }
}
