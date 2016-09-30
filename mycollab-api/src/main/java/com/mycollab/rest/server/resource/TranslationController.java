package com.mycollab.rest.server.resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@RestController
@RequestMapping(value = "/localization")
public class TranslationController {

    @RequestMapping(value = "translations", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity getAllTranslations() {
        String plainCreds = "haiphucnguyen@gmail.com:=5EqGRN5Y=<%`tbX";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<byte[]> response = template.exchange("https://api.getlocalization.com/mycollab/api/translations/zip/", HttpMethod.GET,
                request, byte[].class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            HttpHeaders resultHeader = new HttpHeaders();
            resultHeader.set("Content-Disposition", "attachment; filename=mycollab.zip");
            resultHeader.setContentLength(response.getBody().length);
            return new ResponseEntity(response.getBody(), resultHeader, HttpStatus.OK);
        } else {
            return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
