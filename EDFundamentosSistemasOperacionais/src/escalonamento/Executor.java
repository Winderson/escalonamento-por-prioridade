package escalonamento;

import java.util.ArrayList;
import java.util.List;

public class Executor {

    public static void main(String[] args){
        Algoritmo algoritmo = new Algoritmo();

        Processo[] processos = new Processo[3];

        Processo processo1 = new Processo();
        processo1.setPrioridade(2);
        Processo processo2 = new Processo();
        processo2.setPrioridade(1);
        Processo processo3 = new Processo();
        processo3.setPrioridade(5);
        Processo processo4 = new Processo();
        processo4.setPrioridade(-1);


        processos[0] = processo1;
        processos[1] = processo2;
        processos[2] = processo3;
//        processos.add(processo3);
//        processos.add(processo4);

        algoritmo.algoritmoPrioridade();


//        algoritmo.lerArquivo();


//        for(Processo  processo : processos){
//            System.out.println("Prioridade --> "+processo.getPrioridade());
//        }


    }

}
