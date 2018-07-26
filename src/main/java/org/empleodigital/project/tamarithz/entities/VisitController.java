package org.empleodigital.project.tamarithz.entities;

import java.util.Map;

import javax.validation.Valid;

import org.empleodigital.project.tamarithz.visit.Visit;
import org.empleodigital.project.tamarithz.visit.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class VisitController {

    private final VisitRepository visits;
    private final EmpleadoRepository empleados;


    public VisitController(VisitRepository visits, EmpleadoRepository empleados) {
        this.visits = visits;
        this.empleados = empleados;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Llamado antes de cada @RequestMapping.
     * 
     * - Asegura que siempre haya datos actualizados
     * - Como no se usa el ambito de session Empleado siempre debe tener un id
     * (aunque este no sea parte de los formularios)
     *
     * @param empleadoId
     * @return Empleado
     */
    @ModelAttribute("visit")
    public Visit loadEmpleadoWithVisit(@PathVariable("empleadoId") int empleadoId, Map<String, Object> model) {
        Empleado empleado = this.empleados.findById(empleadoId);
        model.put("empleado", empleado);
        Visit visit = new Visit();
        empleado.addVisit(visit);
        return visit;
    }

    // Spring MVC llama a loadEmpleadoWithVisit(...) antes de que initNewVisitForm sea invocado
    @GetMapping("/empresa/*/empleado/{empleadoId}/visits/new")
    public String initNewVisitForm(@PathVariable("empleadoId") int empleadoId, Map<String, Object> model) {
        return "empleados/createOrUpdateVisitForm";
    }

    // Spring MVC llama a loadEmpleadoWithVisit(...) antes de que processNewVisitForm sea invocado
    @PostMapping("/empresa/{empresaId}/empleados/{empleadoId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "empleados/createOrUpdateVisitForm";
        } else {
            this.visits.save(visit);
            return "redirect:/empresa/{empresaId}";
        }
    }

}
