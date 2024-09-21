package com.thc.realspr.service;

import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;

import java.util.concurrent.TimeUnit;

@Service
public class FirebaseService {

    public String getSignedUrl(String filePath) {
        Storage storage = StorageClient.getInstance().bucket().getStorage();
        String bucketName = StorageClient.getInstance().bucket().getName();

        BlobId blobId = BlobId.of(bucketName, filePath);
        Blob blob = storage.get(blobId);

        if (blob == null) {
            throw new RuntimeException("File not found: " + filePath);
        }

        return blob.signUrl(30, TimeUnit.MINUTES).toString();
    }
}
