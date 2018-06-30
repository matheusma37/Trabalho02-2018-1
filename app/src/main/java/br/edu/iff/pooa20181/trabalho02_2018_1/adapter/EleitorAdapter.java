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
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;

public class EleitorAdapter extends ArrayAdapter<Eleitor> {

    private final Context context;
    private final ArrayList<Eleitor> eleitores;

    public EleitorAdapter(Context context, ArrayList<Eleitor> eleitores) {
        super(context, R.layout.linha_eleitor, eleitores);
        this.context = context;
        this.eleitores = eleitores;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha_eleitor, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.nomeEleitor);
        TextView titulo = (TextView) rowView.findViewById(R.id.numeroTituloEleitor);
        TextView secao = (TextView) rowView.findViewById(R.id.secaoEleitor);
        TextView zona = (TextView) rowView.findViewById(R.id.zonaEleitor);
        nome.setText(eleitores.get(position).getNome());
        titulo.setText(eleitores.get(position).getNumeroDoTitulo());
        secao.setText(eleitores.get(position).getSecao());
        zona.setText(eleitores.get(position).getZona());
        return rowView;
    }
}
