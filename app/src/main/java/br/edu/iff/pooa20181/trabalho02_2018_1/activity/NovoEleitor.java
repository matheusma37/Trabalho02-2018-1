package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class NovoEleitor extends AppCompatActivity {

    private EditText edtNome, edtZona, edtSecao, edtDataNasc,
            edtMunicipio, edtNomeMae, edtTitulo;

    private Button btnSalvar, btnLimpar;

    private int iNumeroTitulo;
    private String sNumeroTitulo;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_eleitor);

        edtNome = (EditText) findViewById(R.id.edtTNomeE);
        edtZona = (EditText) findViewById(R.id.edtTZonaE);
        edtSecao = (EditText) findViewById(R.id.edtTSecaoE);
        edtDataNasc = (EditText) findViewById(R.id.edtTDataNascimentoE);
        edtMunicipio = (EditText) findViewById(R.id.edtTMunicipioE);
        edtNomeMae = (EditText) findViewById(R.id.edtTNomeMaeE);
        edtTitulo = (EditText) findViewById(R.id.edtTTituloE);

        btnSalvar = (Button) findViewById(R.id.buttonSalvarE);
        btnLimpar = (Button) findViewById(R.id.buttonLimparE);

        Intent intent = getIntent();

        sNumeroTitulo = (String) intent.getSerializableExtra("numeroDoTitulo");
        iNumeroTitulo = Integer.parseInt(sNumeroTitulo);

        realm = Realm.getDefaultInstance();

        if (iNumeroTitulo > 0) {
            Eleitor eleitor = realm.where(Eleitor.class).equalTo("numeroDoTitulo",
                    sNumeroTitulo).findFirst();

            edtNome.setText(eleitor.getNome());
            edtZona.setText(eleitor.getZona());
            edtSecao.setText(eleitor.getSecao());
            edtMunicipio.setText(eleitor.getMunicipio());
            edtDataNasc.setText(eleitor.getDataDeNascimento());
            edtNomeMae.setText(eleitor.getNomeDaMae());
            edtTitulo.setText(eleitor.getNumeroDoTitulo());

            edtTitulo.setEnabled(false);
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
                edtZona.setText("");
                edtSecao.setText("");
                edtMunicipio.setText("");
                edtDataNasc.setText("");
                edtNomeMae.setText("");
                if (iNumeroTitulo < 0) {
                    edtTitulo.setText("");
                }
            }
        });
    }

    private void salvar(){
        realm.beginTransaction();

        Eleitor eleitor = new Eleitor();
        eleitor.setNome(edtNome.getText().toString());
        eleitor.setZona(edtZona.getText().toString());
        eleitor.setSecao(edtSecao.getText().toString());
        eleitor.setMunicipio(edtMunicipio.getText().toString());
        eleitor.setDataDeNascimento(edtDataNasc.getText().toString());
        eleitor.setNomeDaMae(edtNomeMae.getText().toString());
        eleitor.setNumeroDoTitulo(edtTitulo.getText().toString());

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();
    }
}