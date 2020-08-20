using System.Reflection;
using ServerParkingUCN.ZeroIce.model;
using Microsoft.EntityFrameworkCore;

namespace ServerParkingUCN.Dao
{
    ///<sumary>
    /// The Connection to Parking UCN Data base
    ///<sumary>
    public class ServerParkingUCNContext : DbContext
    {
          /// <summary>
        /// The Connection to the database to Persona.
        /// </summary>
        /// <value> </value>
        public DbSet<Persona> Personas { get; set; }

        /// <summary>
        /// The Connection to the database to Vehiculo.
        /// </summary>
        /// <value> </value>

        public DbSet<Vehiculo> Vehiculos { get; set; }

        /// <summary>
        /// The Connection to the database to Vehiculo.
        /// </summary>
        /// <value> </value>
        public DbSet<Circulacion> Circulaciones { get; set; } // <---- Linea para referenciar la base de datos circulacion

        /// <summary>
        /// The Connection to the database to Identificacion.
        /// </summary>
        /// <value> </value>

        /// <summary>
        /// Configuration.
        /// </summary>
        /// <param name="optionsBuilder"> </param>
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)

        {
            // Using SQLite
            optionsBuilder.UseSqlite("Data Source=parking.db", options =>
            {
                options.MigrationsAssembly(Assembly.GetExecutingAssembly().FullName);
            });
            base.OnConfiguring(optionsBuilder);
        }

        /// <summary>
        /// Create the ER from Entity.
        /// </summary>
        /// <param name="modelBuilder">to use</param>
       
        
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Make the model to Persona in Db. 
            modelBuilder.Entity<Persona>(p =>
            {
                p.Property(p => p.uid);
                // The Name
                p.Property(p => p.nombre);
                // Required rut -> Primary Key
                p.HasKey(p => p.rut);
                // The Sexo
                p.Property(p => p.sexo);
                // The Cargo
                p.Property(p => p.wposition);
                // The Unidad
                p.Property(p => p.unit);
                // The Email 
                p.Property(p => p.email);
                // The Telefono
                p.Property(p => p.telefono);
                // The Oficina
                p.Property(p => p.oficina);
                // The Workd Address
                p.Property(p => p.direccion);
                // The Country
                p.Property(p => p.country);
           });

             // Make the model to Vehiculo in Db. 
            modelBuilder.Entity<Vehiculo>(v =>
            {
                // The Patente
                v.HasKey(v => v.patente);
                //  The LogoUCN
                v.Property(v => v.codigoLogo);
                // The Marca
                v.Property(v => v.marca);
                // The Modelo
                v.Property(v => v.modelo);
                // The anio
                v.Property(v => v.anio);
                // The Observation
                v.Property(v => v.observacion);
                // The Responsable
                v.Property(v => v.responsable);
                // The Tipo logo 
                v.Property(v => v.tipoLogo);
            });

            modelBuilder.Entity<Circulacion>(c =>
            {
                 // Primary Key
                c.HasKey(c => c.uid);
                 // The Patente
                c.Property(c => c.patente);
                // The fecha de ingreso
                c.Property(c => c.fechaIngreso);
                 // The hora de ingreso
                c.Property(c => c.horaIngreso);
                // The fecha de salida
                c.Property(c => c.fechaSalida);
                // The hora de salida
                c.Property(c => c.horaSalida);
                // The puerta ingreso
                c.Property(c => c.puertaEntrada);
                // The puerta salida
                c.Property(c => c.puertaSalida);
                // Observacion del vehiculo
                c.Property(c => c.observacion);
                // Estado del vehiculo
                c.Property(c => c.estadoVehiculo);
            });

        }

    }
}