/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionariatoyota.Persistencia;

import concesionariatoyota.Logica.AutoToyota;
import concesionariatoyota.Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ControladoraPersistencia {
AutoToyotaJpaController autoJpa = new AutoToyotaJpaController();
    public void agregarAutomovil(AutoToyota auto) {
     autoJpa.create(auto);
    }

    public List<AutoToyota> traerAutos() {
      return  autoJpa.findAutoToyotaEntities();
    }

    public void borrarAuto(int idAuto) {
    try {
        autoJpa.destroy(idAuto);
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    public AutoToyota traerAuto(int idAuto) {
       return autoJpa.findAutoToyota(idAuto);
                
    }

    public void modificarAuto(AutoToyota auto) {
    try {
        autoJpa.edit(auto);
    } catch (Exception ex) {
        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    
   
    } 
    
    
    
