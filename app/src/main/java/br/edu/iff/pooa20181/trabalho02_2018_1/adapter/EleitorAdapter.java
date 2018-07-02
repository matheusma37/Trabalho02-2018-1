package br.edu.iff.pooa20181.trabalho02_2018_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;

public class EleitorAdapter extends RecyclerView.Adapter<EleitorAdapter.EleitorViewHolder> {

    private List<Eleitor> eleitores;
    private Context context;
    private ClickRecyclerViewListener clickRecyclerViewListener;

    public EleitorAdapter(List<Eleitor> eleitores, Context context,
                          ClickRecyclerViewListener clickRecyclerViewListener) {
        this.eleitores = eleitores;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public EleitorAdapter.EleitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.linha_eleitor, parent, false);
        EleitorAdapter.EleitorViewHolder eleitorViewHolder = new EleitorAdapter.EleitorViewHolder(view);
        return eleitorViewHolder;
    }

    @Override
    public void onBindViewHolder(EleitorAdapter.EleitorViewHolder viewHolder, int position) {
        EleitorAdapter.EleitorViewHolder eleitorViewHolder = viewHolder;
        Eleitor eleitor = this.eleitores.get(position);
        eleitorViewHolder.nome.setText("Nome: " + eleitor.getNome());
        eleitorViewHolder.titulo.setText("Título: " + eleitor.getNumeroDoTitulo());
        eleitorViewHolder.secao.setText("Seção: " + eleitor.getSecao());
        eleitorViewHolder.zona.setText("Zona: " + eleitor.getZona());
    }

    @Override
    public int getItemCount() {
        return eleitores.size();
    }

    public class EleitorViewHolder extends RecyclerView.ViewHolder{
        private final TextView nome;
        private final TextView titulo;
        private final TextView secao;
        private final TextView zona;

        public EleitorViewHolder(View view){
            super(view);
            nome = (TextView) itemView.findViewById(R.id.nomeEleitor);
            titulo = (TextView) itemView.findViewById(R.id.numeroTituloEleitor);
            secao = (TextView) itemView.findViewById(R.id.secaoEleitor);
            zona = (TextView) itemView.findViewById(R.id.zonaEleitor);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));
                }
            });
        }
    }
}
