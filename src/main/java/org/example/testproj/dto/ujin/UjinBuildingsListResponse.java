package org.example.testproj.dto.ujin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UjinBuildingsListResponse {
    private String command;
    private String message;
    private Integer error;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<BuildingItem> buildings;
        private UjinPaginationMeta meta;
    }

    @lombok.Data
    public static class BuildingItem {
        private Integer id;
        private BuildingComplex complex;
        private BuildingInfo building;
        private List<Entrance> entrances2;
        private List<Statistic> statistics;
    }

    @lombok.Data
    public static class BuildingComplex {
        private Integer id;
        private String title;
        private String timezone;
    }

    @lombok.Data
    public static class BuildingInfo {
        private Integer id;
        private String title;
        private String alias;
        private Integer floor;
        private Integer apartmentCount;
        private Integer entranceCount;
        private Address address;
        @JsonProperty("sell_enabled")
        private Boolean sellEnabled;
        @JsonProperty("sell_emails")
        private List<String> sellEmails;
        @JsonProperty("security_number")
        private String securityNumber;
        @JsonProperty("guest_scud_pass_limit")
        private Integer guestScudPassLimit;
        @JsonProperty("buildings_properties_rent_available")
        private Boolean buildingsPropertiesRentAvailable;
        @JsonProperty("resident_request_variant")
        private String residentRequestVariant;
        @JsonProperty("paid_tickets_allowed")
        private String paidTicketsAllowed;
        private Meters meters;
    }

    @lombok.Data
    public static class Address {
        private String city;
        private String street;
        private String house;
        private String fullAddress;
    }

    @lombok.Data
    public static class Meters {
        private String mode;
        @JsonProperty("manual-update")
        private ManualUpdate manualUpdate;
    }

    @lombok.Data
    public static class ManualUpdate {
        @JsonProperty("from_day_of_month")
        private Integer fromDayOfMonth;
        @JsonProperty("until_day_of_month")
        private Integer untilDayOfMonth;
        @JsonProperty("push_day_of_month")
        private Integer pushDayOfMonth;
        @JsonProperty("notification_message")
        private String notificationMessage;
    }

    @lombok.Data
    public static class Entrance {
        private Integer number;
        @JsonProperty("first_apartment")
        private Integer firstApartment;
        @JsonProperty("last_apartment")
        private Integer lastApartment;
    }

    @lombok.Data
    public static class Statistic {
        private String type;
        private String title;
        private Integer count;
    }
}
