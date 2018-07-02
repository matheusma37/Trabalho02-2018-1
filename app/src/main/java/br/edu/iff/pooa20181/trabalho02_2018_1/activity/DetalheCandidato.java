package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class DetalheCandidato extends AppCompatActivity {

    private TextView txtNome, txtPartido, txtCargo, txtNumeroUrna, txtNumeroVotos, txtMunicipio, txtEstado;
    private Button btnEditarC, btnDeletarC;

    private Candidato candidato;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_candidato);

        txtNome = (TextView) findViewById(R.id.textVNomeC);
        txtCargo = (TextView) findViewById(R.id.textVCargoC);
        txtMunicipio = (TextView) findViewById(R.id.textVMunicipioC);
        txtEstado = (TextView) findViewById(R.id.textVEstadoC);
        txtNumeroUrna = (TextView) findViewById(R.id.textVNumeroUrnaC);
        txtNumeroVotos = (TextView) findViewById(R.id.textVNumeroVotosC);
        txtPartido = (TextView) findViewById(R.id.textVPartidoC);

        btnEditarC = (Button) findViewById(R.id.buttonEditarC);
        btnDeletarC = (Button) findViewById(R.id.buttonDeletarC);

        Intent intent = getIntent();
        realm = Realm.getDefaultInstance();

        String numeroNaUrna = (String) intent.getSerializableExtra("numeroNaUrna");
        candidato = realm.where(Candidato.class).equalTo("numeroNaUrna",
                numeroNaUrna).findFirst();

        btnDeletarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
            }
        });

        btnEditarC.setOnClickListener(new View.OnClickListener() {
            Intent intentEditar;
            @Override
            public void onClick(View view) {
                intentEditar = new Intent(DetalheCandidato.this, NovoCandidato.class);
                intentEditar.putExtra("numeroNaUrna", candidato.getNumeroNaUrna());
                startActivity(intentEditar);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        txtNome.setText("Nome: " + candidato.getNome());
        txtPartido.setText("Partido: " + candidato.getPartido());
        txtNumeroUrna.setText("Número na Urna: " + candidato.getNumeroNaUrna());
        txtNumeroVotos.setText("Número de Votos: " + candidato.getNumeroDeVotos());
        txtMunicipio.setText("Município: " + candidato.getMunicipio());
        txtEstado.setText("Estado: " + candidato.getEstado());
        txtCargo.setText("Cargo: " + candidato.getCargo());

    }

    public void deletar(){
        realm.beginTransaction();

        candidato.deleteFromRealm();

        realm.commitTransaction();

        Toast.makeText(this,"Candidato deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void finish(){
        realm.close();
        super.finish();
    }
}
