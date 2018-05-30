package escalonamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Algoritmo {

    private int tamanho;

    public void algoritmoPrioridade() {

        List<Processo> processosArquivo = lerArquivo();
        tamanho = processosArquivo.size();
        Processo[] processosFilaExecucao = new Processo[tamanho];
        int somaTempo = 0;
        for (Processo processo : processosArquivo) {
            somaTempo += processo.getTempoCriacao();
        }

        int i = 0;
        int contadorProcesso = 0;
        Processo processoExecucao = null;
        List<String> processosJaVerificados = new ArrayList<String>();

        for (int tempo = 0; tempo < somaTempo+2; tempo++) {
            for (Processo processo : processosArquivo) {
                if (!processosJaVerificados.contains(processo.getIdProcesso()) && processo.getTempoCriacao() == tempo) {
                    processosJaVerificados.add(processo.getIdProcesso());

                    // Verifica se tem prioridade maior que o processo que está executando
                    if(processoExecucao != null){
                        if(processo.getPrioridade() < processoExecucao.getPrioridade()) {
                            processosFilaExecucao[i] = processoExecucao;
                            i++;
                            processoExecucao = processo;
                        } else {
                            processosFilaExecucao[i] = processo;
                        }
                    } else {
                        processoExecucao = processo;
                    }
                    this.aplicaPrioridade(processosFilaExecucao);
                } else {
                    if (processoExecucao != null) {
                        if(processoExecucao.getTempoCPU() == 0){
                            eliminarProcessoFilaExecucao(processosFilaExecucao, processoExecucao);
                            this.aplicaPrioridade(processosFilaExecucao);
                            processoExecucao = verificaProcessoExecucao(processosFilaExecucao);
                            if(processoExecucao == null){
                                break;
                            }
                        }
                    }
                }
            }
            if (processoExecucao != null) {
                System.out.println("Tempo: " + tempo + " --- Id: " + processoExecucao.getIdProcesso());
                processoExecucao.setTempoCPU(processoExecucao.getTempoCPU() - 1);
            } else {
                System.out.println("Tempo: " + tempo + " --- Id: Nenhum");
            }
        }


        //TEMPO DE CRIAÇÂO;ID PROCESSO;PRIORIDADE;TEMPO DE CPU

//        Processo processoExecucao = new Processo();
//        List<Processo> processoEmExecucao = new ArrayList<Processo>();
//        int prioridade = 0;
//
//        for (Processo processo : processos) {
//            if (processo.getPrioridade() < prioridade) {
//                processo.setTempoExecucao(processoExecucao.getTempoExecucao() + 1);
//                if (processo.getTempoExecucao() == processo.getTempoCPU()) {
//                    processo.setFinalizado(true);
//                }
//                System.out.println(processo.getTempoExecucao() + " | " + processo.getIdProcesso());
//                processoEmExecucao.add(processo);
//            } else {
//                processoEmExecucao.get(0);
//            }
//        }

        // Leitura de arquivo

    }

    public Processo verificaProcessoExecucao(Processo[] processosFilaExecucao, Processo processoExecucao) {
        this.aplicaPrioridade(processosFilaExecucao);
        for (int i = 0; i < processosFilaExecucao.length; i++) {
            if (processosFilaExecucao[i] != null && processosFilaExecucao[i].getPrioridade() < processoExecucao.getPrioridade()) {
                Processo processoAux = processosFilaExecucao[i];
                processosFilaExecucao[i] = processoExecucao;
                return processoAux;
            }
        }
        return processoExecucao;
    }

    public Processo verificaProcessoExecucao(Processo[] processosFilaExecucao) {
        this.aplicaPrioridade(processosFilaExecucao);
        for (int i = 0; i < processosFilaExecucao.length; i++) {
            if (processosFilaExecucao[i] != null){
                return processosFilaExecucao[i];
            }
        }
        return null;
    }


    public void eliminarProcessoFilaExecucao(Processo[] processosFilaExecucao, Processo processo){

        if(processosFilaExecucao.length == 1){
            processosFilaExecucao[0] = null;
        } else {
            for(int i=0; i< processosFilaExecucao.length ; i++){
                if(processosFilaExecucao[i] != null) {
                    if (processosFilaExecucao[i].getIdProcesso().equals(processo.getIdProcesso())) {
                        processosFilaExecucao[i] = null;
                    }
                }
            }
        }
    }


    public void aplicaPrioridade(Processo[] processosFila) {
        int j;
        Processo key;
        int i;

        for (j = 1; j < processosFila.length; j++) {
            key = processosFila[j];
            if (key != null && processosFila[j-1] != null) {
                for (i = j - 1; (i >= 0) && (processosFila[i].getPrioridade() > key.getPrioridade()); i--) {
                    processosFila[i + 1] = processosFila[i];
                }
                processosFila[i + 1] = key;
            }
        }

    }


    public List<Processo> lerArquivo() {
        try {
            FileReader arq = new FileReader("lib/escalonados.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String[] processoAux = null;
            List<Processo> processos = new ArrayList<Processo>();
            while (linha != null) {
                Processo processo = new Processo();
                processoAux = linha.split(";");
                if (processoAux.length == 4) {
                    processo.setTempoCriacao(Integer.parseInt(processoAux[0]));
                    processo.setIdProcesso(processoAux[1]);
                    processo.setPrioridade(Integer.parseInt(processoAux[2]));
                    processo.setTempoCPU(Integer.parseInt(processoAux[3]));
                    processos.add(processo);
                    linha = lerArq.readLine();
                } else {
                    System.out.println("Os dados do arquivo não estão preenchidos corretamente.");
                    return new ArrayList<Processo>();
                }

            }
            arq.close();
            return processos;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao ler o arquivo");
        }
        return new ArrayList<Processo>();
    }

}





