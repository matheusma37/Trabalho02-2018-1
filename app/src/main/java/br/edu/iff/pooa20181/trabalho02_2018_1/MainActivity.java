package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerMenu);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.array_menu,
                R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int opcao = spinner.getSelectedItemPosition();
                if(opcao == 0){

                }else if(opcao == 1){

                }
            }
        });
    }
}
