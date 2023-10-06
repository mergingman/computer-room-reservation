package schule.bbs2.j2023.efi3b.computerroomreservation.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schule.bbs2.j2023.efi3b.computerroomreservation.controller.params.RoomReservationWeekOverviewQueryParameters;
import schule.bbs2.j2023.efi3b.computerroomreservation.dto.ComputerRoomReservationDTO;
import schule.bbs2.j2023.efi3b.computerroomreservation.dto.RoomReservationWeekOverviewDTO;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.ComputerRoomReservation;
import schule.bbs2.j2023.efi3b.computerroomreservation.service.ComputerRoomReservationService;

@Controller
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ComputerRoomReservationController {

    private final ComputerRoomReservationService service;


    /**
     * Endpoint to load all reservations within a week of a specified date and room
     * @param params
     * @return
     */
    @GetMapping("/weekOverview")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RoomReservationWeekOverviewDTO getWeekOverview(RoomReservationWeekOverviewQueryParameters params) {
        params.validate();
        return service.getComputerRoomReservationWeekOverview(params);
    }

    @GetMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<ComputerRoomReservationDTO> getPaginatedReservations(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String filter
    ) {
        if(filter != null) {
            StringUtils.remove(filter, '\'');
            StringUtils.remove(filter, '\"');
        }
        return service.getPaginatedReservations(pageable);
    }
}
