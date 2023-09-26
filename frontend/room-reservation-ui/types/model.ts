export type WeekOverview =
    {
        roomName: string,
        date: string,
        weekDay: string,
        weekReservationList: RoomReservation[]
    }

export type RoomReservation =
    {
        weekday: string,
        teacher: string,
        startTime: Date,
        endTime: Date,
        lessonNr: string,
        status: string
    }