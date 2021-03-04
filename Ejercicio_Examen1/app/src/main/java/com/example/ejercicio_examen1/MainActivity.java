package com.example.ejercicio_examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    final String URL ="https://www.w3schools.com/js/customers_mysql.php";

    Button buscar;
    EditText escribir;
    TextView informacion;

    ProgressDialog pd;

    String s = "";

    ArrayList<String>lista = new ArrayList<>();

    View.OnClickListener funcionBuscar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        if(informacion.getText().toString().equalsIgnoreCase("")){
            new getTodoAsincrono().execute(URL);
        }else{
            new getBuscar().execute(URL);
        }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscar = findViewById(R.id.buscar);
        escribir = findViewById(R.id.escribir);
        informacion =findViewById(R.id.informacion);

        buscar.setOnClickListener(funcionBuscar);

    }

    private class getTodoAsincrono extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {

            lista.clear();

            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("App descarga ciudadanos");
            pd.show();

            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            try {

                URL baseURL = new URL(strings[0]);

                HttpsURLConnection conexion = (HttpsURLConnection) baseURL.openConnection();
                conexion.setRequestProperty("content-type", "application/json");


                if (conexion.getResponseCode() == 200) {

                    InputStream responseBody = conexion.getInputStream();

                    String bytesAsTRING = IOUtils.toString(responseBody, StandardCharsets.UTF_8);

                    JSONArray objeto = new JSONArray(bytesAsTRING);

                    pd.setMax(objeto.length());

                    for(int i = 0; i< objeto.length();i++) {

                        JSONObject objetoArray = objeto.getJSONObject(i);

                        s += "Ciudadano numero " + i+ "\n";
                        s += "Nombre:" + objetoArray.getString("Name") + "\n";
                        s += "Ciudad: "+ objetoArray.getString("City") + "\n ";
                        s += "Pais: "+objetoArray.getString("Country") + " \n";

                        s+=" \n";

                        publishProgress(i);

                        Thread.sleep(500);

                        lista.add(s);

                        s="";
                    }

                }else{

                    System.out.println("Ha ocurrido un error");
                    return false;
                }

            } catch (IOException | JSONException | InterruptedException e) {
                e.printStackTrace();
            }


            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            pd.setProgress(values[0]);

            pd.setMessage("Vamos por el ciudadano numero "+pd.getProgress());

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean b) {

            if(b) {

                pd.dismiss();
                informacion.setText("");
                for(String s : lista) informacion.setText(String.format("%s%s", informacion.getText(), s));
            }

            super.onPostExecute(b);
        }
    }

    private class getBuscar extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {

            lista.clear();

            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("App descarga ciudadanos");
            pd.show();

            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            try {

                URL baseURL = new URL(strings[0]);

                HttpsURLConnection conexion = (HttpsURLConnection) baseURL.openConnection();
                conexion.setRequestProperty("content-type", "application/json");


                if (conexion.getResponseCode() == 200) {

                    InputStream responseBody = conexion.getInputStream();

                    String bytesAsTRING = IOUtils.toString(responseBody, StandardCharsets.UTF_8);

                    JSONArray objeto = new JSONArray(bytesAsTRING);

                    pd.setMax(objeto.length());

                    for(int i = 0; i< objeto.length();i++) {

                        JSONObject objetoArray = objeto.getJSONObject(i);
                    if(escribir.getText().toString().equalsIgnoreCase(objetoArray.getString("Country"))){
                        s += "Ciudadano numero " + i+ "\n";
                        s += "Nombre:" + objetoArray.getString("Name") + "\n";
                        s += "Ciudad: "+ objetoArray.getString("City") + "\n ";
                        s += "Pais: "+objetoArray.getString("Country") + " \n";

                        s+=" \n";

                        publishProgress(i);

                        Thread.sleep(500);

                        lista.add(s);

                        s="";
                    }

                    }

                }else{

                    System.out.println("Ha ocurrido un error");
                    return false;
                }

            } catch (IOException | JSONException | InterruptedException e) {
                e.printStackTrace();
            }


            return true;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            pd.setProgress(values[0]);

            pd.setMessage("Vamos por el ciudadano numero "+pd.getProgress());

            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Boolean b) {

            if(b) {

                pd.dismiss();
                informacion.setText("");
                for(String s : lista) informacion.setText(String.format("%s%s", informacion.getText(), s));
            }

            super.onPostExecute(b);
        }

    }

}