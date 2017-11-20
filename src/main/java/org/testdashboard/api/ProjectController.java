package org.testdashboard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @author Arne
 * @since 18/11/2017
 */
@RestController
public class ProjectController implements ProjectApi {

    @Override
    public ResponseEntity<BigDecimal> postProjectTextExecution(MultipartFile upfile) {
        return null;
    }
}
