package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class TipoChallengueI implements PyVOInterface{ 
    public static final int cantCols=2; 
 
    public static final int id = 0; 
    public static final int nombre = 1; 
 
 
    public static final String fields[]={ 
        "id", 
        "nombre"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "TipoChallengue.id", 
        "TipoChallengue.nombre"    }; 
    
    public static int pks[]={0}; 
    public static String tables="TipoChallengue"; 
 
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
