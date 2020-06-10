package Modele;

public class ResetMotDePass {
	private int idReset;
	private String ResetCode;
	private String email;
	
	public ResetMotDePass(int idReset, String resetCode, String email) {
		super();
		this.idReset = idReset;
		ResetCode = resetCode;
		this.email = email;
	}
	
	public ResetMotDePass(String resetCode, String email) {
		super();
		ResetCode = resetCode;
		this.email = email;
	}

	void setIdReset(int idReset) {
		this.idReset = idReset;
	}
	void setResetCode(String resetCode) {
		ResetCode = resetCode;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	public int getIdReset() {
		return idReset;
	}
	public String getResetCode() {
		return ResetCode;
	}
	public String getEmail() {
		return email;
	}
	
}
