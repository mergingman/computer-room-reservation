import type {RoomReservation} from "./model";

export type WeekOverviewResponse =
    {
        roomName: string,
        date: string,
        weekDay: string,
        weekReservationList: RoomReservation[]
    }