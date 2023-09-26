package schule.bbs2.j2023.efi3b.computerroomreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIDelegateController {

    @GetMapping("/rooms")
    public String rooms() {
        return delegateToSinglePageApplication();
    }

    private String delegateToSinglePageApplication() {
        return "forward:index.html";
    }
}
