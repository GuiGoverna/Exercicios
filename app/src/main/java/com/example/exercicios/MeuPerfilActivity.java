package com.example.exercicios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MeuPerfilActivity extends AppCompatActivity {

    private Spinner spinnerNivelAptidao;
    private Spinner spinnerObjetivo;
    private EditText nomeEditText;
    private Button salvarButton;
    private Button voltarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);

        nomeEditText = findViewById(R.id.nomeEditText);
        spinnerNivelAptidao = findViewById(R.id.spinnerNivelAptidao);
        spinnerObjetivo = findViewById(R.id.spinnerObjetivo);
        salvarButton = findViewById(R.id.salvarButton);
        voltarButton = findViewById(R.id.botao_voltar);

        // Adapter para o Spinner de Nível de Aptidão
        ArrayAdapter<CharSequence> adapterNivel = ArrayAdapter.createFromResource(this, R.array.nivel_aptidao, android.R.layout.simple_spinner_item);
        adapterNivel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelAptidao.setAdapter(adapterNivel);

        // Adapter para o Spinner de Objetivos
        ArrayAdapter<CharSequence> adapterObjetivo = ArrayAdapter.createFromResource(this, R.array.objetivos, android.R.layout.simple_spinner_item);
        adapterObjetivo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObjetivo.setAdapter(adapterObjetivo);

        // Salvar perfil
        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString();
                String nivelAptidao = spinnerNivelAptidao.getSelectedItem().toString();
                String objetivo = spinnerObjetivo.getSelectedItem().toString();

                // Salvar as informações no SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("Perfil", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("nome", nome);
                editor.putString("nivelAptidao", nivelAptidao);
                editor.putString("objetivo", objetivo);
                editor.apply();

                // Voltar para a tela principal
                Intent intent = new Intent(MeuPerfilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Voltar para a tela anterior
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
