package com.example.transbgr;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "datasetid",
        "fields",
        "geometry",
        "record_timestamp",
        "recordid"
})
public class Artiste {

    private String Uid;
    @JsonProperty("datasetid")
    private String datasetid;
    @JsonProperty("fields")
    private Fields fields;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("record_timestamp")
    private String recordTimestamp;
    @JsonProperty("recordid")
    private String recordid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("datasetid")
    public String getDatasetid() {
        return datasetid;
    }

    @JsonProperty("datasetid")
    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    @JsonProperty("fields")
    public Fields getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("record_timestamp")
    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    @JsonProperty("record_timestamp")
    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

    @JsonProperty("recordid")
    public String getRecordid() {
        return recordid;
    }

    @JsonProperty("recordid")
    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}