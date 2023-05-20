package com.interview.carrecords.service;

import io.grpc.stub.StreamObserver;

public class RecordsService extends RecordsServiceGrpc.RecordsServiceImplBase {

    @Override
    public void saveRecord(RecordSaveRequest request, StreamObserver<RecordSaveResponse> responseObserver) {

        // call repository to load the data from database
        // we have added static data for example
        RecordSaveResponse recordResp = RecordSaveResponse.newBuilder().build();

        // set the response object
        responseObserver.onNext(recordResp);

        // mark process is completed
        responseObserver.onCompleted();
    }
}
