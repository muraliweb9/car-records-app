package com.interview.carrecords.service;

import com.interview.proto.carrecords.service.RecordSaveRequest;
import com.interview.proto.carrecords.service.RecordSaveResponse;
import com.interview.proto.carrecords.service.RecordsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class RecordsService extends RecordsServiceGrpc.RecordsServiceImplBase {

    @Override
    public void saveRecord(RecordSaveRequest request, StreamObserver<RecordSaveResponse> responseObserver) {

        RecordSaveResponse recordResp =
                RecordSaveResponse.newBuilder().setRecordId("1").build();

        // set the response object
        responseObserver.onNext(recordResp);

        // mark process is completed
        responseObserver.onCompleted();
    }
}
