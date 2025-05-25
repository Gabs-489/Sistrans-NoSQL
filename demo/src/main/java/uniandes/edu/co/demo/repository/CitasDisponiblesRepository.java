package uniandes.edu.co.demo.repository;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CitasDisponiblesRepository {
    
    private final MongoTemplate mongoTemplate;

    public CitasDisponiblesRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Document> findCitasDisponibles(String id) {
        
        
        ObjectId objectId = new ObjectId(id);

        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", objectId)),

            new Document("$unwind", "$prestaciones"),

            new Document("$match", new Document("$expr", new Document("$and", List.of(
                new Document("$gte", List.of("$prestaciones.fecha", "$$NOW")),
                new Document("$lte", List.of(
                    "$prestaciones.fecha",
                    new Document("$dateAdd", new Document()
                        .append("startDate", "$$NOW")
                        .append("unit", "week")
                        .append("amount", 4)
                    )
                ))
            )))),

            new Document("$lookup", new Document()
                .append("from", "medicos")
                .append("let", new Document("medicoIdStr", "$prestaciones.medico"))
                .append("pipeline", List.of(
                    new Document("$match", new Document("$expr",
                        new Document("$eq", List.of(
                            "$_id",
                            new Document("$toObjectId", "$$medicoIdStr")
                        ))
                    ))
                ))
                .append("as", "nomMedico")
            ),

            new Document("$unwind", "$nomMedico"),

            new Document("$lookup", new Document()
                .append("from", "ips")
                .append("let", new Document("ipsIdStr", "$prestaciones.ips"))
                .append("pipeline", List.of(
                    new Document("$match", new Document("$expr",
                        new Document("$eq", List.of(
                            "$_id",
                            new Document("$toObjectId", "$$ipsIdStr")
                        ))
                    ))
                ))
                .append("as", "nomips")
            ),

            new Document("$unwind", "$nomips"),

            new Document("$project", new Document()
                .append("_id", 0)
                .append("Servicio", "$nombre")
                .append("Fecha", "$prestaciones.fecha")
                .append("Medico", "$nomMedico.nombre")
                .append("IPS", "$nomips.nombre")
            )
        );

        return mongoTemplate.getCollection("servicios").aggregate(pipeline).into(new java.util.ArrayList<>());
    }
    
}
