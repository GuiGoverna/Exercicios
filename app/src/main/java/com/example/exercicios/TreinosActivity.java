package com.example.exercicios;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TreinosActivity extends AppCompatActivity {

    private ListView listaTreinos;
    private ArrayList<String> exercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinos);

        listaTreinos = findViewById(R.id.lista_treinos);
        exercicios = new ArrayList<>();

        // Baseado no perfil do usuário, você pode carregar os exercícios sugeridos
        carregarExercicios();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, exercicios);
        listaTreinos.setAdapter(adapter);
    }

    private void carregarExercicios() {
        // Exemplo de lógica para sugerir exercícios (pode expandir conforme o perfil)
        exercicios.add("Flexão de Braço");
        exercicios.add("Agachamento");
        exercicios.add("Prancha Abdominal");
        exercicios.add("Burpees");
        exercicios.add("Abdominais");
    }
}
