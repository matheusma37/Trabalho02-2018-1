package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Candidato extends RealmObject implements Serializable {
    private String nome, partido, cargo, estado, municipio;
    @PrimaryKey
    private String numeroNaUrna;
    private long numeroDeVotos;

    public Candidato(){}

    public Candidato(String nome, String partido,
                     String cargo, String numeroNaUrna,
                     String estado, String municipio,
                     long numeroDeVotos) {
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
        this.numeroNaUrna = numeroNaUrna;
        this.estado = estado;
        this.municipio = municipio;
        this.numeroDeVotos = numeroDeVotos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNumeroNaUrna() {
        return numeroNaUrna;
    }

    public void setNumeroNaUrna(String numeroNaUrna) {
        this.numeroNaUrna = numeroNaUrna;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public long getNumeroDeVotos() {
        return numeroDeVotos;
    }

    public void setNumeroDeVotos(long numeroDeVotos) {
        this.numeroDeVotos = numeroDeVotos;
    }
}
