package org.netzd.servicioswebdip.webservices;

import android.view.ViewDebug;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by Alumno12 on 24/02/18.
 */

public class JSONParser {
    private HttpURLConnection connection = null;
    public JSONParser(){

    }

    private HttpURLConnection createConnection(String petitionUri, Petition petition){
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dataOutputStream = null;

        try{
            switch (petition.getEntity()){
                case POST:
                    URL url = new URL(petitionUri); //Creamos URL bien formada
                    httpURLConnection=(HttpURLConnection) url.openConnection(); //Abrimos conexion
                    httpURLConnection.setInstanceFollowRedirects(false);//Para no hacer redirecciones
                    httpURLConnection.setConnectTimeout(petition.getTimeConnection());//Tiempo que esperara la respuesta del servidor
                    httpURLConnection.setReadTimeout(petition.getTimeConnection());
                    httpURLConnection.setRequestMethod("POST");//Especificamos tipo de metodo
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");//Cabeceras y tipo de peticion
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setRequestProperty("Accept-Encoding", "");
                    httpURLConnection.setDoInput(true);//Banderas
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);

                    dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream()); //A traves de la conexion, leemos sus bytes de salida ya que el json se arma con bytes
                    String paramsJSON = petition.getParamsPost(); //Devolvemos la cadena del json object
                    dataOutputStream.write(paramsJSON.getBytes("UTF-8")); //Lee los bytes de la cadena pero los codifica en UTF-8
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    break;
                case GET:
                    String uri = petitionUri;
                    if(petition.getParamsGet()!=null)
                        uri+=petition.getParamsGet();
                    URL urlGet = new URL(uri);
                    httpURLConnection=(HttpURLConnection) urlGet.openConnection(); //Abrimos conexion
                    httpURLConnection.setInstanceFollowRedirects(false);//Para no hacer redirecciones
                    httpURLConnection.setConnectTimeout(petition.getTimeConnection());//Tiempo que esperara la respuesta del servidor
                    httpURLConnection.setReadTimeout(petition.getTimeConnection());
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setUseCaches(false);
                    break;
                case FRIENDLY:
                    String uriFriendly = petitionUri;
                    if(petition.getParamFriendly()!=null)
                        uriFriendly+=petition.getParamFriendly();
                    URL urlFriendly = new URL(uriFriendly);
                    httpURLConnection=(HttpURLConnection) urlFriendly.openConnection(); //Abrimos conexion
                    httpURLConnection.setInstanceFollowRedirects(false);//Para no hacer redirecciones
                    httpURLConnection.setConnectTimeout(petition.getTimeConnection());//Tiempo que esperara la respuesta del servidor
                    httpURLConnection.setReadTimeout(petition.getTimeConnection());
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setUseCaches(false);
                    break;
                default:
                    URL urlNone = new URL(petitionUri);
                    httpURLConnection=(HttpURLConnection) urlNone.openConnection(); //Abrimos conexion
                    httpURLConnection.setInstanceFollowRedirects(false);//Para no hacer redirecciones
                    httpURLConnection.setConnectTimeout(petition.getTimeConnection());//Tiempo que esperara la respuesta del servidor
                    httpURLConnection.setReadTimeout(petition.getTimeConnection());
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setUseCaches(false);
                    break;
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
            return null;

        }catch (SocketTimeoutException f){
            f.printStackTrace();
            return null;
        }catch (Exception g){
            g.printStackTrace();
            return null;
        }
        return httpURLConnection;
    }
}
