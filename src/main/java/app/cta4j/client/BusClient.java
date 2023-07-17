package app.cta4j.client;

import app.cta4j.model.BusResponse;
import app.cta4j.model.DirectionResponse;
import app.cta4j.model.RouteResponse;
import app.cta4j.model.StopResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BusClient {
    @GetExchange("/getroutes")
    RouteResponse getRoutes();

    @GetExchange("/getdirections")
    DirectionResponse getRouteDirections(@RequestParam("rt") String id);

    @GetExchange("/getstops")
    StopResponse getRouteStops(@RequestParam("rt") String id, @RequestParam("dir") String direction);

    @GetExchange("/getpredictions")
    BusResponse getBuses(@RequestParam("rt") String routeId, @RequestParam("stpid") int stopId);
}
