package com.upadhyde.android.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Input {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "deliveryNo")
    private long deliveryNo;

    @ColumnInfo(name = "plantId")
    private long plantId;

    @ColumnInfo(name = "deliveryType")
    private String deliveryType;

    @ColumnInfo(name = "deliveryItemNo")
    private long deliveryItemNo;

    @ColumnInfo(name = "materialNo")
    private long materialNo;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "gtinNumber")
    private long gtinNumber;

    @ColumnInfo(name = "batchItemNo")
    private long batchItemNo;

    @ColumnInfo(name = "fefoBatchNo")
    private String fefoBatchNo;

    @ColumnInfo(name = "fefoPickedQuantity")
    private String fefoPickedQuantity;

    @ColumnInfo(name = "salesUnit")
    private String salesUnit;

    @ColumnInfo(name = "noOfBoxes")
    private long noOfBoxes;

    @ColumnInfo(name = "conversionFactorUom")
    private long conversionFactorUom;

    @ColumnInfo(name = "conversionFactorBoxes")
    private long conversionFactorBoxes;

    @ColumnInfo(name = "customerCity")
    private String customerCity;

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public long getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(long deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getGtinNumber() {
        return gtinNumber;
    }

    public void setGtinNumber(long gtinNumber) {
        this.gtinNumber = gtinNumber;
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

    public String getFefoPickedQuantity() {
        return fefoPickedQuantity;
    }

    public void setFefoPickedQuantity(String fefoPickedQuantity) {
        this.fefoPickedQuantity = fefoPickedQuantity;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public long getNoOfBoxes() {
        return noOfBoxes;
    }

    public void setNoOfBoxes(long noOfBoxes) {
        this.noOfBoxes = noOfBoxes;
    }

    public long getConversionFactorUom() {
        return conversionFactorUom;
    }

    public void setConversionFactorUom(long conversionFactorUom) {
        this.conversionFactorUom = conversionFactorUom;
    }

    public long getConversionFactorBoxes() {
        return conversionFactorBoxes;
    }

    public void setConversionFactorBoxes(long conversionFactorBoxes) {
        this.conversionFactorBoxes = conversionFactorBoxes;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }
}
