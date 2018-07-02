package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.EleitorAdapter;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class EleitorActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor);

        Button btnEleitor = (Button) findViewById(R.id.buttonEleitor);

        realm = Realm.getDefaultInstance();

        btnEleitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EleitorActivity.this, NovoEleitor.class);
                intent.putExtra("numeroDoTitulo", "-1");
                startActivity(intent);
            }
        });

    }


    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEleitor);

        recyclerView.setAdapter(new EleitorAdapter(getEleitores(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Eleitor> getEleitores(){
        return (List) realm.where(Eleitor.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Eleitor eleitor = (Eleitor) object;
        Intent intent = new Intent(EleitorActivity.this, DetalheEleitor.class);
        intent.putExtra("numeroDoTitulo",eleitor.getNumeroDoTitulo());
        startActivity(intent);
    }

    public void finish(){
        super.finish();
        realm.close();
    }
}