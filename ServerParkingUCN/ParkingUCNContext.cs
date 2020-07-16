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
        public DbSet<PersonasUCN> Personas { get; set; }

        /// <summary>
        /// The Connection to the database to Vehiculo.
        /// </summary>
        /// <value> </value>

        //public DbSet<VehiculosUCN> Vehiculos { get; set; } // <---- Linea para referenciar la base de datos Vehiculo 

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
            //modelBuilder.Entity<Persona>(p =>
            //{
                // Primary Key
                //p.HasKey(p => p.uid);
                // The Name
                //p.Property(p => p.nombre);
                // Required rut
                //p.Property(p => p.rut);
                // The Sexo
                //p.Property(p => p.sexo);
                // The Cargo
                //p.Property(p => wPosition.w);
                // The Unidad
                //p.Property(p => p.unit);
                // The Email 
                //p.Property(p => p.email);
                // The Telefono
                //p.Property(p => p.telefono);
                // The Workd Address
                //p.Property(p => p.direccion);
                // The Country
                //p.Property(p => p.country);
           // });

             // Make the model to Vehiculo in Db. 
            //modelBuilder.Entity<Vehiculo>(v =>
            //{
                // Primary Key
               // v.HasKey(v => v.uid);
                // The Patente
                //v.Property(v => v.Patente);
                // The Marca
                //v.Property(v => v.marca);
                // The Modelo
                //v.Property(v => v.modelo);
                // The anio
                //v.Property(v => v.anio);
                // The Observation
                //v.Property(v => v.observacion);
                // The legal owner
                //v.Property(v => v.responsable);
           // });

        //}

    }


}

    
    
    }
