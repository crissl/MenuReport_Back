package espe.edu.ec.MenuReport.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import oracle.sql.CLOB;



@Entity
@Table(name = "UZSRMENU" )
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@Basic(optional = false)
	@Column(name = "UZSTSISTEMA_ID")
	private Long idP;
	
	@Basic(optional = false)
	@Column(name = "UZSRMENU_CODE")
	@NotNull
	private Long codigoMenu;
	
//	@Basic(optional = false)
	@Column(name = "UZSTSISTEMA_ID_UZSR")
	private Long idF;
	
//	@Basic(optional = false)
	@Column(name = "UZSRMENU_CODE_UZSR")
	private Long codigoMenuF;
	
	@Column(name = "UZSRMENU_NOMBRE")
	private String nombre;
	
	@Column(name = "UZSRMENU_DESCRIPCION")
	private String descripcion;
	
	@Column(name = "UZSRMENU_ESTADO")
	private String estado;
	
	@Column(name = "UZSRMENU_LINK")
	private String link;
	
	@Lob
	@Column(name = "UZSRMENU_IMAGEN")
	private byte[] imagen;
	
	@Temporal (TemporalType.TIMESTAMP)
	@Column(name = "UZSRMENU_FECHA_CREA")
	private Date fechaCrea;
	
	@Column(name = "UZSRMENU_USUARIO_CREA")
	private String usuarioCrea;
	
	@Temporal (TemporalType.TIMESTAMP)
	@Column(name = "UZSRMENU_FECHA_MODIFICA")
	private Date fechaModica;
	
	@Column(name = "UZSRMENU_USUARIO_MODIFICA")
	private String usuarioModica;
	
//    @Basic(optional = false)
    @Column(name = "SIS_ID")
    private Long sistemaId;
    
//    @Basic(optional = false)
    @Column(name = "OPC_ID")
    private Long opcionId;

	public Long getIdP() {
		return idP;
	}

	public void setIdP(Long idP) {
		this.idP = idP;
	}

	public Long getCodigoMenu() {
		return codigoMenu;
	}

	public void setCodigoMenu(Long codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	public Long getIdF() {
		return idF;
	}

	public void setIdF(Long idF) {
		this.idF = idF;
	}

	public Long getCodigoMenuF() {
		return codigoMenuF;
	}

	public void setCodigoMenuF(Long codigoMenuF) {
		this.codigoMenuF = codigoMenuF;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}	

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] bytes) {
		this.imagen = bytes;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFechaModica() {
		return fechaModica;
	}

	public void setFechaModica(Date fechaModica) {
		this.fechaModica = fechaModica;
	}

	public String getUsuarioModica() {
		return usuarioModica;
	}

	public void setUsuarioModica(String usuarioModica) {
		this.usuarioModica = usuarioModica;
	}

	public Long getSistemaId() {
		return sistemaId;
	}

	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	public Long getOpcionId() {
		return opcionId;
	}

	public void setOpcionId(Long opcionId) {
		this.opcionId = opcionId;
	}

	
}
