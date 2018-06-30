package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class Detalhe extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6, text7;

    String tipo;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        text6 = (TextView) findViewById(R.id.textView6);
        text7 = (TextView) findViewById(R.id.textView7);

        Intent intent = getIntent();
        tipo = (String) intent.getSerializableExtra("tipo");
        realm = Realm.getDefaultInstance();

        if (tipo.equals("eleitor")) {
            String numeroDoTitulo = (String) intent.getSerializableExtra("numeroDoTitulo");
            Eleitor eleitor = realm.where(Eleitor.class).equalTo("numeroDoTitulo",
                    numeroDoTitulo).findFirst();

            text1.setText(eleitor.getNome());
            text2.setText(eleitor.getNumeroDoTitulo());
            text3.setText(eleitor.getZona());
            text4.setText(eleitor.getSecao());
            text6.setText(eleitor.getMunicipio());
            text5.setText(eleitor.getDataDeNascimento());
            text7.setText(eleitor.getNomeDaMae());

        } else if (tipo.equals("candidato")) {
            String numeroNaUrna = (String) intent.getSerializableExtra("numeroNaUrna");
            Candidato candidato = realm.where(Candidato.class).equalTo("numeroNaUrna",
                    numeroNaUrna).findFirst();

            text1.setText(candidato.getNome());
            text2.setText(candidato.getPartido());
            text3.setText(candidato.getCargo());
            text4.setText(candidato.getNumeroNaUrna());
            text6.setText(candidato.getMunicipio());
            text5.setText(candidato.getEstado());
            text7.setText(String.valueOf(candidato.getNumeroDeVotos()));
        }
    }
}
