
package com.example.transbgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1ere_date",
        "1ere_date_timestamp",
        "1ere_salle",
        "annee",
        "artistes",
        "cou_is_ilomember",
        "cou_is_receiving_quest",
        "cou_iso2_code",
        "cou_iso3_code",
        "cou_official_lang_code",
        "cou_onu_code",
        "cou_text_en",
        "cou_text_sp",
        "deezer",
        "edition",
        "geo_point_2d",
        "origine_pays1",
        "spotify"
})
public class Fields {

    @JsonProperty("1ere_date")
    private String _1ereDate;
    @JsonProperty("1ere_date_timestamp")
    private Integer _1ereDateTimestamp;
    @JsonProperty("1ere_salle")
    private String _1ereSalle;
    @JsonProperty("annee")
    private String annee;
    @JsonProperty("artistes")
    private String artistes;
    @JsonProperty("cou_is_ilomember")
    private String couIsIlomember;
    @JsonProperty("cou_is_receiving_quest")
    private String couIsReceivingQuest;
    @JsonProperty("cou_iso2_code")
    private String couIso2Code;
    @JsonProperty("cou_iso3_code")
    private String couIso3Code;
    @JsonProperty("cou_official_lang_code")
    private String couOfficialLangCode;
    @JsonProperty("cou_onu_code")
    private String couOnuCode;
    @JsonProperty("cou_text_en")
    private String couTextEn;
    @JsonProperty("cou_text_sp")
    private String couTextSp;
    @JsonProperty("deezer")
    private String deezer;
    @JsonProperty("edition")
    private String edition;
    @JsonProperty("geo_point_2d")
    private List<Double> geoPoint2d = null;
    @JsonProperty("origine_pays1")
    private String originePays1;
    @JsonProperty("spotify")
    private String spotify;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1ere_date")
    public String get1ereDate() {
        return _1ereDate;
    }

    @JsonProperty("1ere_date")
    public void set1ereDate(String _1ereDate) {
        this._1ereDate = _1ereDate;
    }

    @JsonProperty("1ere_date_timestamp")
    public Integer get1ereDateTimestamp() {
        return _1ereDateTimestamp;
    }

    @JsonProperty("1ere_date_timestamp")
    public void set1ereDateTimestamp(Integer _1ereDateTimestamp) {
        this._1ereDateTimestamp = _1ereDateTimestamp;
    }

    @JsonProperty("1ere_salle")
    public String get1ereSalle() {
        return _1ereSalle;
    }

    @JsonProperty("1ere_salle")
    public void set1ereSalle(String _1ereSalle) {
        this._1ereSalle = _1ereSalle;
    }

    @JsonProperty("annee")
    public String getAnnee() {
        return annee;
    }

    @JsonProperty("annee")
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    @JsonProperty("artistes")
    public String getArtistes() {
        return artistes;
    }

    @JsonProperty("artistes")
    public void setArtistes(String artistes) {
        this.artistes = artistes;
    }

    @JsonProperty("cou_is_ilomember")
    public String getCouIsIlomember() {
        return couIsIlomember;
    }

    @JsonProperty("cou_is_ilomember")
    public void setCouIsIlomember(String couIsIlomember) {
        this.couIsIlomember = couIsIlomember;
    }

    @JsonProperty("cou_is_receiving_quest")
    public String getCouIsReceivingQuest() {
        return couIsReceivingQuest;
    }

    @JsonProperty("cou_is_receiving_quest")
    public void setCouIsReceivingQuest(String couIsReceivingQuest) {
        this.couIsReceivingQuest = couIsReceivingQuest;
    }

    @JsonProperty("cou_iso2_code")
    public String getCouIso2Code() {
        return couIso2Code;
    }

    @JsonProperty("cou_iso2_code")
    public void setCouIso2Code(String couIso2Code) {
        this.couIso2Code = couIso2Code;
    }

    @JsonProperty("cou_iso3_code")
    public String getCouIso3Code() {
        return couIso3Code;
    }

    @JsonProperty("cou_iso3_code")
    public void setCouIso3Code(String couIso3Code) {
        this.couIso3Code = couIso3Code;
    }

    @JsonProperty("cou_official_lang_code")
    public String getCouOfficialLangCode() {
        return couOfficialLangCode;
    }

    @JsonProperty("cou_official_lang_code")
    public void setCouOfficialLangCode(String couOfficialLangCode) {
        this.couOfficialLangCode = couOfficialLangCode;
    }

    @JsonProperty("cou_onu_code")
    public String getCouOnuCode() {
        return couOnuCode;
    }

    @JsonProperty("cou_onu_code")
    public void setCouOnuCode(String couOnuCode) {
        this.couOnuCode = couOnuCode;
    }

    @JsonProperty("cou_text_en")
    public String getCouTextEn() {
        return couTextEn;
    }

    @JsonProperty("cou_text_en")
    public void setCouTextEn(String couTextEn) {
        this.couTextEn = couTextEn;
    }

    @JsonProperty("cou_text_sp")
    public String getCouTextSp() {
        return couTextSp;
    }

    @JsonProperty("cou_text_sp")
    public void setCouTextSp(String couTextSp) {
        this.couTextSp = couTextSp;
    }

    @JsonProperty("deezer")
    public String getDeezer() {
        return deezer;
    }

    @JsonProperty("deezer")
    public void setDeezer(String deezer) {
        this.deezer = deezer;
    }

    @JsonProperty("edition")
    public String getEdition() {
        return edition;
    }

    @JsonProperty("edition")
    public void setEdition(String edition) {
        this.edition = edition;
    }

    @JsonProperty("geo_point_2d")
    public List<Double> getGeoPoint2d() {
        return geoPoint2d;
    }

    @JsonProperty("geo_point_2d")
    public void setGeoPoint2d(List<Double> geoPoint2d) {
        this.geoPoint2d = geoPoint2d;
    }

    @JsonProperty("origine_pays1")
    public String getOriginePays1() {
        return originePays1;
    }

    @JsonProperty("origine_pays1")
    public void setOriginePays1(String originePays1) {
        this.originePays1 = originePays1;
    }

    @JsonProperty("spotify")
    public String getSpotify() {
        return spotify;
    }

    @JsonProperty("spotify")
    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
