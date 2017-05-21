package com.pconil.reactor3;

import com.google.gson.*;
import reactor.core.publisher.*;
import reactor.core.scheduler.*;
import reactor.ipc.netty.http.client.*;

import java.nio.charset.*;
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by patrice on 18/05/2017.
 */
public class RTVClient {
    HttpClient httpClient = HttpClient.create("localhost", 8080);
    Scheduler scheduler = Schedulers.newParallel("Parallel", 10, true);
    Gson gson = new GsonBuilder().create();

    Mono<Program> getRTVProgram(String external_id, LocalDate date) {
        return httpClient.get("/GetProgramList?external_id=" + external_id)
                .timeout(Duration.ofMillis(2000L))
                .then(s -> s.receive().asString().collect(Collectors.joining()))
                .map(
                        it -> gson.fromJson(it, Program.class));
    }

    Mono<ExtendedProgram> getRTVExtendedProgram(String external_id) {
        return httpClient.get("/GetExtendedProgram?external_id=" + external_id)
                .timeout(Duration.ofMillis(2000L))
                .then(s -> s.receive().asString().collect(Collectors.joining()))
                .map(
                        it -> gson.fromJson(it, ExtendedProgram.class));
    }
}