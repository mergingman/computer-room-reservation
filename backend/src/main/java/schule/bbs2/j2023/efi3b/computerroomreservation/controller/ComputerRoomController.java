package schule.bbs2.j2023.efi3b.computerroomreservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import schule.bbs2.j2023.efi3b.computerroomreservation.service.ComputerRoomService;

import java.util.List;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ComputerRoomController {

    private final ComputerRoomService service;

    @GetMapping("/computerRoomNames")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<String> getRoomNames() {
        return service.getRoomNames();
    }
}
