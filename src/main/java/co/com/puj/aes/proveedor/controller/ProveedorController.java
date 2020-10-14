package co.com.puj.aes.proveedor.controller;

import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.repository.ProveedorRepository;
import org.springframework.web.bind.annotation.*;


@RestController

public class ProveedorController {

    private ProveedorRepository proveedorRepository;

    @PostMapping("/proveedor")
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor) {
        System.out.println("proveedor = " + proveedor.getContacto());
        return proveedorRepository.save(proveedor);
    }
    @GetMapping("/proveedor{id}")
    public Proveedor getProveedor(@PathVariable("id") String idProveedor){
        return proveedorRepository.getProveedorById(idProveedor);
    }

    @DeleteMapping("/proveedor{id}")
    public String deleteProveedor(@PathVariable("id")String idProvedor){
        return proveedorRepository.delete(idProvedor);
    }

    @PutMapping("/proveedor{id}")
    public String updateProveedor(@PathVariable("id")String idProveedor,@RequestBody Proveedor proveedor){
        return proveedorRepository.update(idProveedor, proveedor);
    }

    /*@GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Proveedor> list = proveedorService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

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

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody Proveedor proveedor) throws Exception {
        Proveedor proveedor1 = proveedorService.findById(id);
        if(proveedor1==null){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        proveedor.setIdEps(id);
        return new ResponseEntity<>(proveedorService.update(proveedor),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Proveedor proveedor1 = proveedorService.findById(id);
        if(proveedor1==null || !proveedorService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(proveedorService.deleteEPS(id), HttpStatus.OK);
    }
    @Deprecated
    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Proveedor proveedor = proveedorService.findId(id);
        if(proveedor==null || !proveedorService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(proveedorService.activar(id), HttpStatus.OK);
    }*/

}
