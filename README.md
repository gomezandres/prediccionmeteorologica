# Proyecto SOLARIUM
---
  Este sistema permite predecir el clima a partir de las coordenadas polares de los planetas Vulcanos, Ferengis, Betasoides con respecto al sol 

### Consideraciones 
---
* Se inicia con el día cero
* 1 año son 365 días
* Se predice hasta 10 años
* Angulo de rotación: 
    * Positivo, sentido anti horario
    * Negativo, sentido horario.
* Distancia es el radio r
* Se carga la base de datos solo si la misma se encuentra vacía.
* El ángulo de rotación inicial para cada planeta se considera en grados.

### Algoritmos
---
Para el calculo de coordenadas polares:
* θ = día × Ángulo de rotación **(°)**  
* x = r × cos(θ) **Ángulo expresado en radianes**
* y = r × sin(θ) **Ángulo expresado en radianes**

Para el cálculo del clima:
* **1** - Sequía: Cálculo de puntos colineales para conocer si los tres planetas junto con el sol están alineados.  [Cálculo de puntos colineales](https://definicion.de/puntos-colineales/)
* **2** - Condiciones óptimas de presión y temperatura: Cálculo de puntos colineales para conocer si solamente los tres planetas están alineados.
* **3** - Lluvia: Cuando los puntos no son colineales y se determina que el Sol se encuentra dentro del triángulo. [Determinación de un punto interior a un triángulo](https://tecdigital.tec.ac.cr/revistamatematica/Contribucionesv3n2002/WMoraMatProg/pag2.html)
*   **3 - A** - Pico máximo de lluvia: Cuando es un período de lluvia y el perímetro del triángulo es el máximo. [Calculo de perímetro de un triángulo](https://www.universoformulas.com/matematicas/geometria/perimetro-triangulo/)
* **4** - Clima Normal: Cuando no se corresponde a ningún punto anterior.

El algoritmo implementado calcula el clima (SEQUIA/OPTIMO/LLUVIA/NORMAL) en el orden descripto anteriormente.

### Implementación 
---
Definición de los servicios 
    
    https://solarium-222019.appspot.com/swagger-ui.html

**Respuestas a las indicaciones**:

**1 - ¿Cuántos períodos de sequía habrá?**

_Request_
    
    curl -X GET --header 'Accept: application/json' 'https://solarium-222019.appspot.com/periodo?periodo=sequia'

_Response_

    {"cantidad": 21}
   
**2 - ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?**
  
**Periodos de lluvia:**

_Request_
    
    curl -X GET --header 'Accept: application/json' 'https://solarium-222019.appspot.com/periodo?periodo=lluvia'
_Response_

    {"cantidad": 814}
**Día con pico máximo de lluvia:**

_Request_

    curl -X GET --header 'Accept: application/json' 'https://solarium-222019.appspot.com/lluvia/maximaintensidad'
_Response_

    {"dia": 1908,"clima": "LLUVIA"}
**3 -¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?**

_Request_

    curl -X GET --header 'Accept: application/json' 'https://solarium-222019.appspot.com/periodo?periodo=optimo'

_Response_

    {"cantidad": 20}
**4 - Consulta del clima de una día particular**

_Request_
    
    curl -X GET --header 'Accept: application/json' 'https://solarium-222019.appspot.com/clima?dia=566'

_Response_

    {"dia": 566,"clima": "LLUVIA"}  

### Construido con 

* [Java 8](https://www.java.com/es/)
* [Eclipse](https://www.eclipse.org/) 
* [Maven](https://maven.apache.org/) 
* [Spring boot](http://spring.io/projects/spring-boot)
* [JUnit](https://junit.org/junit5/)
* [Swagger](https://swagger.io/)
* [PostgreSQL 9.6](https://www.postgresql.org/)
* [Google Cloud Platform - Eclipse Plugin](https://cloud.google.com/eclipse/docs/)
* [App Engine SDK for Java](https://cloud.google.com/appengine/docs/standard/java/download)


## Autor

* **Andres Emilio Gomez** - *andres.e.gomez@gmail.com* - 





---
