package com.prospectos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospectos.interfaces.ProspectoInterface;
import com.prospectos.model.Prospecto;
import com.prospectos.modelDAO.ProspectoDAO;
import com.prospectos.model.Documentos;

@Service
public class ProspectoService implements ProspectoInterface {
	
	@Autowired
	ProspectoDAO dao;

	@Override
	public List<Map<String, Object>> listar() {
		
		return dao.listar();
		
	}

	@Override
	public List<Map<String, Object>> listarId(int id) {
		return dao.listarId(id);
	}

	@Override
	public int  add(Prospecto p) {
		return dao.add(p);
	}

	@Override
	public int  edit(Prospecto p) {
		return dao.edit(p);
	}

	@Override
	public int delete(int id) {
	
		return dao.delete(id);
	}
	
	@Override
	public int deleteDoc(int id) {
		return dao.deleteDoc(id);
	}
	
	@Override
	public int setDocumentos(Documentos d) {
		return dao.setDocumentos(d);
	}
	
	@Override
	public int getMax() {		
		return dao.getMax();
	}
	
	


}
