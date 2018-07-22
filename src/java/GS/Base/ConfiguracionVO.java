package GS.Base;

import AplicationConstant.DatabaseConstant;
import Atributo.AtributoDB;
import Constantes.DatosJAVA;
import Constantes.DatosSQL;

import Entidades.EntidadVO;
import Entidades.Helpers.EntidadHLP;


public class ConfiguracionVO extends EntidadVO  {
public static final String tabla = "configuration";
   
   

public static final String codigo ="cod";
public static final String descripcion ="descripcion";
public static final String dato ="datos";
    
   
 
   
public ConfiguracionVO ( ) throws Exception{
super (
                DatabaseConstant.conexionDefault[0],
                DatabaseConstant.conexionDefault[1],
                DatabaseConstant.conexionDefault[2],
                DatabaseConstant.conexionDefault[3]
                );
        
toEntidad();
}
public  ConfiguracionVO (String Driver, String URL, String Usuario,String Pass ) throws Exception{
super (Driver,URL,Usuario,Pass);
toEntidad();
}
   
public void toEntidad(){
this.tables.add(tabla);
        PK= new AtributoDB( codigo,DatosSQL.TEXT,DatosJAVA.STRING,tabla);
this.atributos.put(descripcion ,new AtributoDB( descripcion,DatosSQL.TEXT,DatosJAVA.STRING,tabla));
this.atributos.put(dato ,new AtributoDB( dato,DatosSQL.TEXT,DatosJAVA.STRING,tabla));
        
}

public static String numeracionInscripcion = "0";


public static String getNext (String id)throws Exception{
        String retorna="0";
        
            EntidadVO conf =  new ConfiguracionVO();
            conf.setValorPK(id);
            conf = EntidadHLP.getByPK(conf);

            retorna = (Integer.parseInt(conf.getValorAtributo(ConfiguracionVO.dato))+1)+"";
            conf.setValorAtributo(ConfiguracionVO.dato, retorna);

            EntidadHLP.doUpdatefromPK(conf);


        return retorna;
    }
    
}

