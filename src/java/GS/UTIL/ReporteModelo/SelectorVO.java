package GS.UTIL.ReporteModelo;

import AplicationConstant.DatabaseConstant;
import Atributo.AtributoDB;
import Constantes.DatosJAVA;
import Constantes.DatosSQL;

import Entidades.EntidadVO;
import Entidades.EntidadVO;


/**
 *
 * @author Administrator
 */
public class SelectorVO extends EntidadVO  {
   public static final String tabla = "reportemodelo";
   
   public static final String codigo = "codigo";
   public static final String descripcion = "descripcion";
   
   
   
   
   public SelectorVO ( ) throws Exception{
         super (
                    DatabaseConstant.conexionDefault[0],
                    DatabaseConstant.conexionDefault[1],
                    DatabaseConstant.conexionDefault[2],
                    DatabaseConstant.conexionDefault[3]
                );
                
        
        this.tables.add(tabla);
        
        this.PK   = new AtributoDB(codigo,DatosSQL.NUMBER, DatosJAVA.INT, tabla);
        this.atributos.put(descripcion,new AtributoDB(descripcion,DatosSQL.TEXT, DatosJAVA.STRING, tabla));
      
        
        
        
        
    }
   

   
   
    
    
}
