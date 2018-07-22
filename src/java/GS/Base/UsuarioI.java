package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class UsuarioI implements PyVOInterface{ 
    public  int cantCols=7; 
 
    public  int user = 0; 
    public  int password = 1; 
    public  int mail = 2; 
    public  int idPerfil = 3; 
    public  int estado = 4; 
    public  int recibeMail = 5; 
    public  int codigoTablet = 6; 
 
 
    public static final String fields[]={ 
        "user", 
        "password", 
        "mail", 
        "idPerfil", 
        "estado", 
        "recibeMail", 
        "codigoTablet"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER    }; 
    
    public static final String fieldsFullName[]={ 
        "usuario.user", 
        "usuario.password", 
        "usuario.mail", 
        "usuario.idPerfil", 
        "usuario.estado", 
        "usuario.recibeMail", 
        "usuario.codigoTablet"    }; 
    
    public static int pks[]={0}; 
    public static String tables="usuario"; 
 
    public static int orderBy[]=new int[0];//posicion de las fields 
    public static int orderByAD[]=new int[0];//postivo es acendente,Negativo decendente 
 
 
    public String[] getFields(){ 
        return fields; 
    }; 
    public int[] getTypes(){ 
        return types; 
    } 
    public String[] getFieldsFullName(){ 
        return fieldsFullName; 
    } 
    public int[] getPks(){ 
        return pks; 
    } 
    public int[] getOrderBy(){ 
        return orderBy; 
    } 
    public int[] getOrderByAD(){ 
        return orderByAD; 
    } 
    public String getTables(){ 
        return tables; 
    } 
 
}
