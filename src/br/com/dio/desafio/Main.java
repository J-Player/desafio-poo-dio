package br.com.dio.desafio;

import br.com.dio.desafio.dominio.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static br.com.dio.desafio.dominio.Curso.criarCurso;
import static br.com.dio.desafio.dominio.Dev.criarDev;

public class Main {

    private static final String SEPARADOR = "-".repeat(50);

    public static void main(String[] args) {
        Curso cursoJava = criarCurso("curso java", "descrição curso java", 8);
        Curso cursoJS = criarCurso("curso js", "descrição curso js", 4);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("mentoria de java");
        mentoria.setDescricao("descrição mentoria java");
        mentoria.setData(LocalDate.now());

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");
        Set<Conteudo> conteudos = bootcamp.getConteudos();
        conteudos.addAll(List.of(cursoJava, cursoJS, mentoria));

        Dev devCamila = criarDev("Camila", bootcamp);
        devCamila.progredir(2);

        Dev devJoao = criarDev("Joao", bootcamp);
        devJoao.progredir(3);

        imprimirDev(devCamila);
        imprimirDev(devJoao);

    }

    private static void imprimirDev(Dev dev) {
        System.out.println("INFORMAÇÕES SOBRE O DEV");
        System.out.printf("\tNOME: %s%n", dev.getNome().toUpperCase());
        System.out.printf("\tEXPERIÊNCIA: %.1f%n", dev.calcularTotalXp());
        System.out.printf("\tCONTEÚDOS INSCRITOS DE %s: ", dev.getNome().toUpperCase());
        if (!dev.getConteudosInscritos().isEmpty()) {
            System.out.println();
            dev.getConteudosInscritos().forEach(Main::imprimirConteudo);
        } else System.out.println("Não há inscrições de conteúdo.");
        System.out.printf("\tCONTEÚDOS CONCLUÍDOS DE %s: ", dev.getNome().toUpperCase());
        if (!dev.getConteudosConcluidos().isEmpty()) {
            System.out.println();
            dev.getConteudosConcluidos().forEach(Main::imprimirConteudo);
        } else System.out.println("Não há conteúdos concluídos.");
    }

    private static void imprimirConteudo(Conteudo conteudo) {
        System.out.printf("\t\tTÍTULO: %s%n", conteudo.getTitulo());
        System.out.printf("\t\tDESCRIÇÃO: %s%n", conteudo.getDescricao());
        if (conteudo instanceof Curso) {
            System.out.printf("\t\tCARGA HORÁRIA: %d%n", ((Curso) conteudo).getCargaHoraria());
        } else if (conteudo instanceof Mentoria) {
            LocalDate data = ((Mentoria) conteudo).getData();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.printf("\t\tDATA: %s%n", data.format(dateTimeFormatter));
        }
        System.out.printf("\t%s%n", SEPARADOR);
    }

}
