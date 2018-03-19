package com.example.superordenata.realmprueba;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.superordenata.realmprueba.adaptars.GridAdapter;
import com.example.superordenata.realmprueba.models.Nota;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private FloatingActionButton fab;
    private EditText etTitle, etNota;
    private RealmResults<Nota> result;
    private GridView gridView;

    private void init(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        fab = findViewById(R.id.fab);
        /*etTitle = findViewById(R.id.etTitle);
        etNota = findViewById(R.id.etNota);*/
        gridView = findViewById(R.id.gridView);

        result = realm.where(Nota.class).equalTo("id", 1).findAll();

        /*etTitle.setText((result.size() > 0) ? result.get(0).getTitle() : "");
        etNota.setText((result.size() > 0) ? result.get(0).getNote() : "");*/

        event();
    }

    private void event(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Nota nota;
                        if(result.size() > 0){
                            /*nota = result.get(0);
                            nota.setTitle(etTitle.getText().toString());
                            nota.setNote(etNota.getText().toString());*/
                            nota = result.get(0);
                            nota.setTitle("Prueba");
                            nota.setNote("Hola");
                        } else {
                            //nota = new Nota(etTitle.getText().toString(), etNota.getText().toString());
                            nota = new Nota("Prueba", "Hola");
                        }
                        realm.copyToRealmOrUpdate(nota);
                    }
                });

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Click" + i, Toast.LENGTH_SHORT).show();
            }
        });

        GridAdapter gridAdapter = new GridAdapter(this, R.layout.grid_item, result);
        gridView.setAdapter(gridAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
