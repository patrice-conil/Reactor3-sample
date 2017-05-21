package com.pconil.reactor3;


import com.github.tomakehurst.wiremock.extension.responsetemplating.*;
import com.github.tomakehurst.wiremock.junit.*;
import org.junit.*;
import reactor.core.publisher.*;

import java.time.*;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*;
import static reactor.core.publisher.Flux.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

    @Rule
    public WireMockRule wm = new WireMockRule(options()
            .extensions(new ResponseTemplateTransformer(false))
    );

    private String baseURL = "http://localhost";

    String url = "/GetProgramList";
    String body = ("This is a nice response from my http mock server");

    @Before
    public void setUp() {
        wm.stubFor(get(urlPathEqualTo("/GetProgramList"))
                .willReturn(aResponse()
                        .withBody("{\"externalId\"=\"{{request.query.external_id}}\",\"channelId\"=\"TF1\"}")
                        .withTransformers("response-template")));

        wm.stubFor(get(urlPathEqualTo("/GetExtendeProgram"))
                .willReturn(aResponse()
                        .withBody("{\"externalId\"=\"{{request.query.external_id}}\",\"channelId\"=\"TF1\",\"summary\"=\"this is the summary\"}")
                        .withTransformers("response-template")));
    }

    @After
    public void tearDown() {

    }


}