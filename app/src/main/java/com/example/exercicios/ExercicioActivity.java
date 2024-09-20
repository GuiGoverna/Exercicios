package com.example.exercicios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExercicioActivity extends AppCompatActivity {

    private LinearLayout layoutExercicios;
    private Button concluirButton;
    private Button voltarButton;
    private SharedPreferences sharedPref;
    private SharedPreferences perfilPref;

    private TextView nomeTextView;
    private TextView nivelAptidaoTextView;
    private TextView objetivoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        layoutExercicios = findViewById(R.id.layoutExercicios);
        concluirButton = findViewById(R.id.btnConcluir);
        voltarButton = findViewById(R.id.btnVoltar);

        nomeTextView = findViewById(R.id.nomeTextView);
        nivelAptidaoTextView = findViewById(R.id.nivelAptidaoTextView);
        objetivoTextView = findViewById(R.id.objetivoTextView);

        sharedPref = getSharedPreferences("Historico", Context.MODE_PRIVATE);
        perfilPref = getSharedPreferences("Perfil", Context.MODE_PRIVATE);

        // Recuperar dados do perfil
        String nome = perfilPref.getString("nome", "Usuário");
        String nivelAptidao = perfilPref.getString("nivelAptidao", "Não definido");
        String objetivo = perfilPref.getString("objetivo", "Não definido");

        // Exibir dados do perfil
        nomeTextView.setText("Nome: " + nome);
        nivelAptidaoTextView.setText("Nível de Aptidão: " + nivelAptidao);
        objetivoTextView.setText("Objetivo: " + objetivo);

        // Adicionar exercícios com base no nível de aptidão e objetivo
        addExercicios(nivelAptidao, objetivo);

        concluirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder exerciciosConcluidos = new StringBuilder();
                for (int i = 0; i < layoutExercicios.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) layoutExercicios.getChildAt(i);
                    if (checkBox.isChecked()) {
                        exerciciosConcluidos.append(checkBox.getText().toString()).append("\n");
                    }
                }

                if (exerciciosConcluidos.length() > 0) {
                    // Adicionar informações ao histórico com a data no formato brasileiro
                    SharedPreferences.Editor editor = sharedPref.edit();
                    String historicoAtual = sharedPref.getString("historico", "");

                    // Utilizar a data no formato brasileiro (dd/MM/yyyy)
                    String dataAtual = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR")).format(new Date());

                    String historicoNovo = "Nome: " + nome + "\nData: " + dataAtual + "\n" + exerciciosConcluidos.toString() + "\n";
                    editor.putString("historico", historicoAtual + historicoNovo);
                    editor.apply();
                }

                // Voltar para a tela principal
                Intent intent = new Intent(ExercicioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a atividade atual e retorna para a tela anterior
            }
        });
    }

    private void addExercicios(String nivelAptidao, String objetivo) {
        layoutExercicios.removeAllViews(); // Limpa a lista atual de exercícios

        // Adiciona logs para verificar os valores recebidos
        Log.d("ExercicioActivity", "Nível de Aptidão: " + nivelAptidao);
        Log.d("ExercicioActivity", "Objetivo: " + objetivo);

        String[] exercicios = getExerciciosParaPerfil(nivelAptidao, objetivo);

        // Adiciona logs para verificar a lista de exercícios retornada
        Log.d("ExercicioActivity", "Exercícios retornados: " + (exercicios.length > 0 ? String.join(", ", exercicios) : "Nenhum exercício encontrado"));

        if (exercicios.length == 0) {
            TextView mensagem = new TextView(this);
            mensagem.setText("Nenhum exercício disponível para o perfil selecionado.");
            layoutExercicios.addView(mensagem);
        } else {
            for (String exercicio : exercicios) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(exercicio);
                layoutExercicios.addView(checkBox);
            }
        }
    }

    private String[] getExerciciosParaPerfil(String nivelAptidao, String objetivo) {
        // Adiciona logs para verificar a lógica de seleção
        Log.d("ExercicioActivity", "Selecionando exercícios para: Nível de Aptidão = " + nivelAptidao + ", Objetivo = " + objetivo);

        if (nivelAptidao.equals("Iniciante") && objetivo.equals("Perder Peso")) {
            return new String[]{"Caminhada rápida", "Agachamento livre", "Polichinelos", "Abdominais básicos", "Flexões de braço modificadas", "Elevação de pernas", "Subir e descer escadas", "Corrida", "Mountain climbers", "Alongamentos dinâmicos"};
        } else if (nivelAptidao.equals("Intermediário") && objetivo.equals("Perder Peso")) {
            return new String[]{"Corrida leve", "Burpees", "Agachamento com salto", "Flexões tradicionais", "Lunges", "Pular corda", "Prancha", "Mountain climbers avançados", "Treino intervalado de alta intensidade (HIIT)", "Deadlifts com peso leve a moderado"};
        } else if (nivelAptidao.equals("Avançado") && objetivo.equals("Perder Peso")) {
            return new String[]{"Sprints", "Snatch com kettlebell", "Burpees com salto de caixa", "Agachamento com barra", "Pull-ups (barra fixa)", "Pliometria avançada", "Clean and Press", "Flexões em anel", "Deadlifts pesados", "HIIT extremo"};
        } else if (nivelAptidao.equals("Iniciante") && objetivo.equals("Ganhar Massa Muscular")) {
            return new String[]{"Levantamento de peso básico", "Agachamento livre", "Supino com halteres", "Remada curvada", "Desenvolvimento com halteres", "Flexões de braço", "Levantamento terra", "Cadeira extensora", "Rosca direta", "Tríceps banco"};
        } else if (nivelAptidao.equals("Intermediário") && objetivo.equals("Ganhar Massa Muscular")) {
            return new String[]{"Agachamento com barra", "Supino com barra", "Remada com barra", "Levantamento terra", "Desenvolvimento militar", "Flexões com peso", "Rosca martelo", "Tríceps pulley", "Leg press", "Abdominais com peso"};
        } else if (nivelAptidao.equals("Avançado") && objetivo.equals("Ganhar Massa Muscular")) {
            return new String[]{"Agachamento frontal", "Supino inclinado", "Levantamento terra sumô", "Desenvolvimento com barra", "Barra fixa com peso", "Paralelas com peso", "Rosca concentrada", "Tríceps testa", "Stiff", "Abdominais na barra fixa"};
        } else if (nivelAptidao.equals("Iniciante") && objetivo.equals("Melhorar Condicionamento")) {
            return new String[]{"Caminhada rápida", "Pular corda", "Agachamentos", "Polichinelos", "Corrida leve", "Burpees simples", "Mountain climbers", "Abdominais", "Prancha básica", "Alongamentos"};
        } else if (nivelAptidao.equals("Intermediário") && objetivo.equals("Melhorar Condicionamento")) {
            return new String[]{"Corrida moderada", "HIIT", "Agachamento com salto", "Burpees", "Flexões com palmas", "Mountain climbers avançados", "Pular corda", "Prancha com torção", "Lunges com salto", "Sprints"};
        } else if (nivelAptidao.equals("Avançado") && objetivo.equals("Melhorar Condicionamento")) {
            return new String[]{"Sprints intervalados", "Burpees com salto de caixa", "Treino de circuitos", "HIIT avançado", "Pliometria com halteres", "Flexões explosivas", "Barra fixa com variação", "Lunges com salto avançados", "Remada invertida", "Snatch com barra"};
        } else {
            // Se nenhum perfil corresponder, retornará um array vazio
            return new String[]{};
        }
    }
}
