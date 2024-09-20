package com.example.exercicios;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HistoricoActivity extends AppCompatActivity {

    private TextView historicoTextView;
    private Button limparHistoricoButton;
    private Button voltarButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        historicoTextView = findViewById(R.id.textViewHistorico);
        limparHistoricoButton = findViewById(R.id.botao_limpar_historico);
        voltarButton = findViewById(R.id.botao_voltar);

        // Recuperar o nome do perfil
        SharedPreferences perfilPref = getSharedPreferences("Perfil", Context.MODE_PRIVATE);
        String nome = perfilPref.getString("nome", "Usuário");

        // Exibir histórico de exercícios
        SharedPreferences sharedPref = getSharedPreferences("Historico", Context.MODE_PRIVATE);
        String historico = sharedPref.getString("historico", "Nenhum exercício concluído.");

        // Atualizar o histórico com o nome do perfil
        StringBuilder historicoComNome = new StringBuilder();
        if (!historico.equals("Nenhum exercício concluído.")) {
            // Adicionar o nome do usuário antes do histórico
            historicoComNome.append(historico);
        } else {
            // Apenas o texto padrão caso não haja histórico
            historicoComNome.append("Nenhum exercício concluído.");
        }

        // Exibir o histórico atualizado
        historicoTextView.setText(historicoComNome.toString());

        // Botão para limpar o histórico
        limparHistoricoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.apply();
                // Atualizar a visualização para mostrar a mensagem padrão sem nome
                historicoTextView.setText("Nenhum exercício concluído.");
            }
        });

        // Botão voltar
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
