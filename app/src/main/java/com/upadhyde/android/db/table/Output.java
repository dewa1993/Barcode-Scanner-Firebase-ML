package com.upadhyde.android.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Output {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "batchItemNo")
    private long batchItemNo;

    @ColumnInfo(name = "plantId")
    private long plantId;

    @ColumnInfo(name = "hhtName")
    private String hhtName;

    @ColumnInfo(name = "deliveryNo")
    private long deliveryNo;

    @ColumnInfo(name = "deliveryItemNo")
    private long deliveryItemNo;

    @ColumnInfo(name = "materialNo")
    private long materialNo;

    @ColumnInfo(name = "fefoBatchNo")
    private String fefoBatchNo;

    @ColumnInfo(name = "fefoPickedQuantity")
    private long fefoPickedQuantity;

    @ColumnInfo(name = "salesUnit")
    private String salesUnit;

    @ColumnInfo(name = "actualBatch")
    private String actualBatch;

    @ColumnInfo(name = "actualPgiQuantity")
    private long actualPgiQuantity;

    @ColumnInfo(name = "expiryDate")
    private String expiryDate;

    @ColumnInfo(name = "fefoDeviation")
    private String fefoDeviation;

    @ColumnInfo(name = "shipperId")
    private long shipperId;

    @ColumnInfo(name = "smsCode")
    private double smsCode;

    @ColumnInfo(name = "gtinNumber")
    private long gtinNumber;


    public Output(long plantId, long deliveryNo, long materialNo, String fefoBatchNo, String actualBatch, long gtinNumber, long batchItemNo) {
        this.plantId = plantId;
        this.deliveryNo = deliveryNo;
        this.materialNo = materialNo;
        this.fefoBatchNo = fefoBatchNo;
        this.actualBatch = actualBatch;
        this.gtinNumber = gtinNumber;
        this.batchItemNo = batchItemNo;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public String getHhtName() {
        return hhtName;
    }

    public void setHhtName(String hhtName) {
        this.hhtName = hhtName;
    }

    public long getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(long deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public long getDeliveryItemNo() {
        return deliveryItemNo;
    }

    public void setDeliveryItemNo(long deliveryItemNo) {
        this.deliveryItemNo = deliveryItemNo;
    }

    public long getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(long materialNo) {
        this.materialNo = materialNo;
    }

    public long getBatchItemNo() {
        return batchItemNo;
    }

    public void setBatchItemNo(long batchItemNo) {
        this.batchItemNo = batchItemNo;
    }

    public String getFefoBatchNo() {
        return fefoBatchNo;
    }

    public void setFefoBatchNo(String fefoBatchNo) {
        this.fefoBatchNo = fefoBatchNo;
    }

    public long getFefoPickedQuantity() {
        return fefoPickedQuantity;
    }

    public void setFefoPickedQuantity(long fefoPickedQuantity) {
        this.fefoPickedQuantity = fefoPickedQuantity;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getActualBatch() {
        return actualBatch;
    }

    public void setActualBatch(String actualBatch) {
        this.actualBatch = actualBatch;
    }

    public long getActualPgiQuantity() {
        return actualPgiQuantity;
    }

    public void setActualPgiQuantity(long actualPgiQuantity) {
        this.actualPgiQuantity = actualPgiQuantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFefoDeviation() {
        return fefoDeviation;
    }

    public void setFefoDeviation(String fefoDeviation) {
        this.fefoDeviation = fefoDeviation;
    }

    public long getShipperId() {
        return shipperId;
    }

    public void setShipperId(long shipperId) {
        this.shipperId = shipperId;
    }

    public double getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(double smsCode) {
        this.smsCode = smsCode;
    }

    public long getGtinNumber() {
        return gtinNumber;
    }

    public void setGtinNumber(long gtinNumber) {
        this.gtinNumber = gtinNumber;
    }
}
