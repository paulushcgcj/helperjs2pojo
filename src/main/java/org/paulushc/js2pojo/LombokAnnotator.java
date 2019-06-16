package org.paulushc.js2pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsonschema2pojo.AbstractAnnotator;

/**
 * <p><b>LombokAnnotator</b></p>
 * Annotator implementation that adds support to Lombok from JSON schemas.
 * It will replace all getters, setters and builders with Lombok annotations.
 */
public class LombokAnnotator extends AbstractAnnotator {

  /**
   * This will set up all properties as private.
   * @param field Field to be set.
   * @param clazz Class type.
   * @param propertyName Name of property.
   * @param propertyNode Node of property.
   */
  @Override
  public void propertyField(
      JFieldVar field,
      JDefinedClass clazz,
      String propertyName,
      JsonNode propertyNode
  ) {
    field.mods().setPrivate();
    super.propertyField(field, clazz, propertyName, propertyNode);
  }

  /**
   * Add to the class some of the lombok annotations.
   * @param clazz Class type.
   * @param propertyNode Node of property.
   */
  @Override
  public void propertyInclusion(JDefinedClass clazz, JsonNode propertyNode) {

    clazz.annotate(Data.class);
    clazz.annotate(Builder.class);
    clazz.annotate(NoArgsConstructor.class);
    clazz.annotate(AllArgsConstructor.class);

  }

}
