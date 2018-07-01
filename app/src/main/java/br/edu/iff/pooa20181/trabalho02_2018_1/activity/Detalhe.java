package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Pessoa;
import io.realm.Realm;

public class Detalhe extends AppCompatActivity {

    private TextView text1, text2, text3, text4, text5, text6, text7;
    private Button btnEditar, btnDeletar;


    private String tipo;
    private Pessoa pessoa;
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

        btnEditar = (Button) findViewById(R.id.buttonEditar);
        btnDeletar = (Button) findViewById(R.id.buttonDeletar);

        Intent intent = getIntent();
        tipo = (String) intent.getSerializableExtra("tipo");

        if (tipo.equals("eleitor")){
            String numeroDoTitulo = (String) intent.getSerializableExtra("numeroDoTitulo");
            pessoa = realm.where(Eleitor.class).equalTo("numeroDoTitulo",
                    numeroDoTitulo).findFirst();
        }else if(tipo.equals("candidato")){
            String numeroNaUrna = (String) intent.getSerializableExtra("numeroNaUrna");
            pessoa = realm.where(Candidato.class).equalTo("numeroDoTitulo",
                    numeroNaUrna).findFirst();
        }

        realm = Realm.getDefaultInstance();

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar(pessoa);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            Intent intentEditar;
            @Override
            public void onClick(View view) {
                if (pessoa instanceof Eleitor){
                    intentEditar = new Intent(Detalhe.this, NovoEleitor.class);
                    intentEditar.putExtra("numeroDoTitulo", ((Eleitor)pessoa).getNumeroDoTitulo());
                    startActivity(intentEditar);
                }else if(pessoa instanceof Candidato){
                    intentEditar = new Intent(Detalhe.this, NovoCandidato.class);
                    intentEditar.putExtra("numeroNaUrna", ((Candidato)pessoa).getNumeroNaUrna());
                    startActivity(intentEditar);
                }
            }
        });

        if (pessoa instanceof Eleitor) {
            text1.setText(((Eleitor)pessoa).getNome());
            text2.setText(((Eleitor)pessoa).getNumeroDoTitulo());
            text3.setText(((Eleitor)pessoa).getZona());
            text4.setText(((Eleitor)pessoa).getSecao());
            text6.setText(((Eleitor)pessoa).getMunicipio());
            text5.setText(((Eleitor)pessoa).getDataDeNascimento());
            text7.setText(((Eleitor)pessoa).getNomeDaMae());

        } else if (pessoa instanceof Candidato) {
            text1.setText(((Candidato)pessoa).getNome());
            text2.setText(((Candidato)pessoa).getPartido());
            text3.setText(((Candidato)pessoa).getCargo());
            text4.setText(((Candidato)pessoa).getNumeroNaUrna());
            text6.setText(((Candidato)pessoa).getMunicipio());
            text5.setText(((Candidato)pessoa).getEstado());
            text7.setText(String.valueOf(((Candidato)pessoa).getNumeroDeVotos()));
        }
    }

    public void deletar(Pessoa p){
        realm.beginTransaction();

        if (p instanceof Eleitor)
            ((Eleitor)p).deleteFromRealm();
        else if (p instanceof Candidato)
            ((Candidato)p).deleteFromRealm();

        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Elemento deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
