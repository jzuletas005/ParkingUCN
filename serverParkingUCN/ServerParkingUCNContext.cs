using System.Reflection;
using ServerParkingUCN.ZeroIce.model;
using Microsoft.EntityFrameworkCore;

namespace ServerParkingUCN.Dao
{
    ///<sumary>
    /// The Connection to FivetDataBase
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
        public DbSet<Circulacion> Circulacion { get; set; } // <---- Linea para referenciar la base de datos Circulacion

        /// <summary>
        /// Configuration.
        /// </summary>
        /// <param name="optionsBuilder"> </param>
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)

        {
            // Using SQLite
            optionsBuilder.UseSqlite("Data Source=parkingUCN.db", options =>
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
                // Primary Key
                p.HasKey(p => p.uid);
                // The Name
                p.Property(p => p.nombre);
                // Required rut
                p.Property(p => p.rut);
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
                // The Workd Address
                p.Property(p => p.direccion);
                // The Country
                p.Property(p => p.country);
           });

             // Make the model to Vehiculo in Db. 
            modelBuilder.Entity<Vehiculo>(v =>
            {
                // Primary Key
                v.HasKey(v => v.uid);
                // The Patente
                v.Property(v => v.patente);
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
            });

            modelBuilder.Entity<Circulacion>(c =>
            {
             // Primary Key
            c.HasKey(c => c.uid);
             // The Patente
            c.Property(c => c.patente);
            // The fecha de ingreso
            c.Property(c => c.fechaIngreso);
            // The fecha de salida
            c.Property(c => c.fechaSalida);

});

        }

    }
}