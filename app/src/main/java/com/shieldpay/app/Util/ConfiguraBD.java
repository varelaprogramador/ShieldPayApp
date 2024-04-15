package com.shieldpay.app.Util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBD {
    private static FirebaseAuth auth;

    public static FirebaseAuth Firebaseautenticacao(){
        if(auth==null){
            auth=FirebaseAuth.getInstance();
        }
        return auth;
    }
}
