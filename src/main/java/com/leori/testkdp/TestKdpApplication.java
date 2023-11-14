package com.leori.testkdp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@SpringBootApplication
public class TestKdpApplication {

    public static final String BODY_EXT_OCR_CE = "{\n" +
            "    \"AuthFlow\": \"REFRESH_TOKEN_AUTH\",\n" +
            "    \"ClientId\": \"7ipnhod4s9iibr6d8icl0o3ba5\",\n" +
            "    \"AuthParameters\": {\n" +
            "        \"REFRESH_TOKEN\": \"eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.OmFej2oxJBHRD1_GgxS4_QW_eDDyOdqoJP6S_A-W7ya4l2w10IztULRE5HYoi3cdL2cwmT355-dOU1GP4D3JIRoMKg-kNaLDbNopyjXDAfegbNf-BaYYYtt1HE4FhrA4LRh7IFOCPTeqjxIFvX0Wl1uHxY13JiXP9SAQcCFY2ULWj9KrtcmfiEBaUlgRaW2t7CqjLFrhybn54b-fkZwH5Z4PgQhmEKwPd_n2TnEpPqJJWc9IizVZl1Q4RHJ_F42S-DpTqamEt7BlsRpMfGTgW4Ue-xVP0lze1kFDtVAoOaR7NdHoGliRnU-IwlxXmf3I1Ml_3ytSVGnPGNgXFmjC2A.tIWVY_ZGeB3zszYn.uCTna8cmzRghjltz0pEe5Kc2Mf2ZrvlIvJNDFXozGLU7NLZX8aZBAJv8X7nqYK6BEPEz8whwrxj6ZHIm63_KUy_-ytx4J17R6Ke6uWrXfxHWLhjys9Vp0m8BRQLGkKAEz3UpSIZB4sA7D-klmMdcvWe7Lp17bpK6j0lUvsveovSrYaunWPqmCaJGrm-ruVDKtJRNTbN6DGN0TEV0ktd6UA1sCL0xHknIOcmk8bRr9-FjX1GGDvi8gPS0K34UStnESCUnZ7rwPBuevbCTm5ufY28cvL9RklE7zhD-7ArnkC1gAx5z7KGG90-h3n8wjABEPG62LFf3mLw_DzqJ0vzN-fp5bOjY1sURbBwhofBy8K9L3oKLht94MM8Kf3tv9zCfb68u19E42dTct6lDq6laaPopan5_0S62ATvMhiW64wcGZ-L5pdVrgKdRnSuvGbwnE3o4gGrtkypNMIAfBxGKX35AVqSalM9VsKBKj3UvSJ9xNxybza7-v0BVVzZWD6jIQZ8c6wFEGRRU_Bcc1K2Llq_odf3iS5NCe2-hkMkPXwux_FEB80BhiBHBDgWD-NZaSiiDnlFvpSaemPT54DHKbe9mBeGstquzOhQFhCGJ-fOeorrZWwdREv3MueL2oKlIZ3pzh3FkfspEXtDFYmMXzMmRAM-5mx-uSr9kT5zDeipEdltfHkJEaZbgWmrDCq-OJ-DA7BJTAPbGqf2ky3Si_ej-XCE-Y9u70dPnBU2lx3kNxlkXa2xfXPerRCFY_Lwrm8WNNmScg0vh2JVs0J7BZnn5Xci36AUyfiPIZlOOjMdZ2tV94CZV83P6yTDsfwE1OYdS75kfvHBoxi6UawskuPzIe2diIHUmXivk1CLAS1zAfEhIsIU50UntvZe-Bk9i1cGPe-0Iq9-5T-K-7JNgMj3vguA9jt0WVyrkqGC3SjLUvf_7MDpzX6sgLpLkzVyiH_z2L7NoXahevhduS9ybsDxJTsr8OZBqAQvZgcxqhPKpNb8HYC8WUgeh-X51gsCV4qdSwkWRLJzMiaGHeqvveMs4jk29dMi20cq54Oz7LBETCBR2ycrzPaWHVQQLR7Nr7gDif_mWxt8jNTUG6ASxgXzOn1_Azt007199EUMXoNUhdAZH7z49wJpkicBrDaVd40wEJWcYRjp2L7vT_8aoz_el3zUJddActmtOC74sTJf6AIdW9vmo8vpY7PRdRM12EW5Gby9LHj6i30Te-vUNOXWebhQvvTsWRaEXBV-Cu_jV_ztq6yGhkd7EixRLEdZJReAbqwe8ArQqfhtNFGLQFNzlj5yvkYKW0VA3HDuA0G9r74cFnRAi-UGo5Vw.7B16_07ykxMkxyslIKjMEg\"\n" +
            "    }\n" +
            "}";

    public static final String MESSAGE = "El endpoint con id \"%s\" respondió con el HTTP code <%s>";

    public static final String OCR_EXT_TOKEN = "https://cognito-idp.us-east-1.amazonaws.com/";

    //    public static final String OCR_EXT_CREATE = "https://k334tpzhmc.execute-api.us-east-1.amazonaws.com/prod/uploades";
    public static final String OCR_EXT_CREATE = "https://e6n1cxcdzc.execute-api.us-east-1.amazonaws.com/dev/doc-upload";
    public static void main(String[] args) {
        SpringApplication.run(TestKdpApplication.class, args);
        System.out.println("test kdp");

        String idToken = getIdToken();

        System.out.println("test kdp ::::::::::::::::" + idToken);

        uploadFile(idToken);
    }

    private static String getIdToken() {
        System.out.println("getIdToken - Start");

        String idToken = null;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-amz-target", "AWSCognitoIdentityProviderService.InitiateAuth");
        headers.set("x-amz-user-agent", "aws-amplify/0.1.x js");
        headers.set("content-type", "application/x-amz-json-1.1");

        HttpEntity<String> request = new HttpEntity<>(BODY_EXT_OCR_CE, headers);

        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(OCR_EXT_TOKEN, request, String.class);

            System.out.println("Response code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

            JsonObject responseBody = parseResponseBody(response, OCR_EXT_TOKEN);
            if (responseBody != null) {
                idToken = responseBody.getAsJsonObject("AuthenticationResult").get("IdToken").getAsString();
            }

        } catch (RestClientException exception) {
            LOGGER.info("RestClientException::::::::::::::::::::::::: " + exception.getMessage());
//            this.addAdvice(ERROR_REST_CLIENT_EXCEPTION);
            return null;
        }

        LOGGER.info("getIdToken - End");
        return idToken;
    }

    private static JsonObject parseResponseBody(ResponseEntity<String> response, String urlId) {
        LOGGER.info("parseResponseBody - Start");

        JsonObject jsonBody = null;

        if (response != null) {
            HttpStatus responseStatus = response.getStatusCode();
            boolean statusIsError = responseStatus.value() > 399 && responseStatus.value() < 600;
            String body = response.getBody();

            if ((!statusIsError) && body != null && !body.isEmpty()) {
                jsonBody = new JsonParser().parse(body).getAsJsonObject();
            } else {
                LOGGER.info(String.format(MESSAGE, urlId, responseStatus));
            }
        }
        LOGGER.info("parseResponseBody - End");

        return jsonBody;
    }

    private static void uploadFile(String idToken) {

        LOGGER.info("uploadFile ::::::::::::::::: ");

        try {
            RestTemplate restTemplate = new RestTemplate();

            String filename = "archivo.pdf";
            String jobType = "balances";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
//            httpHeaders.add("headfilename", filename);
//            httpHeaders.add("headjobtype", jobType);
            httpHeaders.add("authorization", idToken);
//            httpHeaders.add("validate", "true");

//            File file = new File("/Volumes/MICROSD/source AOCR/1604342844940.pdf");
            byte[] fileContent = Files.readAllBytes(Paths.get("/Volumes/MICROSD/source AOCR/1604342844940.pdf"));
//            FileSystemResource fileSystemResource = new FileSystemResource(file);

//            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//            body.add("file", new ByteArrayResource(inputDTO.getFile()) {
//                @Override
//                public String getFilename() {
//                    return filename;
//                }
//            });

//            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, httpHeaders);
//            HttpEntity<byte[]> request = new HttpEntity<>(fileContent, httpHeaders);



            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            HttpHeaders pdfHeaders = new HttpHeaders();
            pdfHeaders.setContentDispositionFormData("incoming_file", "archivo.pdf");
            pdfHeaders.setContentType(MediaType.APPLICATION_PDF);

            body.add("incoming_file", new HttpEntity<byte[]>(fileContent, pdfHeaders));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(body, httpHeaders);

            LOGGER.info("antes del llamado KDP ::::::::::::::::: ");
            ResponseEntity<String> res = restTemplate.postForEntity(OCR_EXT_CREATE, requestEntity, String.class);
            LOGGER.info("despues del llamado KDP ::::::::::::::::: ");
            String response = validateStatus(res);
            if(response == null) {
//                this.addAdvice(ERROR_RESPONSE_NULL);
//                return null;
                LOGGER.info("null response::::::::::::");
            } else {
                LOGGER.info("pass::::::::::::");

                JsonObject responseBody = new JsonParser().parse(response).getAsJsonObject();
                LOGGER.info("responseBody ::::::::::::::: {}"+ responseBody.toString());
            }

        } catch(RestClientException e) {
            LOGGER.info("RestClientException::::::::::::::::::::::::: "+ e.getMessage());
//            this.addAdvice(ERROR_REST_CLIENT_EXCEPTION);
//            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String validateStatus(ResponseEntity<String> res) {
        LOGGER.info("validateStatus :::::::::::::::::::::::::::::::::::::::");
        HttpStatus status = res.getStatusCode();

        int statusResponse = res.getStatusCode().value();
        String body = res.getBody();
        if ((statusResponse == 200 || statusResponse == 201 || statusResponse == 202) && body != null && !body.isEmpty()) {
            return body;
        }
        return null;
    }

}

