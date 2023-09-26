package schule.bbs2.j2023.efi3b.computerroomreservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import schule.bbs2.j2023.efi3b.computerroomreservation.controller.params.RoomReservationWeekOverviewQueryParameters;
import schule.bbs2.j2023.efi3b.computerroomreservation.dto.RoomReservationWeekOverview;
import schule.bbs2.j2023.efi3b.computerroomreservation.service.ComputerRoomReservationService;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class ComputerRoomReservationController {

    private final ComputerRoomReservationService service;


    /**
     * Endpoint to load all reservations
     * @param params
     * @return
     */
    @GetMapping("/rooms/weekOverview")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RoomReservationWeekOverview getWeekOverview(RoomReservationWeekOverviewQueryParameters params) {
        params.validate();
        return service.getComputerRoomReservationWeekOverview(params);
    }
}
