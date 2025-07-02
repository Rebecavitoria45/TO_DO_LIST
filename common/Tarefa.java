package common;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private int id;
    private String descricao;

    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return id + " - " + descricao;
    }
}
