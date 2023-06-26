package app.cta4j.service;

import app.cta4j.client.BusClient;
import app.cta4j.exception.DataFetcherException;
import app.cta4j.model.*;
import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.Objects;
import java.util.Set;

@Service
public final class BusService {
    private final BusClient client;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BusService.class);
    }

    @Autowired
    public BusService(BusClient client, Rollbar rollbar) {
        Objects.requireNonNull(client);

        Objects.requireNonNull(rollbar);

        this.client = client;

        this.rollbar = rollbar;

        this.rollbar.debug("Hello, world!");
    }

    public Set<Route> getRoutes() {
        RouteResponse response;

        try {
            response = this.client.getRoutes();
        } catch (WebClientException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BusService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

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

        DirectionResponse response;

        try {
            response = this.client.getRouteDirections(id);
        } catch (WebClientException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BusService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

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

        StopResponse response;

        try {
            response = this.client.getRouteStops(id, direction);
        } catch (WebClientException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BusService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

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

        BusResponse response;

        try {
            response = this.client.getBuses(routeId, stopId);
        } catch (WebClientException e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            BusService.LOGGER.error(message, e);

            throw new DataFetcherException(ErrorType.INTERNAL_ERROR);
        }

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
