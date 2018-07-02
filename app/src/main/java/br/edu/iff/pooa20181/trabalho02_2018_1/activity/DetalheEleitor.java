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
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class DetalheEleitor extends AppCompatActivity {

    private TextView txtNome, txtTitulo, txtMunicipio, txtZona, txtSecao, txtNomeMae, txtDataNasc;
    private Button btnEditarE, btnDeletarE;

    private Eleitor eleitor;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_eleitor);

        txtNome = (TextView) findViewById(R.id.textVNomeE);
        txtDataNasc = (TextView) findViewById(R.id.textVDataNascE);
        txtMunicipio = (TextView) findViewById(R.id.textVMunicipioE);
        txtNomeMae = (TextView) findViewById(R.id.textVNomeMaeE);
        txtSecao = (TextView) findViewById(R.id.textVSecaoE);
        txtTitulo = (TextView) findViewById(R.id.textVTituloE);
        txtZona = (TextView) findViewById(R.id.textVZonaE);

        btnEditarE = (Button) findViewById(R.id.buttonEditarE);
        btnDeletarE = (Button) findViewById(R.id.buttonDeletarE);

        Intent intent = getIntent();
        realm = Realm.getDefaultInstance();

        String numeroDoTitulo = (String) intent.getSerializableExtra("numeroDoTitulo");
        eleitor = realm.where(Eleitor.class).equalTo("numeroDoTitulo",
                numeroDoTitulo).findFirst();

        btnDeletarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
            }
        });

        btnEditarE.setOnClickListener(new View.OnClickListener() {
            Intent intentEditar;
            @Override
            public void onClick(View view) {
                intentEditar = new Intent(DetalheEleitor.this, NovoEleitor.class);
                intentEditar.putExtra("numeroDoTitulo", eleitor.getNumeroDoTitulo());
                startActivity(intentEditar);
            }
        });

        txtNome.setText("Nome: " + eleitor.getNome());
        txtTitulo.setText("Título: " + eleitor.getNumeroDoTitulo());
        txtZona.setText("Zona: " + eleitor.getZona());
        txtSecao.setText("Secao: " + eleitor.getSecao());
        txtMunicipio.setText("Município: " + eleitor.getMunicipio());
        txtDataNasc.setText("Data de Nascimento: " + eleitor.getDataDeNascimento());
        txtNomeMae.setText("Nome da Mãe: " + eleitor.getNomeDaMae());
    }

    public void deletar(){
        realm.beginTransaction();

        eleitor.deleteFromRealm();

        realm.commitTransaction();

        Toast.makeText(this,"Eleitor deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void finish(){
        realm.close();
        super.finish();
    }
}
