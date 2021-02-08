package espe.edu.ec.MenuReport.vo;

import java.io.Serializable;

public class TipoPersonaVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String CODIGO;
	
	public TipoPersonaVo() {}
	public TipoPersonaVo(String CODIGO) {
		this.CODIGO= CODIGO;
	}
	public String getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}
	
	
}
