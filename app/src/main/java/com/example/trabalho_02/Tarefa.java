package com.example.trabalho_02;

public class Tarefa {
    private static int contChave = 0;

    private String nome;
    private String turno;
    private int chave;

    public Tarefa() {
    }

    public Tarefa(String nome, String turno) {
        this.nome = nome;
        this.turno = turno;
        this.chave = contChave++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public String toString() {
        return nome + " pela " + turno + " | " + "Chave: " + chave;
    }
}
