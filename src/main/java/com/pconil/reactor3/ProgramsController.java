package com.pconil.reactor3;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by patrice on 19/05/2017.
 */
@RestController
@EnableWebMvc
public class ProgramsController {
    @RequestMapping("/GetProgramList")
    public ResponseEntity<Program> getPrograms(@RequestParam(name = "external_id") String externalId) {

        return new ResponseEntity(new Program(externalId, "TF1"), HttpStatus.OK);
    }
}
