package br.com.teste.todolist.enuns;


public enum TypeProfileEnum {

    GESTOR((byte) 1, "GESTOR","Gestor"),
    ADM((byte)2, "ADM","Administrador"),
    USARIO((byte) 3 , "USUARIO","Usuario");

    private Byte cod;
    private final String authority;
    private final String descricao;

    private TypeProfileEnum(byte cod, String authority, String descricao){
        this.cod = cod;
        this.authority = authority;
        this.descricao = descricao;
    }

    public Byte getCod() {
        return cod;
    }

    public void setCod(Byte cod) {
        this.cod = cod;
    }

    public String getAuthority() {
        return authority;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TypeProfileEnum toEnum(Byte cod) {
        if (cod == null) {
            return null;
        }

        for (TypeProfileEnum x : TypeProfileEnum.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("id inválido: " + cod);
    }

    public static TypeProfileEnum toEnum(String desc){
        if(desc == null || desc.isEmpty()){
            return null;
        }

        for(TypeProfileEnum x : TypeProfileEnum.values()){
            if(desc.equals(x.getDescricao())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + desc);
    }
}
