package org.paulushc.js2pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsonschema2pojo.AbstractAnnotator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p><b>HibernateLombokAnnotator</b></p>
 * This annotator will add support both for Lombok and JPA/Hibernate Entity.
 */
public class HibernateLombokAnnotator extends AbstractAnnotator {

    /**
     * Set all properties as private, and if found, add the ID and GeneratedValue annotations to this ID
     * @param field
     * @param clazz
     * @param propertyName
     * @param propertyNode
     */
    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {

        if(propertyName.equalsIgnoreCase("id")){
            field.annotate(Id.class);
            field.annotate(GeneratedValue.class).param("strategy", GenerationType.IDENTITY);

        }
        field.mods().setPrivate();
        super.propertyField(field, clazz, propertyName, propertyNode);
    }

    /**
     * Add some of the Lombok annotations to the class along with Entity annotation, and if not present, add an ID field
     * @param clazz
     * @param propertyNode
     */
    @Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode propertyNode) {

        clazz.annotate(Data.class);
        clazz.annotate(Builder.class);
        clazz.annotate(NoArgsConstructor.class);
        clazz.annotate(AllArgsConstructor.class);

        if(propertyNode.get("properties").has("entity")) {

            clazz.annotate(Entity.class);
            ((ObjectNode)propertyNode.get("properties")).remove("entity");
            if(!propertyNode.get("properties").has("id")){
                ((ObjectNode)propertyNode.get("properties"))
                        .putObject("id")
                        .put("type","integer")
                        .put("description","The ID of " + clazz.name())
                        .put("minLength","1")
                        .put("minimum","1")
                        .put("required","1")
                ;
            }
        }

    }
}
