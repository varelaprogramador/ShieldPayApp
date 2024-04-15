package com.shieldpay.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shieldpay.app.R;

public class AreaActivity extends AppCompatActivity {
    Button botaoCadastrar,botaoEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);


        botaoEntrar= findViewById(R.id.btn_entrar_area);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topageEnt = new Intent(AreaActivity.this, EntrarActivity.class);
                startActivity(topageEnt);
            }
        });
        botaoCadastrar= findViewById(R.id.btn_cadastra_area);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topageCad = new Intent(AreaActivity.this, CadastroActivity.class);
                startActivity(topageCad);
            }
        });





    }
}
