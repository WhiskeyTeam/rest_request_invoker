package com.whiskey.libs.rest.request.serobj;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
public class ResponseHead {
    /**
     * "version": "V2",
     *     "requestId": "string",
     *     "timestamp": 1722477710525,
     *     "images": [
     */

    private String version;
    private String requestId;
    private long timestamp;
    private List<Image> images = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Image {
    private String uid;
    private String name;
    private String inferResult;
    private String message;

    private MatchedTemplate matchedTemplate;
    private ValidationResult validationResult;
    private ConvertedImageInfo convertedImageInfo;

    private List<Field> fields = new ArrayList<>();
}

@Getter
@Setter
@ToString
class MatchedTemplate {
    private int id;
    private String name;
}

@Getter
@Setter
@ToString
class ValidationResult {
    private String result;
}

@Getter
@Setter
@ToString
class ConvertedImageInfo {
    private int width;
    private int height;
    private int pageIndex;
    private boolean longImage;
}

@Getter
@Setter
@ToString
class Field {
    private String name;
    private String valueType;

    private BoundingPoly boundingPoly;

    private String inferText;
    private double inferConfidence;
    private String type;

    private List<SubField> subFields = new ArrayList<>();
}

@Getter
@Setter
@ToString
class BoundingPoly {
    private List<Vertex> vertices = new ArrayList<>();
}

@Getter
@Setter
@ToString
class Vertex {
    private double x;
    private double y;
}

@Getter
@Setter
@ToString
class SubField {
    private BoundingPoly boundingPoly;
    private String inferText;
    private double inferConfidence;
    private boolean lineBreak;
}