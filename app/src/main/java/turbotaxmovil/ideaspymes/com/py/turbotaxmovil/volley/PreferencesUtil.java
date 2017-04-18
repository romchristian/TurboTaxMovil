package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.volley;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by christian.romero on 14/04/2016.
 */
public class PreferencesUtil {

    public final static String PREF_USER = "usuarioLogueado";
    public final static String PREF_GLOBAL = "PREF_GLOBAL";
    public final static String VENDEDOR_UUID = "VENDEDOR_UUID";
    public final static String FECHA_CARGA= "FECHA_CARGA";
    public final static String ULTIMO_NRO_FACTURA = "ULTIMO_NRO_FACTURA";
    public final static String ULTIMO_ID_FACTURA = "ULTIMO_ID_FACTURA";
    public final static String ULTIMO_NRO_COBRANZA = "ULTIMO_NRO_COBRANZA";
    public final static String CAMBIO_VENDEDOR = "CAMBIO_VENDEDOR";
    public final static String TELEFONO_ID = "TELEFONO_ID";
    public final static String URL_SERVICIO = "URL_SERVICIO";
    public final static String URL_LOGIN = "URL_LOGIN";
    public final static String PREF_DISPOSITIVO_REGISTRADO = "registrado";
    public final static String ADMIN_USER = "ADMIN_USER";
    public final static String DEVICE_ADDRESS = "Device_Address";
    public final static String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ssZ";
    public final static String HAY_CONEXION = "estado conexion";



    public static void putUrlServicio(Context context,String url){
        putString(context,URL_SERVICIO,url);
    }

    public static String getUrlServicio(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getString(URL_SERVICIO,ServidorURL.PREF_URL);
        }else {
            return ServidorURL.PREF_URL;
        }

    }


    public static void putUrlLogin(Context context,String url){
        putString(context,URL_LOGIN,url);
    }

    public static String getUrlLogin(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getString(URL_LOGIN,ServidorURL.PREF_URL_NO_AUTH);
        }else {
            return ServidorURL.PREF_URL_NO_AUTH;
        }

    }



    public static void putTelefonoId(Context context,String telefonoId){
        putString(context,TELEFONO_ID,telefonoId);
    }

    public static String getTelefonoId(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getString(TELEFONO_ID,"");
        }else {
            return "12345";
        }


    }


    public static void putUltimaFechaSincronizacion(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        putString(context,FECHA_CARGA,sdf.format(new Date()));
    }

    public static String getUltimaFechaSincronizacion(Context context){
        SharedPreferences pref =  context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getString(FECHA_CARGA,"");
    }

    public static String getUsuarioLogeado(Context context){
        SharedPreferences pref =  context.getSharedPreferences(PREF_USER,Context.MODE_PRIVATE);
        return pref.getString("usuario","error");
    }
    public static String getPassword(Context context){
        SharedPreferences pref =  context.getSharedPreferences(PREF_USER,Context.MODE_PRIVATE);
        return pref.getString("password","");
    }

    public static void putVendedorUUID(Context context,String uuid){
        putString(context,VENDEDOR_UUID,uuid);
    }

    public static String getVendedorUUID(Context context){
        SharedPreferences pref =  context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getString(VENDEDOR_UUID,"");
    }


    public static void putUltimoNroFactura(Context context,Integer numero){
        putInt(context, ULTIMO_NRO_FACTURA, numero);
    }

    public static Integer getUltimoNroFactura(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getInt(ULTIMO_NRO_FACTURA, 0);
    }


    public static void putUltimoIdFactura(Context context,Integer numero){
        putInt(context, ULTIMO_ID_FACTURA, numero);
    }

    public static Integer getUltimoIdFactura(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getInt(ULTIMO_ID_FACTURA, 0);
    }


    public static void putUltimoNroCobranza(Context context,Integer numero){

        putInt(context, ULTIMO_NRO_COBRANZA, numero == null?0:numero);
    }

    public static Integer getUltimoNroCobranza(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getInt(ULTIMO_NRO_COBRANZA, 0);
    }

    public static void putAdminUser(Context context,boolean flag){
        putBoolean(context, ADMIN_USER , flag);
    }

    public static boolean getAdminUser(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getBoolean(ADMIN_USER,false);
        }else {
            return false;
        }


    }

    public static void putDeviceAddress(Context context,String macAddress){
        putString(context,DEVICE_ADDRESS,macAddress);
    }

    public static String getDeviceAddress(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getString(DEVICE_ADDRESS,"");
        }else {
            return "404";
        }


    }


    public static void putHayConexion(Context context,boolean estado){
        putBoolean(context,HAY_CONEXION,estado);
    }

    public static boolean getHayConexion(Context context){
        if(context!=null){
            SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
            return pref.getBoolean(HAY_CONEXION,false);
        }else {
            return false;
        }


    }



    public static String getFormatoFecha(){

            return ("yyyy-MM-dd'T'HH:mm:ssZ");

    }



    public static void putString(Context context, String key,String value){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void putInt(Context context, String key,Integer value){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putLong(Context context, String key,Long value){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putBoolean(Context context, String key,boolean value){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public static String getString(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getString(key,null);
    }

    public static Integer getInt(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getInt(key, 0);
    }


    public static Long getLong(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getLong(key, 0l);
    }

    public static Boolean getBoolean(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(PREF_GLOBAL,Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

}
