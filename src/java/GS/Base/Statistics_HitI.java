package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class Statistics_HitI implements PyVOInterface{ 
    public static final int cantCols=2; 
 
    public static final int idHit = 0; 
    public static final int idStatistic = 1; 
 
 
    public static final String fields[]={ 
        "idHit", 
        "idStatistic"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER    }; 
    
    public static final String fieldsFullName[]={ 
        "Statistics_Hit.idHit", 
        "Statistics_Hit.idStatistic"    }; 
    
    public static int pks[]={0}; 
    public static String tables="Statistics_Hit"; 
 
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
