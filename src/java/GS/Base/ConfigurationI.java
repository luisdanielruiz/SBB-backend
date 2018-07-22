package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class ConfigurationI implements PyVOInterface{ 
    public static final int cantCols=3; 
 
    public static final int cod = 0; 
    public static final int descripcion = 1; 
    public static final int datos = 2; 
 
 
    public static final String fields[]={ 
        "cod", 
        "descripcion", 
        "datos"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "configuration.cod", 
        "configuration.descripcion", 
        "configuration.datos"    }; 
    
    public static int pks[]={0}; 
    public static String tables="configuration"; 
 
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
