package Model.Funcionarios;

import Model.Enums.Cargo;
import Model.Enums.Funcao;


public class Camareira extends Funcionario {

    enum TipoServico {
        SemServico,
        Alimento,
        Arrumacao,
        Utensilios
    }

    Funcao funcaoAtual;
    TipoServico servicoAtual;

    public Camareira() {
        this.setCargo(Cargo.CAMAREIRA);
        this.funcaoAtual = Funcao.Aguardando;
        servicoAtual = TipoServico.SemServico;
    }

    public void limparQuarto(int numQuarto) {
        this.funcaoAtual = Funcao.Atendendo;
        this.servicoAtual = TipoServico.Arrumacao;
    }

    public void atenderServicoDeQuarto(int numQuarto) {
        if (this.isOcupado())
            throw new RuntimeException("Funcionário já está ocupado");
        this.Ocupar();
    }
}

