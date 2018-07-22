package GS.UTIL.ReporteModelo;

import Atributo.AtributoDB;
import Constantes.DatosJAVA;
import Constantes.DatosSQL;
import AplicationConstant.DatabaseConstant;
import Entidades.EntidadVO;


public class TesteoVO extends EntidadVO  {
	public static final String tabla = "testeo";
   
   

	public static final String idTest ="idTest";
	public static final String nombre ="nombre";
	public static final String apellido ="apellido";
	
   
   
	public TesteoVO ( ) throws Exception{
		super (
                    DatabaseConstant.conexionDefault[0],
                    DatabaseConstant.conexionDefault[1],
                    DatabaseConstant.conexionDefault[2],
                    DatabaseConstant.conexionDefault[3]
                ) ;
				
		toEntidad();
	}
	public  TesteoVO (String Driver, String URL, String Usuario,String Pass ) throws Exception{
		super (Driver,URL,Usuario,Pass);
		toEntidad();
	}
	public  TesteoVO (String conexion[] ) throws Exception{
		super (conexion[0],conexion[1],conexion[2],conexion[3]);
		toEntidad();
	}
   
	public void toEntidad(){
		this.tables.add(tabla);
        
	
		this.PK=new AtributoDB( idTest,DatosSQL.NUMBER,DatosJAVA.INT,tabla);
		this.atributos.put(nombre ,new AtributoDB( nombre,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
		this.atributos.put(apellido ,new AtributoDB( apellido,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
        
	}

    
}

