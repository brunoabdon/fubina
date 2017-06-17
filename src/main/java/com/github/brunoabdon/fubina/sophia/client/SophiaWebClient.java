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


import java.util.ArrayList;
import java.util.Collection;

import com.github.brunoabdon.fubina.FiltroMaterial;
import com.github.brunoabdon.fubina.Material;

/**
 *
 * @author Bruno Abdon
 */
public class SophiaWebClient {

    private static final String TARGET_URL = 
        "http://acervo.bn.br/sophia_web/asp/resultadoFrame.asp?modo_busca=combinada&content=resultado";
    
    public Collection<Integer> getCodigosObras(final FiltroMaterial f){
        
        final Collection<Integer> codigosObras = new ArrayList<>();
        
      
        //ver como fazer sobre paginacao
        //a pagina de busca ja tem todos os ids. nao precisa navegar
        //mas tem um limite maximo de 1000 resultados (em 84 paginas)
        
        //monta body
        //posta
        //pega stream
        //faz um sacaner :  //Scanner s = new Scanner(stream);
        //pra cada match: String nextMatch = s.findWithinHorizon(yourPattern, 0);
            //extrai o codigo da obra*
            //salva na lista de codigos
        //verifica se teve 1000 elementos (ve um texto de aviso na pag)
          //se sim, é ruim faltou gente
          //se nao, tá completo
          
          //* assim que e extrai: nessas linhas no topo:
          
            //parent.hiddenFrame.vetor_pag[1].push('[1]528447.1, 529550.1, 532981.1, 465040.1, 355065.1, 526245.1, 488161.1, 421818.1, 534976.1, 519362.1, 396578.1, 445345.1');
            //                                       ^ ^         ^         ^ 
            //                                     pag mat1      mat2      mat3 ... sem os ".1"
        
        
        return codigosObras;

    }
    
    public Material getMaterial(final Integer codigoObra){
        return null;
    }
    
}
