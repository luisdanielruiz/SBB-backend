package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class StatisticsI implements PyVOInterface{ 
    public static final int cantCols=6; 
 
    public static final int id = 0; 
    public static final int user = 1; 
    public static final int challengue = 2; 
    public static final int tipoChallengue = 3; 
    public static final int time = 4; 
    public static final int hits = 5; 
 
 
    public static final String fields[]={ 
        "id", 
        "user", 
        "challengue", 
        "tipoChallengue", 
        "time", 
        "hits"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "Statistics.id", 
        "Statistics.user", 
        "Statistics.challengue", 
        "Statistics.tipoChallengue", 
        "Statistics.time", 
        "Statistics.hits"    }; 
    
    public static int pks[]={0}; 
    public static String tables="Statistics"; 
 
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
