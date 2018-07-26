
package org.empleodigital.project.tamarithz.entities;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class EmpresaController {

    private static final String VIEWS_EMPRESA_CREATE_OR_UPDATE_FORM = "empresa/formularioModificarEmpresa";
    private final EmpresaRepository empresas;


    public EmpresaController(EmpresaRepository tamarithzService) {
        this.empresas = tamarithzService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/empresa/new")
    public String initCreationForm(Map<String, Object> model) {
        Empresa empresa = new Empresa();
        model.put("empresa", empresa);
        return VIEWS_EMPRESA_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/empresa/new")
    public String processCreationForm(@Valid Empresa empresa, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_EMPRESA_CREATE_OR_UPDATE_FORM;
        } else {
            this.empresas.save(empresa);
            return "redirect:/empresa/" + empresa.getId();
        }
    }

    @GetMapping("/empresa/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("empresa", new Empresa());
        return "empresa/findEmpresa";
    }

    @GetMapping("/empresas")
    public String processFindForm(Empresa empresa, BindingResult result, Map<String, Object> model) {

        if (empresa.getIdentificador() == null) {
            empresa.setIdentificador(""); // cadena vacía para el tipo de busqueda amplio (todas)
        }

        Collection<Empresa> results = this.empresas.findByIdentificador(empresa.getIdentificador());
        if (results.isEmpty()) {
            result.rejectValue("identificador", "notFound", "not found");
            return "empresa/findEmpresa";
        } else if (results.size() == 1) {
            // una sola empresa encontrada
            empresa = results.iterator().next();
            return "redirect:/empresa/" + empresa.getId();
        } else {
            // varias empresas encontradas
            model.put("selections", results);
            return "empresa/empresaList";
        }
    }

    @GetMapping("/empresa/{empresaId}/edit")
    public String initUpdateEmpresaForm(@PathVariable("empresaId") int empresaId, Model model) {
        Empresa empresa = this.empresas.findById(empresaId);
        model.addAttribute(empresa);
        return VIEWS_EMPRESA_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/empresa/{empresaId}/edit")
    public String processUpdateEmpresaForm(@Valid Empresa empresa, BindingResult result, @PathVariable("empresaId") int empresaId) {
        if (result.hasErrors()) {
            return VIEWS_EMPRESA_CREATE_OR_UPDATE_FORM;
        } else {
            empresa.setId(empresaId);
            this.empresas.save(empresa);
            return "redirect:/empresa/{empresaId}";
        }
    }

    @GetMapping("/empresa/{empresaId}")
    public ModelAndView showEmpresa(@PathVariable("empresaId") int empresaId) {
        ModelAndView mav = new ModelAndView("empresa/empresaDetails");
        mav.addObject(this.empresas.findById(empresaId));
        return mav;
    }

}
