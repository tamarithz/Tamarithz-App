
package org.empleodigital.project.tamarithz.entities;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresas/{empresaId}")
class EmpleadoController {

    private static final String VIEWS_EMPLEADO_CREATE_OR_UPDATE_FORM = "empleado/createOrUpdatePetForm";
    private final EmpleadoRepository empleados;
    private final EmpresaRepository empresas;

    public EmpleadoController(EmpleadoRepository empleados, EmpresaRepository empresas) {
        this.empleados = empleados;
        this.empresas = empresas;
    }

    @ModelAttribute("empresa")
    public Empresa findEmpresa(@PathVariable("empresaId") int empresaId) {
        return this.empresas.findById(empresaId);
    }

    @InitBinder("empresa")
    public void initEmpresaBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("empleado")
    public void initEmpleadoBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new EmpleadoValidator());
    }

    @GetMapping("/empleado/new")
    public String initCreationForm(Empresa empresa, ModelMap model) {
        Empleado empleado = new Empleado();
        empresa.addEmpleado(empleado);
        model.put("empleado", empleado);
        return VIEWS_EMPLEADO_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/empleados/new")
    public String processCreationForm(Empresa empresa, @Valid Empleado empleado, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(empleado.getName()) && empleado.isNew() && empresa.getEmpleado(empleado.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        empresa.addEmpleado(empleado);
        if (result.hasErrors()) {
            model.put("empleado", empleado);
            return VIEWS_EMPLEADO_CREATE_OR_UPDATE_FORM;
        } else {
            this.empleados.save(empleado);
            return "redirect:/empresas/{empresaId}";
        }
    }

    @GetMapping("/empleados/{empleadoId}/edit")
    public String initUpdateForm(@PathVariable("empleadoId") int empleadoId, ModelMap model) {
        Empleado empleado = this.empleados.findById(empleadoId);
        model.put("empleado", empleado);
        return VIEWS_EMPLEADO_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/empleados/{empleadoId}/edit")
    public String processUpdateForm(@Valid Empleado empleado, BindingResult result, Empresa empresa, ModelMap model) {
        if (result.hasErrors()) {
            empleado.setEmpresa(empresa);
            model.put("empleado", empleado);
            return VIEWS_EMPLEADO_CREATE_OR_UPDATE_FORM;
        } else {
            empresa.addEmpleado(empleado);
            this.empleados.save(empleado);
            return "redirect:/empresas/{empresaId}";
        }
    }

}
