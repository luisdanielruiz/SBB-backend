/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS.Base;

import Py.DB.VO.PyVO;
import Py.DB.VO.PyVOInterface;

/**
 *
 * @author admin
 */
public class TesteoI implements PyVOInterface{
    int cantCols=3;
    
    int idTest=0;
    int nombre=1;
    int apellido=2;
    
    
    public static String fields[]={
        "idTest",
        "nombre",
        "apellido"
    };
    public static int types[]={
        PyVO.TYPE_NUMBER,
        PyVO.TYPE_TEXT,
        PyVO.TYPE_TEXT
    };
    public static String fieldsFullName[]={
        "testeo.idTest",
        "testeo.nombre",
        "testeo.apellido"
    };
    public static int pks[]={0};
    public static String tables="testeo";
    
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
