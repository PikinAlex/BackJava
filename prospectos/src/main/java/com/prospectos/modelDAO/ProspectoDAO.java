package com.prospectos.modelDAO;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prospectos.interfaces.ProspectoInterface;
import com.prospectos.model.Documentos;
import com.prospectos.model.Prospecto;

@Repository
public class ProspectoDAO implements ProspectoInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String, Object>> lista = template.queryForList("select * from prospectos");
		return lista;
	}

	@Override
	public List<Map<String, Object>> listarId(int id) {
		List<Map<String, Object>> list = template.queryForList("select * from prospectosdocumentos where id_prospecto =?", id);
		return list;
	}

	@Override
	public int add(Prospecto p) {
		String sql = "insert into prospectos(nombre,apellido_p,apellido_m,calle,numero,colonia,codigo_postal,telefono,rfc,status)values(?,?,?,?,?,?,?,?,?,?)";
		return template.update(sql, p.getNombre(), p.getApellido_p(), p.getApellido_m(),p.getCalle(),p.getNumero(),p.getColonia(),p.getCodigo_postal(),p.getTelefono(),p.getRfc(),p.getStatus());
	}

	@Override
	public int edit(Prospecto p) {
		String sql = "update prospectos set nombre=?, apellido_p=?,apellido_m=?,calle=?,numero=?,colonia=?,codigo_postal=?,telefono=?,rfc=?,observaciones=?,status=? where id_prospecto=?";
		return template.update(sql, p.getNombre(), p.getApellido_p(),p.getApellido_m(),p.getCalle(),p.getNumero(),p.getColonia(),p.getCodigo_postal(),p.getTelefono(),p.getRfc(),p.getObservaciones(),p.getStatus(),p.getId());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from prospectos where id_prospecto=?";
		return template.update(sql, id);

	}

	@Override
	public int deleteDoc(int id) {
		String sql="delete from prospectosdocumentos where id_documento=?";
		return template.update(sql,id);
	}

	@Override
	public int getMax() {
		String sql="select MAX(id_prospecto) from prospectos";
		if(template.queryForObject(sql, int.class)==null) {
			return 0;
		}
		int r;
		r=template.queryForObject(sql, int.class);
		return  r;
	}

	@Override
	public int setDocumentos(Documentos d) {
		String sql="insert into prospectosdocumentos(id_prospecto, documento, nombre) values (?,?,?) ";		
		return template.update(sql,d.getId_prospecto(), d.getDocumento(), d.getNombre());
	}

}
