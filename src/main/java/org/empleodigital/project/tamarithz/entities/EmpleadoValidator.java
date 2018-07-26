package org.empleodigital.project.tamarithz.entities;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> para el formulario de <code>Empleado</code>.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 **/
public class EmpleadoValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public void validate(Object obj, Errors errors) {
        Empleado empleado = (Empleado) obj;
        String name = empleado.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // birth date validation
        if (empleado.getName() == null) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Empleado instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Empleado.class.isAssignableFrom(clazz);
    }


}
