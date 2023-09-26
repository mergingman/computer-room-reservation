import type {WeekOverview} from "../../types/model";
import type {WeekOverviewResponse} from "../../types/dto";

export function parseWeekOverviewResponse(json: WeekOverviewResponse): WeekOverview {
    json.weekReservationList
        .map((reservation) => {
            const temp = {}
        })

}

function