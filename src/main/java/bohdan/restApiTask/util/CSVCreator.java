package bohdan.restApiTask.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CSVCreator {
    private String csvFileName;
    private String[] csvHeader;
    List<List<String>> csvBody;

    public InputStreamResource createInputStreamResource(){

        ByteArrayInputStream byteArrayOutputStream;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(csvHeader))) {
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {throw new RuntimeException(e.getMessage());}

        return new InputStreamResource(byteArrayOutputStream);

    }
    public HttpHeaders createHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        return headers;
    }
}
