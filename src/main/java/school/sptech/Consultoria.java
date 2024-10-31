package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.Comparator;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
    }

    public Consultoria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public void contratar(Desenvolvedor desenvolvedor){
        if(desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }


    public void contratarFullstack(DesenvolvedorWeb desenvolvedor){ if (desenvolvedor.isFullstack()){
        desenvolvedores.add(desenvolvedor);
    }
    }

    public Double getTotalSalarios(){
        Double salario = 0.0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            salario += desenvolvedores.get(i).calcularSalario();
        } return salario;
    }

    public Integer qtdDesenvolvedoresMobile(){
        return this.desenvolvedores.stream()
                .filter(devDaVez-> devDaVez instanceof DesenvolvedorMobile)
                .toList().size();
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        return this.desenvolvedores.stream()
                .filter(dev->dev.calcularSalario() >= salario).toList();

    }

    public Desenvolvedor buscarMenorSalario(){
        return this.desenvolvedores.stream()
        .sorted(Comparator.comparingDouble(Desenvolvedor::calcularSalario)).toList().get(0);
    }

/*
    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){}

*/




}
