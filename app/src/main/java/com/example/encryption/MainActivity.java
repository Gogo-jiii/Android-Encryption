package com.example.encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    Button btnEncryptData, btnDecryptData, btnCreateSHA256, btnEncodeBase64, btnDecodeBase64;
    TextView txtResult;
    TextInputLayout tilData;
    private EncryptionManager encryptionManager;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEncryptData = findViewById(R.id.btnEncryptData);
        btnDecryptData = findViewById(R.id.btnDecryptData);
        btnCreateSHA256 = findViewById(R.id.btnCreateSHA256);
        btnEncodeBase64 = findViewById(R.id.btnEncodeBase64);
        btnDecodeBase64 = findViewById(R.id.btnDecodeBase64);

        txtResult = findViewById(R.id.txtResult);
        tilData = findViewById(R.id.tilData);

        encryptionManager = EncryptionManager.getInstance();

        btnEncryptData.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String data = tilData.getEditText().getText().toString();
                if (!TextUtils.isEmpty(data)) {
                    result = encryptionManager.encrypt(data);
                    txtResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Field is empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDecryptData.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!TextUtils.isEmpty(result)) {
                    result = encryptionManager.decrypt(result);
                    txtResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "No data to decrypt.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCreateSHA256.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String data = tilData.getEditText().getText().toString();
                if (!TextUtils.isEmpty(data)) {
                    result = encryptionManager.getSHA256(data);
                    txtResult.setText(result);

                    if (result.equals(encryptionManager.getSHA256(data))) {
                        Log.d("TAG", "same");
                    } else {
                        Log.d("TAG", "NOT same");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Field is empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEncodeBase64.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String data = tilData.getEditText().getText().toString();
                if (!TextUtils.isEmpty(data)) {
                    result = encryptionManager.encodeBase64(data);
                    txtResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Field is empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDecodeBase64.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!TextUtils.isEmpty(result)) {
                    result = encryptionManager.decodeBase64(result);
                    txtResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "No data to decode.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}