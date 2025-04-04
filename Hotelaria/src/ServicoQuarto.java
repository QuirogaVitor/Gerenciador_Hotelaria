/**package src;

public class ServicoQuarto {
    public String nomeFuncionario;
    private int quartoId;
    private String tipoServico;

    public ServicoQuarto(int quartoId, String tipoServico) {
        this.quartoId = quartoId;
        this.tipoServico = tipoServico;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }


    @Override
    public String toString() {
        return "ServicoQuarto [quartoId=" + quartoId + ", tipoServico=" + tipoServico +  "]";
    }

    
}
**/