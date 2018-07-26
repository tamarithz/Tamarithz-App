
package org.empleodigital.project.tamarithz.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class CrashController {

    @GetMapping("/oups")
    public String triggerException() {
        throw new RuntimeException("Error");
    }

}
