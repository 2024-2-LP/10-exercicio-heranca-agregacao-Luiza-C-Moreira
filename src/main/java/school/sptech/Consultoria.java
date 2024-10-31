package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
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
                .min(Comparator.comparingDouble(Desenvolvedor::calcularSalario))
                .orElse(null);
    }


    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){
        return desenvolvedores.stream()
                .filter(dev ->(dev instanceof DesenvolvedorWeb &&(
                        ((DesenvolvedorWeb) dev).getBackend().equals(tecnologia) ||
                                ((DesenvolvedorWeb) dev).getFrontend().equals(tecnologia) ||
                                ((DesenvolvedorWeb) dev).getSgbd().equals(tecnologia)))
                        ||
                        (dev instanceof DesenvolvedorMobile && (
                                ((DesenvolvedorMobile) dev).getPlataforma().equals(tecnologia) ||
                                        ((DesenvolvedorMobile) dev).getLinguagem().equals(tecnologia)))
                ).collect(Collectors.toList());
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){
        Double total = 0.0;
        List<Desenvolvedor> dev = buscarPorTecnologia(tecnologia);

        for (int i = 0; i < dev.size(); i++) {
            total = dev.get(i).calcularSalario() + total;
        }

        return total;
    }






}
