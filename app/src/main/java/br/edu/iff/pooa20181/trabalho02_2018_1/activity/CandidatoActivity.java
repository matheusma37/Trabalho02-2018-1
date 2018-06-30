package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.Detalhe;
import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.CandidatoAdapter;
import br.edu.iff.pooa20181.trabalho02_2018_1.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class CandidatoActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato);

        Button btnCandidato = (Button) findViewById(R.id.buttonCandidato);

        realm = Realm.getDefaultInstance();

        btnCandidato.setOnClickListener((view) -> {
            Intent intent = new Intent(CandidatoActivity.this, NovoCandidato.class);
            startActivity(intent);
        });

    }


    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCandidato);

        recyclerView.setAdapter(new CandidatoAdapter(getCandidatos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Candidato> getCandidatos(){

        return (List) realm.where(Candidato.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Candidato candidato = (Candidato) object;
        Intent intent = new Intent(CandidatoActivity.this, Detalhe.class);
        intent.putExtra("numeroNaUrna",candidato.getNumeroNaUrna());
        intent.putExtra("tipo", "candidato");
        startActivity(intent);
    }


    public void finish(){
        super.finish();
        realm.close();


    }
}