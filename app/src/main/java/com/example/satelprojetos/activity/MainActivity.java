package com.example.satelprojetos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.satelprojetos.R;
import com.example.satelprojetos.model.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private EditText nomeUsuario, senhaUsuario;
    private String  cadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeUsuario = findViewById(R.id.editTextTextPersonName);
        senhaUsuario = findViewById(R.id.editTextTextPassword);
    }
    public void abrirDrawer(View view){

        nomeUsuario = findViewById(R.id.editTextTextPersonName);
        final DatabaseReference usuarioDatabase = referencia.child("usuarios");
        final Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario.getText().toString());
        usuario.setSenha(senhaUsuario.getText().toString());
        Query queryUid=usuarioDatabase.orderByKey();
        queryUid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cadastrado="";
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    final String key=datas.getKey();
                    usuarioDatabase.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Usuario usuarioBanco = dataSnapshot.getValue(Usuario.class);
                            if ((usuario.getNome().equals(usuarioBanco.getNome())) && (usuario.getSenha().equals(usuarioBanco.getSenha())) ) {
                                cadastrado = "X";
                                Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                                intent.putExtra("ID", key);
                                startActivity(intent);
                            }
                            Log.i("TESTE2", usuarioBanco.getNome());
                            Log.i("TESTE2", usuarioBanco.getSenha());
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if(cadastrado!="X"){
                                        Toast.makeText(MainActivity.this, "Verique os dados digitados", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, 100);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Log.i("TESTE", key);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Log.i("ENTREI","apertei back");

            final AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.LightDialogTheme);
            dialog.setTitle("Sair do aplicativo?");
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAndRemoveTask();
                    System.exit(0);
                }
            });
            dialog.setNegativeButton("Não", null);
            dialog.create();
            dialog.show();

    }
}