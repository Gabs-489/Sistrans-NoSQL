package uniandes.edu.co.demo.repository;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServiciosSolicitadosRepository {
    
    private final MongoTemplate mongoTemplate;

    public ServiciosSolicitadosRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> findServiciosSolicitados(Date fechaInicio, Date fechaFin) {
        
        List<Document> pipeline = List.of(
            new Document("$unwind", "$prestaciones"),

            new Document("$match", new Document("$expr", 
                new Document("$and", List.of( 
                    new Document("$lte", List.of("$prestaciones.fecha", fechaFin)), 
                    new Document("$gte", List.of("$prestaciones.fecha", fechaInicio))  
                ))
            ).append("prestaciones.afiliado", new Document("$ne", null))),

            new Document("$group", new Document()
                .append("_id", "$nombre")
                .append("solicitudes", new Document("$sum", 1))
            ),

            new Document("$sort", new Document("solicitudes", -1)),

            new Document("$limit", 20),

            new Document("$project", new Document()
                .append("_id", 0)
                .append("Servicio", "$_id")
                .append("Solicitudes", "$solicitudes")
            )
        );


        return mongoTemplate.getCollection("servicios").aggregate(pipeline).into(new java.util.ArrayList<>());
    }
}
