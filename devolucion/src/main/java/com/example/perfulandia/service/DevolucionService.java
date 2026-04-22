package com.example.perfulandia.service;

import com.example.perfulandia.model.Devolucion;
import com.example.perfulandia.repository.DevolucionRepository;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class DevolucionService {

    // @Autowired
    private DevolucionRepository devolucionRepository;

    public DevolucionService (DevolucionRepository devolucionRepository){
        this.devolucionRepository = devolucionRepository;
    }

    public List<Devolucion> buscarTodo(){
        return devolucionRepository.findAll();
    }

    public Devolucion buscar(long id){
        return devolucionRepository.findById(id).get();
    }

    public Devolucion guardar(Devolucion devolucion){
        return devolucionRepository.save(devolucion);
    }

    public void Eliminar(long id){
        devolucionRepository.deleteById(id);
    }

}