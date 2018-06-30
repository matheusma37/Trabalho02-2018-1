package br.edu.iff.pooa20181.trabalho02_2018_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;

public class CandidatoAdapter extends RecyclerView.Adapter<CandidatoAdapter.CandidatoViewHolder> {

    private List<Candidato> candidatos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;
    private RecyclerView.ViewHolder holder;
    private int position;

    @Override
    public CandidatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.linha_candidato, parent, false);
        CandidatoViewHolder candidatoViewHolder = new CandidatoViewHolder(view);
        return candidatoViewHolder;
    }

    @Override
    public void onBindViewHolder(CandidatoViewHolder viewHolder, int position) {
        CandidatoViewHolder candidatoViewHolder = viewHolder;
        Candidato candidato = this.candidatos.get(position);
        candidatoViewHolder.nome.setText(candidato.getNome());
        candidatoViewHolder.cargo.setText(candidato.getCargo());
        candidatoViewHolder.partido.setText(candidato.getPartido());
        candidatoViewHolder.numero.setText(candidato.getNumeroNaUrna());
    }

    @Override
    public int getItemCount() {
        return candidatos.size();
    }

    public static class CandidatoViewHolder extends RecyclerView.ViewHolder{
        private final TextView nome;
        private final TextView partido;
        private final TextView cargo;
        private final TextView numero;

        public CandidatoViewHolder(View view){
            super(view);
            nome = (TextView) itemView.findViewById(R.id.nomeCandidato);
            partido = (TextView) itemView.findViewById(R.id.partidoCandidato);
            cargo = (TextView) itemView.findViewById(R.id.cargoCandidato);
            numero = (TextView) itemView.findViewById(R.id.numeroCandidato);
        }
    }
}
