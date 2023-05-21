package cu.edu.cujae.bd.dto;

public class RolDto {
    private int codRol;
    private String rol;

    public RolDto(int codRol, String rol) {
        this.codRol = codRol;
        this.rol = rol;
    }

    public RolDto( String rol) {
        this.rol = rol;
    }

    public int getCodRol() {
        return codRol;
    }
    public void setCodRol(int codRol) {
        this.codRol = codRol;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
