package com.example.peter.okhttpgson;


import com.google.gson.annotations.SerializedName;

/**
 * The type Large area.
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class LargeArea {

    @SerializedName("service_area")
    private ServiceArea serviceArea;
    private String name;
    @SerializedName("large_service_area")
    private LargeServiceArea largeServiceArea;
    private String code;

    /**
     * Gets service area.
     *
     * @return the service area
     */
    ServiceArea getServiceArea() {
        return serviceArea;
    }

}
