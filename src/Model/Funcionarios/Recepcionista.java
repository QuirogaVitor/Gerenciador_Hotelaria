package Model.Funcionarios;

import Model.Enums.Cargo;

public class Recepcionista extends Funcionario {

    public Recepcionista()
    {
        this.setCargo(Cargo.RECEPCIONISTA);
    }

    @Override
    public void RealizarCadastro() {
        // ADD: Tratamento para tela de cadastrar f
    }
}
