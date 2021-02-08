package espe.edu.ec.MenuReport.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.hibernate.exception.GenericJDBCException;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import espe.edu.ec.MenuReport.vo.TipoPersonaVo;
import espe.edu.ec.MenuReport.dao.MenuRepository;
import espe.edu.ec.MenuReport.model.Menu;

//@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/menus")
public class MenuController {

	private byte[] bytes;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuRepository tipoPersonas;

	// Listar Todos Menu
	@RequestMapping(value = "/menuget", method = RequestMethod.GET)
	public ResponseEntity<Menu> listarMenus() throws SQLException {
		List<Menu> menu = menuRepository.findAll();
//			return menu;
		if (menu.isEmpty()) {
			return new ResponseEntity("no hay resultados", HttpStatus.OK);
		} else {
			return new ResponseEntity(menu, HttpStatus.OK);
		}

	}

	// Listar Menu padre
	@RequestMapping(value = "/menugetPadre", method = RequestMethod.GET)
	public ResponseEntity<Menu> listarMenuPadre() throws SQLException {
		List<Menu> menu = menuRepository.findAllPadre();
//			return menu;
		if (menu.isEmpty()) {
			return new ResponseEntity("no hay resultados", HttpStatus.OK);
		} else {
			return new ResponseEntity(menu, HttpStatus.OK);
		}

	}

	// Listar Menu Hijo
	@RequestMapping(value = "/menugetHijo", method = RequestMethod.GET)
	public ResponseEntity<Menu> listarMenuHijo() throws SQLException {
		List<Menu> menu = menuRepository.findAllHijo();
//			return menu;
		if (menu.isEmpty()) {
			return new ResponseEntity("no hay resultados", HttpStatus.OK);
		} else {
			return new ResponseEntity(menu, HttpStatus.OK);
		}

	}

	// Listar por Id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Menu> listarmenuid(@PathVariable Long idP) throws SQLException {
		Menu menuid = menuRepository.findByIdP(idP);
		return new ResponseEntity(menuid, HttpStatus.OK);
	}

	// listar menu por estado
	@RequestMapping(value = "/estado_noticia", method = RequestMethod.GET)
	public ResponseEntity<Menu> listarMenu() throws SQLException {
		List<Menu> MenuEstado = menuRepository.findByEstadoMenu();
		return new ResponseEntity(MenuEstado, HttpStatus.OK);
	}


//      Crea  Menu	y subMenu				
	@RequestMapping(value = "/crearMenu", method = RequestMethod.POST)
//		public ResponseEntity<Menu> crearMenu(@RequestParam("idF") Long idMf,  @RequestParam("codigoMenuF") Long codeMf, @RequestParam("nombre") String name, @RequestParam("descripcion") String description, @RequestParam("estado") String state, @RequestParam("link") String lk, @RequestParam("fechaCrea") Date dateC, @RequestParam("usuarioCrea") String userC ) throws SQLException, IOException, UnknownException, NonUniqueResultException, MultipartException, MissingServletRequestParameterException{
	public ResponseEntity<Menu> crearMenu(@RequestBody Menu menus) throws SQLException {


		Long ultimoId = menuRepository.findTopByOrderByIdPDesc().getIdP() + 1;
//		System.out.println("ultimoId  " + ultimoId);

		Long ultimoC = menuRepository.findTopByOrderByCodigoMenuDesc().getCodigoMenu() + 1;
//		System.out.println("ultimoC  " + ultimoC);

		Menu menu = new Menu();
		Menu id = menuRepository.findByIdPAndCodigoMenu(ultimoId, ultimoC);

		if (id != null) {
			System.out.println("ya existe");
			return new ResponseEntity("ya existe", HttpStatus.CREATED);
		} else {
			
			menu.setIdP(ultimoId);
			menu.setCodigoMenu(ultimoC);
			menu.setIdF(menus.getIdF());
			menu.setCodigoMenuF(menus.getCodigoMenuF());
			menu.setNombre(menus.getNombre());
//				menu.setDescripcion(description);
//				menu.setEstado(state);
//				menu.setLink(lk);
			menu.setFechaCrea(menus.getFechaCrea());
			menu.setUsuarioCrea(menus.getUsuarioCrea());
//				menu.setFechaModica(dateM);
//				menu.setUsuarioModica(userM);
//				menu.setSistemaId(SId);
//				menu.setOpcionId(OId);		
//			System.out.println("obejto a guardar idp " + menusgetIdP());
//			System.out.println("obejto a guardar idmenu " + menu..getCodigoMenu());

			menuRepository.save(menu);

			return new ResponseEntity<Menu>(menu, HttpStatus.CREATED);

		}

	}

//      Crear Contedido del Menu					
	@RequestMapping(value = "/crearContenido", method = RequestMethod.POST)
	public ResponseEntity<Menu> crearContenido( @RequestParam("imagen") MultipartFile file,
			@RequestParam("idF") Long idCf, @RequestParam("codigoMenuF") Long codeCf,
			@RequestParam("nombre") String nameC, @RequestParam("descripcion") String descriptionC,
			@RequestParam("estado") String stateC, @RequestParam("link") String lkC,
			@RequestParam("fechaCrea") Date dateCC, @RequestParam("usuarioCrea") String userCC)
			throws SQLException, IOException, UnknownException, NonUniqueResultException, MultipartException {

		Long ultimoIdC = menuRepository.findTopByOrderByIdPDesc().getIdP() + 1;
//		System.out.println("ultimoIdC  " + ultimoIdC);
		Long ultimoCM = menuRepository.findTopByOrderByCodigoMenuDesc().getCodigoMenu() + 1;
//		System.out.println("ultimoCM  " + ultimoCM);


		Menu menus = new Menu();

		Menu id = menuRepository.findByIdPAndCodigoMenu(ultimoIdC, ultimoCM);

		if (id != null) {
			System.out.println("ya existe");
			return new ResponseEntity("ya existe", HttpStatus.CREATED);
		} else {
			menus.setIdP(ultimoIdC);
			menus.setCodigoMenu(ultimoCM);
			menus.setIdF(idCf);
			menus.setCodigoMenuF(codeCf);
			menus.setNombre(nameC);
			menus.setDescripcion(descriptionC);
			menus.setEstado(stateC);
			menus.setLink(lkC);
			menus.setFechaCrea(dateCC);
			menus.setUsuarioCrea(userCC);
//				menus.setFechaModica(dateM);
//				menus.setUsuarioModica(userM);
//				menus.setSistemaId(SId);
//				menus.setOpcionId(OId);		
			this.bytes = file.getBytes();
			menus.setImagen(this.bytes);
			this.bytes = null;
			menuRepository.save(menus);

			return new ResponseEntity<Menu>(menus, HttpStatus.CREATED);

		}

	}

//      Modificar Menu
	@RequestMapping(value = "/modificar/{idM}/{codeM}", method = RequestMethod.PUT)
	public ResponseEntity<Menu> actualizarMenu(@RequestParam("imagen") MultipartFile file, @PathVariable Long idM,
			@PathVariable Long codeM, @RequestParam("idF") Long idMf, @RequestParam("codigoMenuF") Long codeMf,
			@RequestParam("nombre") String name, @RequestParam("descripcion") String description,
			@RequestParam("estado") String state, @RequestParam("link") String lk,
			@RequestParam("fechaModica") Date dateM, @RequestParam("usuarioModica") String userM,
			@RequestParam("sistemaId") Long SId, @RequestParam("opcionId") Long OId)
			throws SQLException, IOException, UnknownException, NonUniqueResultException {

		Menu id = menuRepository.findByIdPAndCodigoMenu(idM, codeM);
		id.setIdF(idMf);
		id.setCodigoMenuF(codeMf);
		id.setNombre(name);
		id.setDescripcion(description);
		id.setEstado(state);
		id.setLink(lk);
//		    id.setFechaCrea(dateC);
//		    id.setUsuarioCrea(userC);
		id.setFechaModica(dateM);
		id.setUsuarioModica(userM);
		id.setSistemaId(SId);
		id.setOpcionId(OId);

		this.bytes = file.getBytes();
		id.setImagen(this.bytes);
		this.bytes = null;
		System.out.println("body menu : " + id);
		menuRepository.save(id);
		return new ResponseEntity(id, HttpStatus.CREATED);
	}

//      Eliminar Menu logico
	@RequestMapping(value = "/eliminar/{ids}", method = RequestMethod.POST)
	public ResponseEntity<Menu> eliminarNoticia(@RequestBody Menu menu, @PathVariable Long idP) throws SQLException {
		String q = "UPDATE UZSRMENU SET estado = 'I' WHERE idP= " + idP + "";
		jdbcTemplate.update(q);
		System.out.println("Estado inactivo q : " + q);
		return new ResponseEntity(HttpStatus.CREATED);
	}

//      buscar tipo persona		
	@RequestMapping(value = "/tipoPersona/{pidm}", method = RequestMethod.GET)
	public List<TipoPersonaVo> getTipoPersona(@PathVariable int pidm) throws SQLException {
		String p = "SELECT PEBEMPL_BCAT_CODE AS CODIGO FROM PEBEMPL WHERE PEBEMPL_PIDM =" + pidm + " ";
		return jdbcTemplate.query(p, new BeanPropertyRowMapper<>(TipoPersonaVo.class));
	}

//  buscar contedido de un hjio	
@RequestMapping(value = "/contenidoIdContenido/{idF}/{codigoMenuF}", method = RequestMethod.GET)
public List<Menu> getcontenidoIdContenido(@PathVariable Long idF, @PathVariable Long codigoMenuF) throws SQLException {
   List<Menu> menuid = menuRepository.findByIdFAndCodigoMenuF(idF, codigoMenuF);
   
   return menuid;
//	return jdbcTemplate.query(p, new BeanPropertyRowMapper<>(Menu.class));
}
//buscar contedido de un hjio	2


}
