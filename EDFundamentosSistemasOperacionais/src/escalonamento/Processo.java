package escalonamento;

public class Processo {

    // Váriaveis
    private int tempoCriacao;
    private String idProcesso;
    private int prioridade;
    private int tempoCPU;
    private int tempoExecucao;
    private boolean finalizado;


    // Getters and Setters

    public Processo(){
        this.tempoCriacao = 0;
        this.idProcesso = "";
        this.prioridade = 0;
        this.tempoCPU = 0;
        this.tempoExecucao = 0;
        this.finalizado = false;
    }


    public int getTempoCriacao() {
        return tempoCriacao;
    }

    public void setTempoCriacao(int tempoCriacao) {
        this.tempoCriacao = tempoCriacao;
    }

    public String getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(String idProcesso) {
        this.idProcesso = idProcesso;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getTempoCPU() {
        return tempoCPU;
    }

    public void setTempoCPU(int tempoCPU) {
        this.tempoCPU = tempoCPU;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
