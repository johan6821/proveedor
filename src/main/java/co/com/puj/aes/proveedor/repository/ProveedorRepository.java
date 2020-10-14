package co.com.puj.aes.proveedor.repository;
import co.com.puj.aes.proveedor.entity.Proveedor;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProveedorRepository {

@Autowired
    private DynamoDBMapper dynamoDbMapper;
//    Optional<Proveedor> findById(String id);

    public Proveedor save(Proveedor proveedor) {
        System.out.println("proveedor.getContacto() en el repositorio = " + proveedor.getContacto());

        dynamoDbMapper.save(proveedor);
        return proveedor;
    }

    public Proveedor getProveedorById (String idProveedor){
        return dynamoDbMapper.load(Proveedor.class, idProveedor);
    }

    public String delete (String idProveedor){
        Proveedor proveedor = dynamoDbMapper.load(Proveedor.class, idProveedor);
        dynamoDbMapper.delete(proveedor);
        return "Proveedor Eliminado!";

    }
    public  String update (String idProveedor, Proveedor proveedor){
        dynamoDbMapper.save(proveedor,
                new DynamoDBSaveExpression()
        .withExpectedEntry("idProveedor",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(idProveedor)
                )));
        return idProveedor;

    }


}
