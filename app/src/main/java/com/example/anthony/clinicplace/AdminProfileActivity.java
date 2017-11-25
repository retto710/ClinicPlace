package com.example.anthony.clinicplace;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

public class AdminProfileActivity extends AppCompatActivity {

    private static  String DB_NAME = "clinicplace-mobilehub-2110396219-Clinica";
    private static  String COGNITO_POOL_ID = "us-east-1:539d8ea8-54fa-4ec5-9cf0-47ff69d1e1e9";
    private static  Regions COGNITO_REGION = Regions.US_EAST_1;
    private static  Context context;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private Button registrarClinica;
    private Button verClinica;
    private    AmazonDynamoDBClient ddbClient;


    private ArrayList<Clinica> clinicas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        registrarClinica= (Button)findViewById(R.id.nuevaClnica);
        verClinica=(Button)findViewById(R.id.verClnica);

        registrarClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


        verClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProfileActivity.this,ClinicActivity.class);
                startActivity(intent);
            }
        });


    }
}
