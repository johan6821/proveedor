package co.com.puj.aes.proveedor.controller;

import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/proveedor")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ProveedorController {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private KafkaTemplate<String, Proveedor> kafkaTemplate;
    private static final String TOPIC = "reserva";

    /*@GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Proveedor> list = proveedorRepository.getProveedorList();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }*/

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Proveedor proveedor) throws Exception {
        return new ResponseEntity<>(proveedorRepository.save(proveedor), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id ) throws Exception {
        Proveedor proveedor = proveedorRepository.getProveedorById(id);
        if(proveedor==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        kafkaTemplate.send(TOPIC, proveedor);
        return new ResponseEntity<>(proveedorRepository.getProveedorById(id),HttpStatus.OK);
    }
@KafkaListener(topics = "reserva", groupId = "JohanCespedes")
    public ResponseEntity <?> updateCalificaicion(@RequestBody Proveedor calificacion) throws Exception {
    Proveedor proveedor1 = proveedorRepository.getProveedorById(calificacion.getIdProveedor());
    if(proveedor1==null){
        return new ResponseEntity<>("No existe un Proveedor correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
    }
    System.out.println("Esta es la calificación  = " + calificacion.getCalificacion());
    //proveedor1.setCalificacion(calificacion.getCalificacion());
    return null;
}
    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idProveedor, @RequestBody Proveedor proveedor) throws Exception {
        Proveedor proveedor1 = proveedorRepository.getProveedorById(idProveedor);
        if(proveedor1==null){
            return new ResponseEntity<>("No existe un Proveedor correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        proveedor.setIdProveedor(idProveedor);
        return new ResponseEntity<>(proveedorRepository.update(idProveedor, proveedor),HttpStatus.OK);
    }

  /*  @DeleteMapping("/proveedor/{id}")
    public String deleteProveedor(@PathVariable("id")String idProvedor){
        return proveedorRepository.delete(idProvedor);
    }*/

    @ResponseBody
    @DeleteMapping("{idProveedor}")
    public ResponseEntity <?> delete(@PathVariable("idProveedor") String idProveedor) throws Exception {
        Proveedor proveedor = proveedorRepository.getProveedorById(idProveedor);
        if(proveedor==null /*|| !proveedorService.existeById(id)*/){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proveedorRepository.delete(idProveedor), HttpStatus.OK);
    }


    /*@PostMapping("/proveedor")
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor) {
        System.out.println("proveedor = " + proveedor.getContacto());
        return proveedorRepository.save(proveedor);
    }
    @GetMapping("/proveedor/{id}")
    public Proveedor getProveedor(@PathVariable("id") String idProveedor){
        return proveedorRepository.getProveedorById(idProveedor);
    }

     @PutMapping("/proveedor/{id}")
    public String updateProveedor(@PathVariable("id")String idProveedor,@RequestBody Proveedor proveedor){
        return proveedorRepository.update(idProveedor, proveedor);
    }



    */

    /*

    @GetMapping("/findAllByProveedor")
    public ResponseEntity <?> findAllByEps() throws Exception {
        List <Map<String, String>> list = proveedorService.findAllByEps();
        if(!(list == null)){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Proveedor proveedor = proveedorService.findById(id);
        if(proveedor==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proveedorService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Proveedor proveedor) throws Exception {
        return new ResponseEntity<>(proveedorService.create(proveedor), HttpStatus.OK);
    }




    }*/

}
