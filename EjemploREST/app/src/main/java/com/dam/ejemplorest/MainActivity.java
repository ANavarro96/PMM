package com.dam.ejemplorest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    final String URL ="https://animechanapi.xyz/api/quotes";
    final String URLpelis = "http://www.omdbapi.com";
    // La clave que sirve de autorización para la api OMDB
    final String apiKey = "43ce2d54";

    TextView tw;
    Button button;
    Button botonPeli;
    EditText cuadroBusqueda;
    ArrayList<String> lista = new ArrayList<>();

    View.OnClickListener funcionPulsar  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Lanzamos un GET de todos los animes
            if(comprobarConexion()) new getTodoAsincrono().execute(URL);
            else Toast.makeText(getApplicationContext(),"Hay algún problema con la conexión, revisa",Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener funcionVerPelis  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Lanzamos un GET de todos los animes
            if(comprobarConexion()) new getPelisAsincrono().execute(URLpelis,apiKey);
            else Toast.makeText(getApplicationContext(),"Hay algún problema con la conexión, revisa",Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw = findViewById(R.id.tw);

        button = findViewById(R.id.button);
        button.setOnClickListener(funcionPulsar);

        botonPeli = findViewById(R.id.botonPelis);
        botonPeli.setOnClickListener(funcionVerPelis);
        cuadroBusqueda = findViewById(R.id.editTextTextPersonName);

    }

    // Función sencilla que comprueba si el Móvil tiene conexión a internet
    public boolean comprobarConexion(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo redMovil = cm.getNetworkInfo(cm.TYPE_MOBILE);
        NetworkInfo redWifi = cm.getNetworkInfo(cm.TYPE_WIFI);
        if ((redMovil != null || redWifi != null) && (redMovil.isConnectedOrConnecting() || redWifi.isConnectedOrConnecting())) {
            return true;
        }
        return false;
    }



    private class getTodoAsincrono extends AsyncTask<String, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            lista.clear();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            // En este ArrayList de String guardaremos las listas


            try {
                // Creamos nuestra URL de la API que nos devolverá 10 citas de animes al azar
                //URL animeURL = new URL("https://animechanapi.xyz/api/quotes");
                URL animeURL = new URL(strings[0]);

                // Instanciamos la conexión
                HttpsURLConnection conexion = (HttpsURLConnection) animeURL.openConnection();
                conexion.setRequestProperty("content-type", "application/json");

                
                System.out.println(conexion.getResponseCode());
                if (conexion.getResponseCode() == 200) {
                    // Ha ido bien, vamos a recoger cada las citas, recordad que está en JSON
                    InputStream responseBody = conexion.getInputStream();

                    JSONObject objeto = new JSONObject(IOUtils.toString(responseBody, StandardCharsets.UTF_8));

                    String s = "";
                    System.out.println(objeto.getJSONArray("data"));
                    JSONArray arrayCitas = objeto.getJSONArray("data");
                    for(int i = 0; i< arrayCitas.length();i++) {
                        JSONObject objetoArray = arrayCitas.getJSONObject(i);
                        s += objetoArray.getString("quote") + " ";
                        s += objetoArray.getString("character") + " ";
                        s += objetoArray.getString("anime") + " ";
                        lista.add(s);
                        System.out.println(s);
                        s = "";
                    }
                }
                else {
                    // Error handling code goes here
                    System.out.println("Algo ha ido mal...");
                    return false;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if(b) {
                tw.setText("");
                for(String s : lista) tw.setText(String.format("%s%s", tw.getText(), s));
            }
            else tw.setText("Ha habido algún problema");
        }
    }


    private class getPelisAsincrono extends AsyncTask<String, Integer, Boolean>{

        // Un objeto de Android que sivre para mostrar el progreso de la descarga
        ProgressDialog pd;
        String error = "";

        @Override
        protected void onPreExecute() {
            lista.clear();
            // Creamos un objeto ProgressDialog, que irá informando al usuario del
            // progreso en el que se encuentra
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Descargando peliculas...");
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Ejemplo de ProgressDialog");
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            // En este ArrayList de String guardaremos las listas

            try {
                // Creamos nuestra URL de la API que nos devolverá 10 citas de animes al azar
                //URL animeURL = new URL("https://animechanapi.xyz/api/quotes");
                String baseURL = strings[0];
                String clave = strings[1];
                String nombrePeli = cuadroBusqueda.getText().toString();
                URL pelisURL = new URL(baseURL + "/?s=" + nombrePeli + "&apiKey=" + clave);

                // Instanciamos la conexión
                HttpURLConnection conexion = (HttpURLConnection) pelisURL.openConnection();
                conexion.setRequestProperty("content-type", "application/json");


                System.out.println(conexion.getResponseCode());
                if (conexion.getResponseCode() == 200) {
                    // Ha ido bien, vamos a recoger cada las citas, recordad que está en JSON
                    InputStream responseBody = conexion.getInputStream();

                    JSONObject objeto = new JSONObject(IOUtils.toString(responseBody, StandardCharsets.UTF_8));

                    Boolean encontrado = objeto.getBoolean("Response");
                    if(encontrado) {
                        String s = "";

                        System.out.println(objeto.getJSONArray("Search"));
                        JSONArray arrayPelis = objeto.getJSONArray("Search");
                        pd.setMax(arrayPelis.length());
                        for (int i = 0; i < arrayPelis.length(); i++) {
                            JSONObject objetoArray = arrayPelis.getJSONObject(i);
                            s += objetoArray.getString("Title") + " ";
                            s += objetoArray.getString("Year") + " ";
                            lista.add(s);
                            System.out.println(s);
                            s = "";
                            // Cuando llamamos a publishProgress, se ejeutará el código que haya en
                            // onProgressUpdate
                            publishProgress(i);

                            // Aquí pararé el hilo para hacer como que está tardando
                            Thread.sleep(3000);

                        }
                    }

                    else{
                        error = objeto.getString("Error");
                        return false;
                    }
                }
                else {
                    // Error handling code goes here
                    System.out.println("Algo ha ido mal...");
                    return false;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            pd.setProgress(values[0]);
            pd.setMessage("Descargadas " + pd.getProgress() + " peliculas de " + pd.getMax());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean b) {
            // Con dismiss cerramos el diálogo
            pd.dismiss();
            tw.setText("");
            System.out.println(b);
            System.out.println(error);
            if(b)  for(String s : lista) tw.setText(tw.getText() + s + "\n");
            else Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        }
    }





}