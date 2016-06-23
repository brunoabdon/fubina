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
package com.github.brunoabdon.fubina.sophia.client;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.brunoabdon.fubina.FiltroSophiaWeb;
import com.github.brunoabdon.fubina.Material;
import pl.touk.throwing.ThrowingFunction;

import br.nom.abdon.rest.AbstractRestClient;
import br.nom.abdon.rest.RESTResponseException;

/**
 *
 * @author Bruno Abdon
 */
public class SophiaWebClient extends AbstractRestClient<Exception>{

    private static final String TARGET_URL = 
        "http://acervo.bn.br/sophia_web/asp/resultadoFrame.asp?modo_busca=combinada&content=resultado";

    
    private static ThrowingFunction<
                        RESTResponseException,
                        Response,
                        Exception> EXCEPTION_DEALER =
        e -> {
            if(e.getStatusInfo().getStatusCode() 
                == Response.Status.NOT_FOUND.getStatusCode()){
                throw new NotFoundException(e);
            }
            throw new Exception(e);
        };
    
    
    
    public SophiaWebClient() {
        super(
            MediaType.APPLICATION_FORM_URLENCODED_TYPE, 
            MediaType.APPLICATION_JSON_TYPE, 
            EXCEPTION_DEALER);
    }

    public List<Material> find(FiltroSophiaWeb f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
