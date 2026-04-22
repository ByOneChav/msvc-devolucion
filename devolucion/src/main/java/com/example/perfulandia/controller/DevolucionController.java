package com.example.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.perfulandia.assembler.DevolucionModelAssembler;
import com.example.perfulandia.model.Devolucion;
import com.example.perfulandia.service.DevolucionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/perfulandia/devoluciones")
@Tag(
    name = "DEVOLUCIÓN",
    description = "Endpoints para gestionar Devoluciones: registrar, listar, actualizar, eliminar y obtener detalles de las devoluciones realizados por los usuarios.")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @Autowired
    private DevolucionModelAssembler assembler;


    // ENPOINT QUE LISTA TODOS LAS DEVOLUCIONES
    @GetMapping
    @Operation(summary = "Listar todo las Devoluciones", description = "Devuelve una lista completa de todas las devoluciones registrados en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente todos las Devoluciones",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Devolucion.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna Devolucion",
            content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran datos"))),
        @ApiResponse(responseCode = "500", description = "Error al listar Devolucion")
    })
    public ResponseEntity<?> listarDevoluciones(){
        List<Devolucion> devoluciones = devolucionService.buscarTodo();
        if(devoluciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(devoluciones);
    }


    // ENPOINT QUE BUSCA DEVOLUCION POR SU ID
    @GetMapping("/{idDev}")
    @Operation(summary = "Obtener Devolucion por ID", description = "Recupera la información de una Devolucion específica mediante su identificador único (id)." )
    @Parameters(value = {
        @Parameter(name = "idDev", description = "ID de la Devolucion que se requiere buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolucion actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Devolucion.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar Pago",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Devolucion no encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error del servidor al actualizar Devolucion",
            content = @Content)
    })
    public ResponseEntity<?> buscarDevolucion(@PathVariable Long idDev){
        try{
            Devolucion devolucion = devolucionService.buscar(idDev);
            return ResponseEntity.ok(assembler.toModel(devolucion));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    // ENPOINT QUE GUARDA DEVOLUCION
    @PostMapping
    @Operation(summary = "Guardar nueva Devolucion", description = "Registra una nueva Devolucion en el sistema a partir de los datos enviados en el cuerpo de la solicitud.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description= "Objeto Devolucion que va a registrar", required = true, content = @Content(schema = @Schema(implementation = Devolucion.class))))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Devolucion creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Devolucion.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para crear Devolucion"),
        @ApiResponse(responseCode = "500", description = "Error al crear Devolucion")
    })
    public ResponseEntity<?> guardarDevolucion(@RequestBody Devolucion devolucion){
        try {
            Devolucion devolucionRegistrar = devolucionService.guardar(devolucion);
            return ResponseEntity.ok(assembler.toModel(devolucionRegistrar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("NO SE PUDO GUARDAR LA DEVOLUCION XD");
        }
    }


    // ENPOINT QUE ACTUALIZA UN DEVOLUCION POR ID
    @PutMapping("/{idDev}")
    @Operation(summary = "Actualizar Devolucion por ID", description = "Modifica los datos de una Devolucion existente, identificada por su ID. Requiere el cuerpo de la Devolucion actualizado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolucion actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Devolucion.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar la Devolucion",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Devolucion no encontrada",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error del servidor al actualizar Devolucion",
            content = @Content)
    })
    public ResponseEntity<?> actualizarDevolucion(@PathVariable Long idDev, @RequestBody Devolucion devolucionActualizar){
        try{
            Devolucion devolucionBuscado = devolucionService.buscar(idDev);
            devolucionBuscado.setIdDevolucion(idDev);
            devolucionBuscado.setUsuario(devolucionActualizar.getUsuario());
            devolucionBuscado.setMotivo(devolucionActualizar.getMotivo());
            devolucionBuscado.setFechaDevolucion(devolucionActualizar.getFechaDevolucion());
            devolucionBuscado.setProducto(devolucionActualizar.getProducto());
            devolucionBuscado.setEstado(devolucionActualizar.getEstado());
            devolucionBuscado.setMontoReembolso(devolucionActualizar.getMontoReembolso());
            devolucionService.guardar(devolucionBuscado);

            return ResponseEntity.ok(assembler.toModel(devolucionBuscado));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL ID DE LA DEVOLUCION NO ESTÁ REGISTRADO EN LA BASE DE DATO PARA PODER ACTUALIZAR");
        }
        
    }


    // ENPOINT QUE ELIMINA UNA DEVOLUCION POR ID
    @Operation(summary = "Eliminar Devolucion por ID", description = "Elimina una Devolucion registrado en el Sistema, usando su ID como referencia. Esta acción es irreversible.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Devolucion eliminado correctamente"),
    @ApiResponse(responseCode = "404", description = "Devolucion no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error al eliminar la Devolucion")
    })
    @DeleteMapping("/{idDev}")
    public ResponseEntity<?> eliminarDevolucion(@PathVariable long idDev){
        try{
            devolucionService.Eliminar(idDev);
            return ResponseEntity.status(HttpStatus.OK).body("¡SE ELIMINÓ LA DEVOLUCION CON ÉXITO!");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LA DEVOLUCION NO ESTA REGISTRADO PARA PODER SER ELIMINADO");
        }
    }

}
