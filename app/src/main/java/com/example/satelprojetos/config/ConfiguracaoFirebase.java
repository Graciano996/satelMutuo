package com.example.satelprojetos.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autentificacao;

    public static FirebaseAuth getFirebaseAuth(){
        if(autentificacao == null){
            autentificacao = FirebaseAuth.getInstance();
        }
        return autentificacao;



    }
}
