package GS.Base; 
 
import Py.DB.VO.PyVO; 
import Py.DB.VO.PyVOInterface; 
 
/** 
 * 
 * @author admin 
 */ 
public class UserAppI implements PyVOInterface{ 
    public static final int cantCols=27; 
 
    public static final int idUser = 0; 
    public static final int userName = 1; 
    public static final int password = 2; 
    public static final int email = 3; 
    public static final int name = 4; 
    public static final int surname = 5; 
    public static final int country = 6; 
    public static final int state = 7; 
    public static final int city = 8; 
    public static final int address = 9; 
    public static final int postalCode = 10; 
    public static final int idFacebook = 11; 
    public static final int idGoogle = 12; 
    public static final int profilePicture = 13; 
    public static final int phone_cod = 14; 
    public static final int phone = 15; 
    public static final int status = 16; 
    public static final int axisY = 17; 
    public static final int axisX = 18; 
    public static final int location = 19; 
    public static final int mailConfirmado = 20; 
    public static final int dni = 21; 
    public static final int cuil = 22; 
    public static final int premium = 23; 
    public static final int fechaRegistro = 24; 
    public static final int sesionHistory = 25; 
    public static final int statistics = 26; 
 
 
    public static final String fields[]={ 
        "idUser", 
        "userName", 
        "password", 
        "email", 
        "name", 
        "surname", 
        "country", 
        "state", 
        "city", 
        "address", 
        "postalCode", 
        "idFacebook", 
        "idGoogle", 
        "profilePicture", 
        "phone_cod", 
        "phone", 
        "status", 
        "axisY", 
        "axisX", 
        "location", 
        "mailConfirmado", 
        "dni", 
        "cuil", 
        "premium", 
        "fechaRegistro", 
        "sesionHistory", 
        "statistics"    }; 
    
    public static final int types[]={ 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_NUMBER, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT, 
        PyVO.TYPE_TEXT    }; 
    
    public static final String fieldsFullName[]={ 
        "UserApp.idUser", 
        "UserApp.userName", 
        "UserApp.password", 
        "UserApp.email", 
        "UserApp.name", 
        "UserApp.surname", 
        "UserApp.country", 
        "UserApp.state", 
        "UserApp.city", 
        "UserApp.address", 
        "UserApp.postalCode", 
        "UserApp.idFacebook", 
        "UserApp.idGoogle", 
        "UserApp.profilePicture", 
        "UserApp.phone_cod", 
        "UserApp.phone", 
        "UserApp.status", 
        "UserApp.axisY", 
        "UserApp.axisX", 
        "UserApp.location", 
        "UserApp.mailConfirmado", 
        "UserApp.dni", 
        "UserApp.cuil", 
        "UserApp.premium", 
        "UserApp.fechaRegistro", 
        "UserApp.sesionHistory", 
        "UserApp.statistics"    }; 
    
    public static int pks[]={0}; 
    public static String tables="UserApp"; 
 
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
