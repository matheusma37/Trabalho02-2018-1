package br.edu.iff.pooa20181.trabalho02_2018_1.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Eleitor extends RealmObject implements Serializable {
    private String nome, nomeDaMae, zona, secao, municipio, dataDeNascimento;
    @PrimaryKey
    private String numeroDoTitulo;

    public Eleitor(){}

    public Eleitor(String nome, String nomeDaMae,
                   String numeroDoTitulo, String zona,
                   String secao, String municipio,
                   String dataDeNascimento) {
        this.nome = nome;
        this.nomeDaMae = nomeDaMae;
        this.numeroDoTitulo = numeroDoTitulo;
        this.zona = zona;
        this.secao = secao;
        this.municipio = municipio;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getNumeroDoTitulo() {
        return numeroDoTitulo;
    }

    public void setNumeroDoTitulo(String numeroDoTitulo) {
        this.numeroDoTitulo = numeroDoTitulo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
