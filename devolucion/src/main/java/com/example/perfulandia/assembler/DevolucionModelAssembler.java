package com.example.perfulandia.assembler;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.perfulandia.controller.DevolucionController;
import com.example.perfulandia.model.Devolucion;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DevolucionModelAssembler  implements RepresentationModelAssembler <Devolucion, EntityModel<Devolucion>>{

    @Override
    public EntityModel<Devolucion> toModel(Devolucion d){
        return EntityModel.of(
            d,
            linkTo(methodOn(DevolucionController.class).buscarDevolucion(d.getIdDevolucion())).withRel("Se busca DEVOLUCION por su ID en el Sistema "),
            linkTo(methodOn(DevolucionController.class).listarDevoluciones()).withRel("Se lista todos las DEVOLUCIONES en el Sistema"),
            linkTo(methodOn(DevolucionController.class).eliminarDevolucion(d.getIdDevolucion())).withRel("Se elimina DEVOLUCION por su ID en el Sistema"),
            linkTo(methodOn(DevolucionController.class).actualizarDevolucion(d.getIdDevolucion(),d)).withRel("Se actualiza DEVOLUCION por su ID en el Sistema")
        );
    }


}
