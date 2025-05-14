package Model.Enums;

public enum Cargo {
    GERENTE(1),
    RECEPCIONISTA(2),
    CAMAREIRA(3);

    private final int id;

    Cargo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Cargo fromId(int id) {
        for (Cargo tipo : values()) {
            if (tipo.getId() == id) return tipo;
        }
        throw new IllegalArgumentException("TipoFuncionario inválido: " + id);
    }
}