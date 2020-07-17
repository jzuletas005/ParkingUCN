using System.Reflection;
using ServerZeroIce.model;
using Microsoft.EntityFrameworkCore;

namespace Dao

{
    public class ParkingUCNContext : DbContext
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

        public DbSet<Vehiculo> Vehiculos { get; set; } // <---- Linea para referenciar la base de datos Vehiculo 

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
            modelBuilder.Entity<Persona>(pe =>
            {
                // Primary Key
                pe.HasKey(p => p.uid);
                // The Name
                pe.Property(p => p.nombre);
                // Required rut
                pe.Property(p => p.rut);
                // The Sexo
                pe.Property(p => p.sexo);
                // The Cargo
                pe.Property(p => p.wposition); 
                // The Unidad
                pe.Property(p => p.unit);
                // The Email 
                pe.Property(p => p.email);
                // The Telefono
                pe.Property(p => p.telefono);
                // The Workd Address
                pe.Property(p => p.direccion);
                // The Country
                pe.Property(p => p.country);
           });

             // Make the model to Vehiculo in Db. 
            modelBuilder.Entity<Vehiculo>(vh =>
            {
                // Primary Key
                vh.HasKey(v => v.uid);
                // The Patente
                vh.Property(v => v.patente);
                // The Marca
                vh.Property(v => v.marca);
                // The Modelo
                vh.Property(v => v.modelo);
                // The anio
                vh.Property(v => v.anio);
                // The Observation
                vh.Property(v => v.observacion);
                // The Responsable
                vh.Property(v => v.responsable);
            });

        }

    }


}