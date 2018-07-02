package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class NovoCandidato extends AppCompatActivity {

    private EditText edtNome, edtPartido, edtCargo, edtNumeroUrna,
            edtMunicipio, edtEstado, edtNumeroVotos;

    private Button btnSalvar, btnLimpar;

    private int iNumeroUrna;
    private String sNumeroUrna;

    private Realm realm;
    private Candidato candidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_candidato);

        edtNome = (EditText) findViewById(R.id.edtTNomeC);
        edtPartido = (EditText) findViewById(R.id.edtTPartidoC);
        edtCargo = (EditText) findViewById(R.id.edtTCargoC);
        edtNumeroUrna = (EditText) findViewById(R.id.edtTNumeroUrnaC);
        edtMunicipio = (EditText) findViewById(R.id.edtTMunicipioC);
        edtEstado = (EditText) findViewById(R.id.edtTEstadoC);
        edtNumeroVotos = (EditText) findViewById(R.id.edtTNumeroVotosC);

        btnSalvar = (Button) findViewById(R.id.buttonSalvarC);
        btnLimpar = (Button) findViewById(R.id.buttonLimparC);

        Intent intent = getIntent();

        sNumeroUrna = (String) intent.getSerializableExtra("numeroNaUrna");
        iNumeroUrna = Integer.parseInt(sNumeroUrna);

        realm = Realm.getDefaultInstance();
        candidato = null;

        if (iNumeroUrna >= 0){
            candidato = realm.where(Candidato.class).equalTo("numeroNaUrna",
                    sNumeroUrna).findFirst();

            edtNome.setText(candidato.getNome());
            edtPartido.setText(candidato.getPartido());
            edtCargo.setText(candidato.getCargo());
            edtMunicipio.setText(candidato.getMunicipio());
            edtEstado.setText(candidato.getEstado());
            edtNumeroUrna.setText(candidato.getNumeroNaUrna());
            edtNumeroVotos.setText(String.valueOf(candidato.getNumeroDeVotos()));

            edtNumeroUrna.setEnabled(false);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNome.setText("");
                edtPartido.setText("");
                edtCargo.setText("");
                edtMunicipio.setText("");
                edtEstado.setText("");
                if (iNumeroUrna < 0){
                    edtNumeroUrna.setText("");
                }
                edtNumeroVotos.setText("0");
            }
        });
    }

    private void salvar(){
        realm.beginTransaction();

        if(candidato == null){
            candidato = new Candidato();
            candidato.setNumeroNaUrna(edtNumeroUrna.getText().toString());
        }

        setar(candidato);

        realm.copyToRealm(candidato);
        realm.commitTransaction();

        Toast.makeText(this,"Candidato Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void setar(Candidato c){
        c.setNome(edtNome.getText().toString());
        c.setPartido(edtPartido.getText().toString());
        c.setCargo(edtCargo.getText().toString());
        c.setMunicipio(edtMunicipio.getText().toString());
        c.setEstado(edtEstado.getText().toString());
        c.setNumeroDeVotos(Integer.parseInt(edtNumeroVotos.getText().toString()));
    }

    public void finish(){
        realm.close();
        super.finish();
    }
}
