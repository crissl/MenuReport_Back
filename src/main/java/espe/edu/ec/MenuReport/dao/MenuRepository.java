package espe.edu.ec.MenuReport.dao;

import java.util.List;

import org.hibernate.NonUniqueResultException;
//import javax.persistence.NonUniqueResultException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import espe.edu.ec.MenuReport.model.Menu;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
	List<Menu> findAll();
	@Query(value = "SELECT * FROM UZSRMENU order by UZSTSISTEMA_ID", nativeQuery = true)
//        Menu findAllMenu();
	 Menu findByIdP (Long idP);
	 Menu findByIdPAndCodigoMenu (Long idP,Long condigoMenu);
	 List<Menu> findByIdFAndCodigoMenuF (Long idF,Long condigoMenuF);
	 Menu findTopByOrderByIdPDesc();
	 Menu findTopByOrderByCodigoMenuDesc();
	 //List<Menu> findByIdFAndCodigoMenuF(Long a,Long b);
	    

	    
//		@Query(value = "SELECT COUNT(UZSTSISTEMA_ID) FROM  UTIC.UZSRMENU order by UZSTSISTEMA_ID", nativeQuery = true)
//		Menu findTopByOrderByIdDesc();
//	    Menu findByCodigoMenu (Long codigoMenu);
//	@Query(value = "SELECT *FROM UTIC.UZSRMENU order by UZSRMENU_CODE", nativeQuery = true)
//	    Menu findTopByOrderByIdDesc1();
	
 
//	@Query(value ="select * FROM UZSRMENU m WHERE m.UZSTSISTEMA_ID =:id  ORDER BY id ASC", nativeQuery = true)
//	@Query("FROM Menu m WHERE m.idP =:id  ORDER BY idP ASC")
//	Optional<Menu> findAllById(@Param("id") Long idP);
	
	
	@Query("FROM Menu m WHERE m.estado ='A' ORDER BY idP ASC")
	List<Menu> findByEstadoMenu();
	@Query("FROM Menu m WHERE m.idF =0 AND m.codigoMenuF =0 ORDER BY idP ASC")
	List<Menu> findAllPadre();
	@Query("FROM Menu m WHERE m.idF !=0 AND m.codigoMenuF =0 ORDER BY idP ASC")
	List<Menu> findAllHijo();
	@Query("FROM Menu m WHERE m.idF !=0 AND m.codigoMenuF !=0 ORDER BY idP ASC")
	List<Menu> findAllContenido();

}
