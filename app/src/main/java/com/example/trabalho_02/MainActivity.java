package com.example.trabalho_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_ADD = 1;
    public static int REQUEST_EDIT = 2;

    EditText chave;
    int chaveEnviada;
    boolean chaveExiste;

    //List View
    ArrayList<Tarefa> listaTarefas;
    ArrayAdapter adapter;
    ListView listViewTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chaveExiste = false;

        listaTarefas = new ArrayList<Tarefa>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas );
        listViewTarefas = findViewById(R.id.listaTarefas);
        listViewTarefas.setAdapter(adapter);


    }

    //Adicionar Tarefa
    public void clicarAdicionarTarefa(View v){
        Intent intent = new Intent(this, AdicionarTarefa.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    //Editar Tarefa
    public void clicarEditarTarefa(View v){
        Intent intent = new Intent(this, AdicionarTarefa.class);

        chave =  (EditText) findViewById(R.id.textEdit);
        chaveEnviada = Integer.parseInt(chave.getText().toString());

        Tarefa tarefa = listaTarefas.get(chaveEnviada);

        intent.putExtra("chaveTarefa", tarefa.getChave());
        intent.putExtra("nomeTarefa", tarefa.getNome());
        intent.putExtra("turnoTarefa", tarefa.getTurno());

        startActivityForResult(intent, REQUEST_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ADD && resultCode == AdicionarTarefa.RESULT_ADD){

            String nomeTarefa = (String) data.getExtras().get("nomeTarefa");
            String turnoTarefa = (String) data.getExtras().get("turnoTarefa");

            Tarefa tarefa = new Tarefa(nomeTarefa, turnoTarefa);

            listaTarefas.add(tarefa);
            adapter.notifyDataSetChanged();

        }else if(requestCode==REQUEST_EDIT && resultCode == AdicionarTarefa.RESULT_ADD){
            String nomeTarefa = (String) data.getExtras().get("nomeTarefa");
            String turnoTarefa = (String) data.getExtras().get("turnoTarefa");

            for(int i=0; i<listaTarefas.size(); i++){
                if(listaTarefas.get(i).getChave() == chaveEnviada){
                    listaTarefas.get(i).setNome(nomeTarefa);
                    listaTarefas.get(i).setTurno(turnoTarefa);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }


        }else if(resultCode == AdicionarTarefa.RESULT_CANCEL){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }

    }

    //Pop-up Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Pop-up Menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, AdicionarTarefa.class);
                startActivityForResult(intent, REQUEST_ADD);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}