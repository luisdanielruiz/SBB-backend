package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class ContenidoI implements PyVOInterface{ 
    int cantCols=11; 
 
    int id = 0; 
    int modelo = 1; 
    int seccion = 2; 
    int subcontenido_id = 3; 
    int orden = 4; 
    int descripcion = 5; 
    int seo = 6; 
    int url = 7; 
    int textos = 8; 
    int archivos = 9; 
    int activo = 10; 
 
 
    public static String fields[]={ 
        "id", 
        "modelo", 
        "seccion", 
        "subcontenido_id", 
        "orden", 
        "descripcion", 
        "seo", 
        "url", 
        "textos", 
        "archivos", 
        "activo"    }; 
    
    public static int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static String fieldsFullName[]={ 
        "contenido.id", 
        "contenido.modelo", 
        "contenido.seccion", 
        "contenido.subcontenido_id", 
        "contenido.orden", 
        "contenido.descripcion", 
        "contenido.seo", 
        "contenido.url", 
        "contenido.textos", 
        "contenido.archivos", 
        "contenido.activo"    }; 
    
    public static int pks[]={0}; 
    public static String tables="contenido"; 
 
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
