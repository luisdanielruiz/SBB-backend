package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class ChallengueI implements PyVOInterface{ 
    public static final int cantCols=5; 
 
    public static final int idChallengue = 0; 
    public static final int tipoChallengue = 1; 
    public static final int nombre = 2; 
    public static final int hits = 3; 
    public static final int time = 4; 
 
 
    public static final String fields[]={ 
        "idChallengue", 
        "tipoChallengue", 
        "nombre", 
        "hits", 
        "time"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "Challengue.idChallengue", 
        "Challengue.tipoChallengue", 
        "Challengue.nombre", 
        "Challengue.hits", 
        "Challengue.time"    }; 
    
    public static int pks[]={0}; 
    public static String tables="Challengue"; 
 
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
