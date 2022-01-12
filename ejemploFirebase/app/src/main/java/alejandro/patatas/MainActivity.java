package alejandro.patatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    Button normal, ordenado,lays;
    DatabaseReference referencia;
    Query patatasCaras, patatasLays;

    private ValueEventListener handlerTodos;
    private ChildEventListener handlerCaras,handlerLays;

    public String mostrarTodasPatatas(DataSnapshot snapshot){
        int contador = 1;
        String s = "";
        Patatas patataLeida;
        for(DataSnapshot hijo : snapshot.getChildren()){
            patataLeida = hijo.getValue(Patatas.class);
            s += "La patata numero: "+ contador +" tiene esta información " + patataLeida.toString() + "\n";
            contador++;
        }
        return s;
    }

    public String mostrarPatata(DataSnapshot dataSnapshot){
        return dataSnapshot.getValue(Patatas.class).toString();
    }

    public View.OnClickListener queryGeneral = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            limpiar();
            handlerTodos = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    texto.setText(mostrarTodasPatatas(snapshot));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println(error.getDetails());
                }
            };

            referencia.addValueEventListener(handlerTodos);
        }
    };

    public View.OnClickListener queryCaras = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            limpiar();
            patatasCaras = referencia.orderByChild("precio");

            handlerCaras = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    texto.setText(texto.getText() + mostrarPatata(snapshot) + "\n");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

            patatasCaras.addChildEventListener(handlerCaras);
        }
    };

    public View.OnClickListener queryLays = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            limpiar();
            // Patatas cuya marca sea Lays
             patatasLays = referencia.orderByChild("marca").equalTo("Lays");
             // `patatas cuyo sabor empiece por una letra igual o superior a j
             // patatasLays = referencia.orderByChild("sabor").startAfter("j");
             // Top 3 de patatas más caras, al ser ascendente tengo que coger las 3 últimas
             //patatasLays = referencia.orderByChild("precio").limitToLast(3);

            handlerLays = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    texto.setText(texto.getText() + mostrarPatata(snapshot) + "\n");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

            patatasLays.addChildEventListener(handlerLays);
        }
    };

    public void limpiar(){
        texto.setText("");
        // Eliminamos los listeners!
        if(handlerTodos != null ) {
            referencia.removeEventListener(handlerTodos);
            handlerTodos = null;
        }
        else if (handlerLays != null){
            patatasLays.removeEventListener(handlerLays);
            handlerLays = null;
        }
        else if (handlerCaras != null) {
            patatasCaras.removeEventListener(handlerCaras);
            handlerCaras = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        limpiar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        normal = findViewById(R.id.todas);
        normal.setOnClickListener(queryGeneral);
        ordenado = findViewById(R.id.caras);
        ordenado.setOnClickListener(queryCaras);
        lays = findViewById(R.id.lays);
        lays.setOnClickListener(queryLays);


        FirebaseDatabase bbdd = FirebaseDatabase.getInstance();

        referencia = bbdd.getReference().child("Patatas");

        Map<String,Object> nuevas = new HashMap<>();
        nuevas.put("p09",new Patatas("el gallo rojo",1,"original"));
        nuevas.put("p07",new Patatas("doritos",1,"chili"));
        nuevas.put("p08",new Patatas("doritos",1,"ranch"));

        Patatas nueva = new Patatas("pringles",2.4,"paprika");
        // De esta forma nos guardamos la clave creada
        referencia.push().getKey();

        // referencia.setValue(nueva)
        // referencia.updateChild(nueva)

        //referencia.child("p06").setValue(null);



    }
}