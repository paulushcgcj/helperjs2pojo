# HelperJS 2 Pojo

[![pipeline status](https://gitlab.com/paulushc/helperjs2pojo/badges/master/pipeline.svg)](https://gitlab.com/paulushc/helperjs2pojo/commits/master)
[![coverage report](https://gitlab.com/paulushc/helperjs2pojo/badges/master/coverage.svg)](https://gitlab.com/paulushc/helperjs2pojo/commits/master)
![Sonar Coverage](https://img.shields.io/sonar/https/sonarcloud.io/paulushcgcj_helperjs2pojo/coverage.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=paulushcgcj_helperjs2pojo&metric=alert_status)](https://sonarcloud.io/dashboard?id=paulushcgcj_helperjs2pojo)

![Maven Central](https://img.shields.io/maven-central/v/io.github.paulushcgcj/helper-js2pojo.svg)
![Bintray](https://img.shields.io/bintray/v/paulushc/io.github.paulushcgcj/helper-js2pojo.svg)
![GitHub release](https://img.shields.io/github/release/paulushcgcj/helperjs2pojo.svg)


This package is intended to be used as custom Annotator to [JsonSchema2Pojo](https://github.com/joelittlejohn/jsonschema2pojo).

With this custom annotator, you can add lombok and JPA support to generated codes.

For released versions go directly to [maven central](https://mvnrepository.com/artifact/io.github.paulushcgcj/helper-js2pojo)

## How to use it

In your `pom.xml` file, add the following inside your build plugins entry, replacing where needed:

```xml
<plugin>
    <groupId>org.jsonschema2pojo</groupId>
    <artifactId>jsonschema2pojo-maven-plugin</artifactId>
    <version>0.5.1</version>
    <dependencies>
        <dependency>
            <groupId>io.github.paulushcgcj</groupId>
            <artifactId>helper-js2pojo</artifactId>
            <version>X.Y.Z</version>
        </dependency>
    </dependencies>
    <configuration>
        <removeOldOutput>true</removeOldOutput>
        <useCommonsLang3>true</useCommonsLang3>
        <includeConstructors>false</includeConstructors>
        <includeAccessors>false</includeAccessors>
        <includeAdditionalProperties>false</includeAdditionalProperties>
        <includeJsr303Annotations>true</includeJsr303Annotations>
        <sourceDirectory>${basedir}/src/main/resources/your_folder_here</sourceDirectory>
        <targetPackage>your.package.folder.structure.here</targetPackage>
        <outputDirectory>${basedir}/target/generated-sources/your_folder_here</outputDirectory>
        <propertyWordDelimiters>-</propertyWordDelimiters>
        <customAnnotator>full.path.to.annotator</customAnnotator>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## Annotators

You can choose the annotator that best suits your needs. Below you will find all annotators found on this package:

### Lombok

The `io.github.paulushcgcj.js2pojo.LombokAnnotator` annotator will add Lombok dependencies to your generated file, removing that bunch of boilerplate code, replacing it with Lombok annotations.
With this you will end up with a much more cleaner code.

### JPA

If you need to add some of the JPA annotations, like `@Entity` and `@Id`, all you have to do is add the custom annotator `io.github.paulushcgcj.js2pojo.HibernateAnnotator`. Also you will need to add to your schema an entry marking it with entity as true.

Ex:

```json
{
  "$id": "https://example.com/person.schema.json",
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "Person",
  "type": "object",
  "properties": {
    "entity": true,
    "firstName": {
      "type": "string",
      "description": "The person's first name."
    },
    "lastName": {
      "type": "string",
      "description": "The person's last name."
    },
    "age": {
      "description": "Age in years which must be equal to or greater than zero.",
      "type": "integer",
      "minimum": 0
    }
  }
}
```

### Lobok + JPA

If you want just a single annotator that gives you both worlds, you can use the `io.github.paulushcgcj.js2pojo.HibernateLombokAnnotator` and have both things in the same annotator.