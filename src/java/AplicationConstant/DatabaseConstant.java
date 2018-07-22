    /*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AplicationConstant;

import Entidades.EntidadVO;
import Entidades.Helpers.EntidadHLP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class DatabaseConstant {
   
    public static String localReportes="/var/lib/tomcat/webapps/contexto/";
    public static String generatedFiles="/var/lib/tomcat/webapps/contexto/";
    public static String wwwReportes="/var/lib/tomcat/webapps/contexto/";
    public static String www="http://localhost:8080/";
    
    public static String conexionDefault[]={
                              "com.mysql.jdbc.Driver",
                              "jdbc:mysql://localhost/SmartBoxingBag",// 
                             "root",
                              ""
                                }; 
    
     
    /*public static String conexionDefault[]={
                              "com.mysql.jdbc.Driver",
                              "jdbc:mysql://localhost/SmartBoxingBag",// 
                             "root",
                              "vPASYFtEQn4a"
                                };*/
  
    public static String urlImagenes="/var/lib/tomcat/webapps/contextoPlenus/";
    public static String webImagenes="/var/lib/tomcat/webapps/contextoPlenus/";
    
    public static String log ="";
    public static boolean getConection=false;
   
    public static SimpleDateFormat dfANMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat hfANMAT = new SimpleDateFormat("hh:mm"); 
    public static SimpleDateFormat dfUser = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dfDB = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dfReporteTraza = new SimpleDateFormat("yyMMdd");
    public static SimpleDateFormat dfEtiqueta = new SimpleDateFormat("yyMMdd");
    public static SimpleDateFormat dTimefDB = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static SimpleDateFormat dTimefUser = new SimpleDateFormat("dd/MM hh:mm");
    public static final DecimalFormat doublef = new DecimalFormat("#.##");
    
    public static String meses[] ={"","Enero","Febrero","Marzo","Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre","Octubre","Nomviembre","Diciembre"};
    public static SimpleDateFormat dia = new SimpleDateFormat("dd");
    public static SimpleDateFormat mes = new SimpleDateFormat("M");
    public static SimpleDateFormat ano = new SimpleDateFormat("yyyy");
    
    public static String getFechaLetras (Date d){
        System.out.print("ss"+d);
        String retorna = dia.format(d)+ " de "+ meses[Integer.parseInt(mes.format(d))]+" de "+ ano.format(d);
        
        return retorna;
    }
    
    
    public static void getDB() throws Exception {
        
       /* 
        try{
            if (!getConection){
                
            BufferedReader bf = new BufferedReader(new FileReader("C:/GResto/config.txt"));
     
            String nuevaConexion[]= new String[4];
                
            String linea="";
            linea = bf.readLine();
            nuevaConexion[0] =linea;
            linea = bf.readLine();
            nuevaConexion[1] = linea;
            linea = bf.readLine();
            nuevaConexion[2] = linea;
            linea = bf.readLine();
            nuevaConexion[3] = linea;
            
            //DatabaseConstant.conexionDefault = nuevaConexion;
                
            linea = bf.readLine();
            urlImagenes = linea;
            linea = bf.readLine();
            webImagenes = linea;
            
            }
        }catch(Exception ex){
            DatabaseConstant.log+= ex.getLocalizedMessage()+"\n<br>";
            ex.printStackTrace();
        }        */
    }
    
    
    

      private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
      public static Random rnd = new Random(); 
      
      public static String randomString(int tamSerie  ) { 
          int len = tamSerie;
          StringBuilder sb = new StringBuilder( len ); 
          for( int i = 0; i < len; i++ ) 
              sb.append( AB.charAt( rnd.nextInt(AB.length()) ) ); 
          return sb.toString(); 
      }
      
      
      
              
      public static String [] getGerarquiaLimpia(String str){
        String retorna[] = str.replaceAll(".000", "").split("\\.");
        
        return retorna;
      }
      
      
      public static String getGerarquiaLimpiaConsulta(String str){
        String retorna[] = getGerarquiaLimpia(str);
        String r ="";
        for (int i=0; i< retorna.length;i++){
            r+= retorna[i]+".";
        }

        
        return r;
      }
      
      
      
     public static String getCodAutoComplete(String txt){
        String spl[] = txt.split("\\(");
        
        return spl[spl.length-1].replaceAll("\\)", "");
    }
    
      

}