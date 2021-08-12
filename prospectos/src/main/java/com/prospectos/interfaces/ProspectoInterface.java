package com.prospectos.interfaces;

import java.util.List;
import java.util.Map;

import com.prospectos.model.Prospecto;
import com.prospectos.model.Documentos;

public interface ProspectoInterface {
	public List<Map<String, Object>> listar();

	public List<Map<String, Object>> listarId(int id);

	public int add(Prospecto p);

	public int edit(Prospecto p);

	public int delete(int id);
	
	public int deleteDoc(int id);
	public int getMax();
	public int setDocumentos(Documentos d);

}
