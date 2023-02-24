/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionariatoyota.Logica;

import concesionariatoyota.Persistencia.ControladoraPersistencia;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void agregarAutomovil(String modelo, String marca, String motor, String color, String patente, int cantPuertas) {
      AutoToyota auto = new AutoToyota();
      auto.setModelo(modelo);
      auto.setMarca(marca);
      auto.setMotor(motor);
      auto.setColor(color);
      auto.setPatente(patente);
      auto.setCantPuertas(cantPuertas);
        
controlPersis.agregarAutomovil(auto);
JOptionPane optionPane = new JOptionPane("Se guardó correctamente");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Guardado Exitoso");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public List<AutoToyota> traerAutos() {
      return controlPersis.traerAutos();
    }

    public void borrarAuto(int idAuto) {
        controlPersis.borrarAuto(idAuto);
    }

    public AutoToyota traerAuto(int idAuto) {
        return controlPersis.traerAuto(idAuto);
    }

    public void modificarAuto(AutoToyota auto, String modelo, String marca, String motor, String color, String patente, int cantPuertas) {
        auto.setColor(color);
        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setMotor(motor);
        auto.setPatente(patente);
        auto.setCantPuertas(cantPuertas);
        
        
        controlPersis.modificarAuto(auto);
    }

    

    

   
}