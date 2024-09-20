package com.example.exercicios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button abrirExerciciosButton = findViewById(R.id.botao_abrir_exercicios);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button meuPerfilButton = findViewById(R.id.botao_meu_perfil);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button historicoButton = findViewById(R.id.botao_historico);

        abrirExerciciosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExercicioActivity.class);
                startActivity(intent);
            }
        });

        meuPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeuPerfilActivity.class);
                startActivity(intent);
            }
        });

        historicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
                startActivity(intent);
            }
        });
    }
}
