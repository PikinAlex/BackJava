package com.prospectos.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.prospectos.service.FilesStorageService;
import com.prospectos.service.ProspectoService;
import com.prospectos.message.Response;
import com.prospectos.message.ResponseMessage;
import com.prospectos.model.Prospecto;
import com.prospectos.model.Documentos;
import com.prospectos.model.FileInfo;

@RestController
@RequestMapping(path="/prospectos")
public class ProspectoController {

	@Autowired
	ProspectoService service;
	
	@GetMapping("/listar")
	public List<Map<String,Object>>listar(){
		return service.listar();
	}
	
	@PostMapping("/agregar")
	public Response save(@RequestBody Prospecto p,Model model) {
		Response response= new Response();
		int r;
		try {
			r = service.add(p);
			
		}catch(Exception e) {
			r=0;
			System.out.println(e.toString());
		}
		response.setValue(r);
		return response;
		/*try {
			service.add(p);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Se agrego con exito"));
		}catch(Exception e) {
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.toString()));
		}*/
		
	}
	
	@PostMapping("/actualizar/{id}")
	public String save(@RequestBody Prospecto p,@PathVariable int id,Model model) {
		p.setId(id);
		int r=service.edit(p);
		if(r==0) {
			return "No Actualizado!";
		}
		return "Actualizado con exito!";
	}
	@PostMapping("/eliminar/{id}")
	public Response delete(@PathVariable int id,Model model) {
		int r;
		r=service.delete(id);
		Response response= new Response();
		response.setValue(r);
		return response;
	}
	
	@GetMapping("/listarDocs/{id}")
	public List<Map<String, Object>> getDocs(@PathVariable int id,Model model) {
		return service.listarId(id);	
	}
	
	@PostMapping("/setDocumentos")
	public int saveDocs(@RequestBody Documentos d,Model model) {
		return service.setDocumentos(d);	
	}
	
	@PostMapping("/eliminarDoc/{id}")
	public Response deleteDoc(@PathVariable int id,Model model) {
		int r;
		r=service.deleteDoc(id);
		Response response= new Response();
		response.setValue(r);
		return response;
	}
	
	@GetMapping("/getMaxID")
	public Response get(Model model) {
		int r;
		r =service.getMax();
		Response response = new Response();
		response.setValue(r);
		return response;
	}
	
	@Autowired
	  FilesStorageService storageService;
	  
	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	      storageService.save(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      
	  
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	  @GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(ProspectoController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
}
