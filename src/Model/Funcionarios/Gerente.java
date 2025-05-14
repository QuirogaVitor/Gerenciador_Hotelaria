package Model.Funcionarios;

import Model.Enums.Cargo;

public class Gerente extends Funcionario {

    public Gerente(){
        this.setCargo(Cargo.GERENTE);
    }

    @Override
    public void RealizarCadastro(){
        //Chamar da tela de cadastrar funcionários como gerente
    }

}
