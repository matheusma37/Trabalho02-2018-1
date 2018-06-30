package br.edu.iff.pooa20181.trabalho02_2018_1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;

public class CandidatoAdapter extends ArrayAdapter<Candidato> {

    private final Context context;
    private final ArrayList<Candidato> candidatos;

    public CandidatoAdapter(Context context, ArrayList<Candidato> candidatos) {
        super(context, R.layout.linha_candidato, candidatos);
        this.context = context;
        this.candidatos = candidatos;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha_candidato, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.nomeCandidato);
        TextView partido = (TextView) rowView.findViewById(R.id.partidoCandidato);
        TextView cargo = (TextView) rowView.findViewById(R.id.cargoCandidato);
        TextView numero = (TextView) rowView.findViewById(R.id.numeroCandidato);
        nome.setText(candidatos.get(position).getNome());
        partido.setText(candidatos.get(position).getPartido());
        cargo.setText(candidatos.get(position).getCargo());
        numero.setText(candidatos.get(position).getNumeroNaUrna());
        return rowView;
    }
}
