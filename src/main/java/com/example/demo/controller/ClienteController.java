package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("clientes/")
public class ClienteController {

	@Autowired
	ClienteService clienteService;		

	/**
	 * Agregar una colección de clientes, si es solo uno será una colección con un solo cliente
	 * 
	 * @param clientes
	 * @return
	 */
	@PostMapping("agregar")
	public ResponseEntity<?> addCollection(@RequestBody Collection<Cliente> clientes) {

		Collection<Cliente> nuevosClientes = clienteService.addCollection(clientes);

		return new ResponseEntity<>(nuevosClientes, HttpStatus.OK);
	}

	/**
	 * Obtener la lista de clientes y exportarla a excel
	 * 
	*/	
	@GetMapping("exportar")
	public  ResponseEntity<?> exporClientes() {
		
		Collection<Cliente> clientes = clienteService.getAll();
		exporExcel(clientes);
		return new ResponseEntity<>(clientes, HttpStatus.OK);

	}
	
	/**
	 * metodo privado que realiza la exportación a Excel de la lista de clientes recibida
	 * @param clientes
	 */
	private void exporExcel(Collection<Cliente> clientes) {

		Integer contFila = 0;

		HSSFWorkbook libro = new HSSFWorkbook();

		HSSFSheet hoja = libro.createSheet();

		HSSFRow fila = hoja.createRow(contFila);

		HSSFCell celda = fila.createCell(5);		
		
		HSSFRichTextString texto = new HSSFRichTextString("LISTADO DE CLIENTES");
		celda.setCellValue(texto);		
		
		// Cabeceras
		contFila = 3;
		fila = hoja.createRow(contFila);
		celda = fila.createCell(5);		
		celda.setCellValue("APELLIDOS");
	
		celda = fila.createCell(6);
		celda.setCellValue("NOMBRE");
		
		celda = fila.createCell(7);
		celda.setCellValue("DIRECCIóN");
		
		celda = fila.createCell(8);
		celda.setCellValue("POBLACIóN");
		
		celda = fila.createCell(9);
		celda.setCellValue("PROVINCIA");

		contFila = 6;

		fila=hoja.createRow(contFila);

		for (Cliente cliente : clientes) {
			celda = fila.createCell(5);
			celda.setCellValue(cliente.getApellidos());
		
			celda = fila.createCell(6);
			celda.setCellValue(cliente.getNombre());

			celda = fila.createCell(7);
			celda.setCellValue(cliente.getDireccion());

			celda = fila.createCell(8);
			celda.setCellValue(cliente.getPoblacion());

			celda = fila.createCell(9);
			celda.setCellValue(cliente.getProvincia());

			// pasamos a la siguiente fila
			contFila++;
			fila = hoja.createRow(contFila);
		}

		try {
			FileOutputStream ficheroClientes = new FileOutputStream("clientes.xls");
			libro.write(ficheroClientes);
			ficheroClientes.close();
			libro.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
