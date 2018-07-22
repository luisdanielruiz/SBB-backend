package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class MensajesI implements PyVOInterface{ 
    public  final int cantCols=5; 
 
    public  final int idMensaje = 0; 
    public  final int idUser = 1; 
    public  final int mail = 2; 
    public  final int asunto = 3; 
    public  final int mensaje = 4; 
 
 
    public static final String fields[]={ 
        "idMensaje", 
        "idUser", 
        "mail", 
        "asunto", 
        "mensaje"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "mensajes.idMensaje", 
        "mensajes.idUser", 
        "mensajes.mail", 
        "mensajes.asunto", 
        "mensajes.mensaje"    }; 
    
    public static int pks[]={0}; 
    public static String tables="mensajes"; 
 
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
