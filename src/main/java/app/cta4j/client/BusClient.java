package app.cta4j.client;

import app.cta4j.model.DirectionResponse;
import app.cta4j.model.RouteResponse;
import app.cta4j.model.StopResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BusClient {
    @GetExchange("/getroutes")
    ResponseEntity<RouteResponse> getRoutes();

    @GetExchange("/getdirections")
    ResponseEntity<DirectionResponse> getRouteDirections(@RequestParam("rt") String id);

    @GetExchange("/getstops")
    ResponseEntity<StopResponse> getRouteStops(@RequestParam("rt") String id, @RequestParam("dir") String direction);
}
