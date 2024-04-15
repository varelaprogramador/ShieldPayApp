package com.shieldpay.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shieldpay.app.Models.Usuario;
import com.shieldpay.app.R;
import com.shieldpay.app.Util.ConfiguraBD;

public class CadastroActivity extends AppCompatActivity {
    Usuario usuario=new Usuario();
    FirebaseAuth autenticacao;
    EditText campoNome, campoCpf, campoDataNasci, campoEmail, campoSenha;
    TextView acessoTela;
    Button botaoCadastro;
    CheckBox checkTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        inicializar();

    }

    private void inicializar() {
        campoNome = findViewById(R.id.LabelName);
        campoCpf = findViewById(R.id.labelCpf);
        campoDataNasci = findViewById(R.id.LabelData);
        campoEmail = findViewById(R.id.LabelEmail);
        campoSenha = findViewById(R.id.LabelPassword);
        botaoCadastro = findViewById(R.id.validarCadastro);
        checkTermos= findViewById(R.id.checktermos);
        acessoTela=findViewById(R.id.textacesso);




    }
    public void loader() {
        Loading loading = new Loading(this);

        // ... (lógica da sua função que requer o loader)
        //pre loader




                loading.show();

                Handler handler = new Handler();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        loading.cancel();
                    }
                };
                handler.postDelayed(runnable,5000);



        // Opcionalmente, cancele o loader após a lógica terminar
    }

    public void validarCadastro(View view) {

        String nome = campoNome.getText().toString();
        String cpf = campoCpf.getText().toString();
        String dataNasci = campoDataNasci.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        boolean isValidaty=true;

        //Erro personalizado
        LayoutInflater inflater = getLayoutInflater();
        View customToastView = inflater.inflate(R.layout.custom_erro, null);
        TextView toastText = customToastView.findViewById(R.id.toast_text);





        //verificacao
        if (nome.isEmpty()) {

            toastText.setText("Preencha seu nome");  // Defina o texto da sua mensagem
            isValidaty=false;



        }  else if (dataNasci.isEmpty()) {
            toastText.setText("Preencha sua Data de Nascimento");
            isValidaty=false;

        } else if (cpf.isEmpty()) {
            toastText.setText("Preencha seu cpf");
            isValidaty=false;

        }else if (email.isEmpty()) {
            toastText.setText("Preencha seu E-mail");
            isValidaty=false;

        } else if (senha.isEmpty()) {
            toastText.setText("Preencha sua senha");
            isValidaty=false;
        } else if (!checkTermos.isChecked()) {

            toastText.setText("Confirme os termos de condições.");
            isValidaty=false;
        }


        if(!isValidaty){
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(customToastView);
        toast.show();
        }else{
            //cadastro

            loader();

            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setDataNasci(dataNasci);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            cadastrarUsuario();
        }

    }
    public void toTelaEntrar(View v){
        Intent topage = new Intent(CadastroActivity.this, EntrarActivity.class);
        startActivity(topage);
    }

    private void cadastrarUsuario() {





        //sucess
        LayoutInflater inflaterSucess = getLayoutInflater();
        View customToastViewSucess = inflaterSucess.inflate(R.layout.custom_aviso_sucess, null);
        TextView sucessToastText = customToastViewSucess.findViewById(R.id.toast_text);

        //error
        LayoutInflater inflaterErro = getLayoutInflater();
        View ToastViewErro = inflaterErro.inflate(R.layout.custom_erro,null);
        TextView erroToastText = ToastViewErro.findViewById(R.id.toast_text);



        Toast toast = new Toast(this);

        autenticacao=ConfiguraBD.Firebaseautenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sucessToastText.setText("Cadastrado com sucesso!!");


                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(customToastViewSucess);
                    toast.show();
                }else{
                    erroToastText.setText("Ocorreu um erro!!");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(ToastViewErro);
                    toast.show();
                }
            }
        });


    }
}