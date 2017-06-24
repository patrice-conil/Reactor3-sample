package com.pconil.reactor3;

import com.github.tomakehurst.wiremock.extension.responsetemplating.*;
import com.github.tomakehurst.wiremock.junit.*;
import org.junit.*;
import reactor.core.publisher.*;

import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*;
import static reactor.core.publisher.Flux.range;

/**
 * Created by patrice on 21/05/2017.
 */
public class RTVClientTest {
//    @Rule
//    public WireMockRule wm = new WireMockRule(options()
//            .extensions(new ResponseTemplateTransformer(false))
//    );

    private String baseURL = "http://localhost";

    String url = "/GetProgramList";
    String body = ("This is a nice response from my http mock server");

//    @Before
//    public void setUp() {
//        wm.stubFor(get(urlPathEqualTo("/GetExtendedProgram"))
//                .willReturn(aResponse()
//                        .withBody("{\"externalId\"=\"{{request.query.external_id}}\",\"channelId\"=\"TF1\",\"summary\"=\"this is the summary\"}")
//                        .withTransformers("response-template")));
//
//        wm.stubFor(get(urlPathEqualTo("/GetProgramList"))
//                .willReturn(aResponse()
//                        .withBody("{\"externalId\"=\"{{request.query.external_id}}\",\"channelId\"=\"TF1\"}")
//                        .withTransformers("response-template")));
//
//    }

    @After
    public void tearDown() {

    }

    @Test
    public void getTenPrograms() throws Exception {
        RTVClient rtvClient = new RTVClient();
        Flux<Program> flux = Flux.empty();

        List<Program> programs = range(1, 10).flatMap(s -> {
            return flux.mergeWith(rtvClient.getRTVProgram(s.toString(), LocalDate.now()).log().subscribe());
        }).collectList().subscribe().block(Duration.ofMillis(10000L));

        assertNotNull(programs);
        assertEquals(programs.size(), 10);
    }

    @Test
    public void getOneProgram() throws Exception {
        RTVClient rtvClient = new RTVClient();

        Program program = rtvClient.getRTVProgram("1", LocalDate.now())
                .log().subscribe().block(Duration.ofMillis(1500L));

        assertNotNull(program);
        assertEquals(program.getChannelId(), "TF1");
        assertEquals(program.getExternalId(), "1");
    }

    @Test
    public void getProgramThenExtendedProgram() throws Exception {
        RTVClient rtvClient = new RTVClient();

        ExtendedProgram program =  rtvClient.getRTVExtendedProgram(rtvClient.getRTVProgram("1", LocalDate.now())
                        .log().subscribe().block().getExternalId())
                        .subscribe().block(Duration.ofMillis(3000L));

        assertNotNull(program);
        assertEquals(program.getChannelId(), "TF1");
        assertEquals(program.getExternalId(), "1");
        assertEquals(program.getSummary(), "this is the summary");
    }

    @Test
    public void getExtendedProgram() throws Exception {
        RTVClient rtvClient = new RTVClient();

        ExtendedProgram program = rtvClient.getRTVExtendedProgram("1")
                .log().subscribe().block(Duration.ofMillis(2000L));

        assertNotNull(program);
        assertEquals(program.getChannelId(), "TF1");
        assertEquals(program.getExternalId(), "1");
        assertEquals(program.getSummary(), "this is the summary");
    }

    @Test
    public void dummy() {
        Flux<List<String>> listFlux = Flux.empty();
        List<String> strings = Arrays.asList("1", "2", "3", "4","5");

        List<String> list = Flux.fromIterable(strings).flatMap(id -> Mono.just(Arrays.asList(id))).collect(Collectors.toList())
                .subscribe().block().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        assertEquals(5, list.size());
        assertEquals("1", list.get(0));

    }

    Collector collector = new Collector() {
        @Override
        public Supplier supplier() {
            return null;
        }

        @Override
        public BiConsumer accumulator() {
            return null;
        }

        @Override
        public BinaryOperator combiner() {
            return null;
        }

        @Override
        public Function finisher() {
            return null;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return null;
        }
    };
}