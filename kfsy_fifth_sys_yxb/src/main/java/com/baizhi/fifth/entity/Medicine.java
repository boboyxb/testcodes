package com.baizhi.fifth.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Medicine implements Serializable {
    //定义私有属性
    private String id;
    private String unit;//单位
    private String nationalDrugName;//国药准字号
    private Date approvaldateOfNationalDrugName;//国药准字号批准日期
    private String ingredient;//明书成分
    private String description;//性状
    private String dosageAdministration;//用法和用量
    private String sideEffects;//不良反应
    private String contraindications;//禁忌
    private String precaution;//注意事项
    private String interaction;//药物相互作用
    private String depot;//贮藏
    private String packing;//包装
    private Date expiryDate;//有效期
    private String company;//生产企业

    private List<Picture> pictures;

    public Medicine(String id, String unit, String nationalDrugName, Date approvaldateOfNationalDrugName, String ingredient, String description, String dosageAdministration, String sideEffects, String contraindications, String precaution, String interaction, String depot, String packing, Date expiryDate, String company) {
        this.id = id;
        this.unit = unit;
        this.nationalDrugName = nationalDrugName;
        this.approvaldateOfNationalDrugName = approvaldateOfNationalDrugName;
        this.ingredient = ingredient;
        this.description = description;
        this.dosageAdministration = dosageAdministration;
        this.sideEffects = sideEffects;
        this.contraindications = contraindications;
        this.precaution = precaution;
        this.interaction = interaction;
        this.depot = depot;
        this.packing = packing;
        this.expiryDate = expiryDate;
        this.company = company;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", unit='" + unit + '\'' +
                ", nationalDrugName='" + nationalDrugName + '\'' +
                ", approvaldateOfNationalDrugName=" + approvaldateOfNationalDrugName +
                ", ingredient='" + ingredient + '\'' +
                ", description='" + description + '\'' +
                ", dosageAdministration='" + dosageAdministration + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", precaution='" + precaution + '\'' +
                ", interaction='" + interaction + '\'' +
                ", depot='" + depot + '\'' +
                ", packing='" + packing + '\'' +
                ", expiryDate=" + expiryDate +
                ", company='" + company + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNationalDrugName() {
        return nationalDrugName;
    }

    public void setNationalDrugName(String nationalDrugName) {
        this.nationalDrugName = nationalDrugName;
    }

    public Date getApprovaldateOfNationalDrugName() {
        return approvaldateOfNationalDrugName;
    }

    public void setApprovaldateOfNationalDrugName(Date approvaldateOfNationalDrugName) {
        this.approvaldateOfNationalDrugName = approvaldateOfNationalDrugName;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosageAdministration() {
        return dosageAdministration;
    }

    public void setDosageAdministration(String dosageAdministration) {
        this.dosageAdministration = dosageAdministration;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Medicine() {

    }
}
