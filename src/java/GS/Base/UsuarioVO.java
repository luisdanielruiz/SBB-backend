package GS.Base;

import AplicationConstant.DatabaseConstant;
import Atributo.AtributoDB;
import Constantes.DatosJAVA;
import Constantes.DatosSQL;

import Entidades.EntidadVO;


public class UsuarioVO extends EntidadVO  {
	public static final String tabla = "usuario";
	public static final String usuario ="user";
	public static final String Password ="password";
	public static final String Mail ="mail";
	public static final String idPerfil ="idPerfil";
	public static final String estado ="estado";
	public static final String recibeMail ="recibeMail";
        public static final String CodigosActividadesWeb ="CodigosActividadesWeb";
	//public static final String codigoTablet ="codigoTablet";
    
	public UsuarioVO ( ) throws Exception{
		super (DatabaseConstant.conexionDefault[0],DatabaseConstant.conexionDefault[1],DatabaseConstant.conexionDefault[2],DatabaseConstant.conexionDefault[3]);
        
		toEntidad();
	}
	public  UsuarioVO (String Driver, String URL, String Usuario,String Pass ) throws Exception{
		super (Driver,URL,Usuario,Pass);
		toEntidad();
	}
	public  UsuarioVO (String conexion[] ) throws Exception{
		super (conexion[0],conexion[1],conexion[2],conexion[3]);
		toEntidad();
	}
   
	public void toEntidad(){
		this.tables.add(tabla);
        

		this.PK=new AtributoDB( usuario,DatosSQL.TEXT,DatosJAVA.STRING,tabla);
		this.atributos.put(Password ,new AtributoDB( Password,DatosSQL.TEXT,DatosJAVA.STRING,tabla));
		this.atributos.put(Mail ,new AtributoDB( Mail,DatosSQL.TEXT,DatosJAVA.STRING,tabla));
		this.atributos.put(idPerfil ,new AtributoDB( idPerfil,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
		this.atributos.put(estado ,new AtributoDB( estado,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
		this.atributos.put(recibeMail ,new AtributoDB( recibeMail,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
		//this.atributos.put(codigoTablet ,new AtributoDB( codigoTablet,DatosSQL.NUMBER,DatosJAVA.INT,tabla));
        
	}

    
}

