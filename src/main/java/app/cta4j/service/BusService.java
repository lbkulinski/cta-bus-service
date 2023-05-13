package app.cta4j.service;

import app.cta4j.client.BusClient;
import app.cta4j.exception.DataFetcherException;
import app.cta4j.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public final class BusService {
    private final BusClient client;

    @Autowired
    public BusService(BusClient client) {
        Objects.requireNonNull(client);

        this.client = client;
    }

    public Set<Route> getRoutes() {
        ResponseEntity<RouteResponse> responseEntity = this.client.getRoutes();

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (!statusCode.isSameCodeAs(HttpStatus.OK) || !responseEntity.hasBody()) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        RouteResponse response = responseEntity.getBody();

        if (response == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        RouteBody body = response.body();

        if (body == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        Set<Route> routes = body.routes();

        if (routes == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        return routes;
    }

    public Set<Direction> getRouteDirections(String id) {
        Objects.requireNonNull(id);

        ResponseEntity<DirectionResponse> responseEntity = this.client.getRouteDirections(id);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (!statusCode.isSameCodeAs(HttpStatus.OK) || !responseEntity.hasBody()) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        DirectionResponse response = responseEntity.getBody();

        if (response == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        DirectionBody body = response.body();

        if (body == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        Set<Direction> directions = body.directions();

        if (directions == null) {
            String message = "Directions with the specified route ID could not be found";

            throw new DataFetcherException(message, ErrorType.NOT_FOUND);
        }

        return directions;
    }

    public Set<Stop> getRouteStops(String id, String direction) {
        Objects.requireNonNull(id);

        Objects.requireNonNull(direction);

        ResponseEntity<StopResponse> responseEntity = this.client.getRouteStops(id, direction);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (!statusCode.isSameCodeAs(HttpStatus.OK) || !responseEntity.hasBody()) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        StopResponse response = responseEntity.getBody();

        if (response == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        StopBody body = response.body();

        if (body == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        Set<Stop> stops = body.stops();

        if (stops == null) {
            String message = "Stops with the specified route ID and direction could not be found";

            throw new DataFetcherException(message, ErrorType.NOT_FOUND);
        }

        return stops;
    }

    public Set<Bus> getBuses(String routeId, int stopId) {
        Objects.requireNonNull(routeId);

        if (stopId <= 0) {
            String message = "The specified stop ID must be positive integer";

            throw new DataFetcherException(message, ErrorType.BAD_REQUEST);
        }

        ResponseEntity<BusResponse> responseEntity = this.client.getBuses(routeId, stopId);

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (!statusCode.isSameCodeAs(HttpStatus.OK) || !responseEntity.hasBody()) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        BusResponse response = responseEntity.getBody();

        if (response == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        BusBody body = response.body();

        if (body == null) {
            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

        Set<Bus> buses = body.buses();

        if (buses == null) {
            String message = "Buses with the specified route ID and stop ID could not be found";

            throw new DataFetcherException(message, ErrorType.NOT_FOUND);
        }

        return buses;
    }
}
