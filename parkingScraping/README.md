
# ParkingUCN
- **Course**: Proyecto Desarrollo e Integración de Soluciones.
- **Degree**: Ingeniería en Computación e Informática.

## Description
- Este modulo captura datos del Directorio de la UCN y encuentra los RUT de ellos por medio de NombreRutyFirma 


## Java Libraries
- SLF4J   1.7.30
- Logback 1.3.0
- JSoup  1.13.1
- JUnit 5.7.0
- H2 1.4.200
- ORMLite 5.1
- SQLite 3.32.3.1
- ZeroIce 3.7.4
- CSV 5.2 


## UML

![Screenshot](../img/scrapper.png)

<!--
  @startuml
  class Person{
  - id: int
  - name: String
  - rut: String
  - sex: String
  - wposition: String
  - unit: String
  - email: String
  - phone: String
  - office: String
  - address: String
  - country: String
  + Person(): ORM Lite
  + Person()
  + getId()
  + getName()
  + getRut()
  + getSex()
  + getWposition()
  + getUnit()
  + getEmail()
  + getPhone()
  + getOffice()
  + getAddress()
  + getCountry()
  }
  class ScrapperData{
  - {static}log: Logger
  + Main(): void
  - {static}Rutificador(): String[]
  - {static}ReversWord(): String
  }
  class ScrapperSQLite{
  - {static}log: Logger
  + Main(): void
  }
  note "datos.csv" as N1
  ScrapperData ..N1:<create>
  ScrapperSQLite o--N1:<use>
  ScrapperSQLite o--Person:<use>
  @enduml
-->
