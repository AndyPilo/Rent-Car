package cu.edu.cujae.bd.dto;

public class UserDto {
	private int codUser;
	private String username;
	private String password;
	private RolDto rol;
	
	public UserDto(int codUser, String username, String password,RolDto rol) {
		super();
		this.codUser = codUser;
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public UserDto( String username, String password,RolDto rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	
	public int getCodUser() {
		return codUser;
	}
	public void setCodUser(int codUser) {
		this.codUser = codUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public RolDto getRol() {
		return rol;
	}

	public void setRol(RolDto rol) {
		this.rol = rol;
	}

}
