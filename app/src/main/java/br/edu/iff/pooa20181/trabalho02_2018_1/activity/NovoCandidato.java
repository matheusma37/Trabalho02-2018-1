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

        if (iNumeroUrna > 0){
            Candidato candidato = (Candidato) realm.where(Candidato.class).equalTo("numeroNaUrna",
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
                edtNumeroVotos.setText("");
            }
        });
    }

    private void salvar(){
        realm.beginTransaction();

        Candidato candidato = new Candidato();
        candidato.setNome(edtNome.getText().toString());
        candidato.setPartido(edtPartido.getText().toString());
        candidato.setCargo(edtCargo.getText().toString());
        candidato.setMunicipio(edtMunicipio.getText().toString());
        candidato.setEstado(edtEstado.getText().toString());
        candidato.setNumeroNaUrna(edtNumeroUrna.getText().toString());
        candidato.setNumeroDeVotos(Integer.parseInt(edtNumeroVotos.getText().toString()));

        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();
    }

}
