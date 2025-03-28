package src;


//ServicoQuarto.java

public class ServicoQuarto {
    private int quartoId;
    private String tipoServico;
    private String descricao;

    // Construtor
    public ServicoQuarto(int quartoId, String tipoServico, String descricao) {
        this.quartoId = quartoId;
        this.tipoServico = tipoServico;
        this.descricao = descricao;
    }

    // Getters e setters
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
