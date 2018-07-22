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
public class ReporteModeloVO extends EntidadVO  {
   public static final String tabla = "reportemodelo";
   
   public static final String codigo = "codigo";
   public static final String descripcion = "descripcion";
   public static final String html = "html";
   public static final String textos = "textos";
   public static final String archivos = "archivos";
   public static final String descripTextos = "descripTextos";
   public static final String descripArchivos = "descripArchivos";
   public static final String tipo = "tipo";
   public static final String explicacion = "explicacion";
   
   
   
   public ReporteModeloVO ( ) throws Exception{
         super (
                    DatabaseConstant.conexionDefault[0],
                    DatabaseConstant.conexionDefault[1],
                    DatabaseConstant.conexionDefault[2],
                    DatabaseConstant.conexionDefault[3]
                );
                
        
        this.tables.add(tabla);
        
        this.PK   = new AtributoDB(codigo,DatosSQL.NUMBER, DatosJAVA.INT, tabla);
        this.atributos.put(descripcion,new AtributoDB(descripcion,DatosSQL.TEXT, DatosJAVA.STRING, tabla));
        this.atributos.put(html,new AtributoDB(html,DatosSQL.TEXT, DatosJAVA.STRING, tabla));

        this.atributos.put(textos,new AtributoDB(textos,DatosSQL.NUMBER, DatosJAVA.INT, tabla));
        this.atributos.put(archivos,new AtributoDB(archivos,DatosSQL.NUMBER, DatosJAVA.INT, tabla));
        this.atributos.put(tipo,new AtributoDB(tipo,DatosSQL.NUMBER, DatosJAVA.INT, tabla));


        this.atributos.put(descripArchivos,new AtributoDB(descripArchivos,DatosSQL.TEXT, DatosJAVA.STRING, tabla));
        this.atributos.put(descripTextos,new AtributoDB(descripTextos,DatosSQL.TEXT, DatosJAVA.STRING, tabla));
        this.atributos.put(explicacion,new AtributoDB(explicacion,DatosSQL.TEXT, DatosJAVA.STRING, tabla));
        
        
        
        
        
    }
   

   
   
    
    
}
