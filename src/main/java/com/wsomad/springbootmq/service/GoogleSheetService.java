package com.wsomad.springbootmq.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleSheetService {
    private static final String APPLICATION_NAME = "SpringBoot MQ MultiConsumers";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final List<String> SCOPES = List.of(SheetsScopes.SPREADSHEETS);
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private final Sheets sheets;

    public GoogleSheetService() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = getClass().getResourceAsStream("/credential.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        this.sheets = new Sheets.Builder(
                httpTransport,
                GSON_FACTORY,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();
    }

    public void appendRow(String spreadsheetId, String sheetName, List<Object> values) throws IOException {
        ValueRange body = new ValueRange().setValues(Collections.singletonList(values));
        sheets.spreadsheets().values()
                .append(spreadsheetId, sheetName + "!A1", body)
                .setValueInputOption("RAW")
                .execute();
    }
}
