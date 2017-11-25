package com.example.anthony.clinicplace;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anthony on 09/11/2017.
 */


public class MapperHelper {

    private static String COGNITO_POOL_ID = "us-east-1:539d8ea8-54fa-4ec5-9cf0-47ff69d1e1e9";
    private static Regions COGNITO_REGION = Regions.US_EAST_1;
    public static final String BUCKET_NAME = "clinicplace-deployments-mobilehub-2110396219";
    private static CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonDynamoDBClient ddbClient;
    private static AmazonS3Client s3;
    private static TransferUtility sTransferUtility;
    DynamoDBMapper mapper;

    public static AmazonS3Client getS3Client() {
         return s3;
    }
    public MapperHelper(Context context)
    {
        credentialsProvider = new CognitoCachingCredentialsProvider(context, COGNITO_POOL_ID, COGNITO_REGION);
        ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        s3 = new AmazonS3Client(credentialsProvider);
        mapper = new DynamoDBMapper(ddbClient);

     }

    public static TransferUtility getTransferUtility(Context context) {
        if (sTransferUtility == null) {
            sTransferUtility = new TransferUtility(getS3Client(),
                    context.getApplicationContext());
        }

        return sTransferUtility;
    }


}
