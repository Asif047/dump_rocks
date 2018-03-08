package com.cbc_app_poc.rokomari.rokomarians.Model;


public class ModelParticipate {

    private RecreationHour recreationHour;

    private Integer userId;

    public ModelParticipate(RecreationHour recreationHour, Integer userId) {
        this.recreationHour = recreationHour;
        this.userId = userId;
    }

    public RecreationHour getRecreationHour() {
        return recreationHour;
    }

    public void setRecreationHour(RecreationHour recreationHour) {
        this.recreationHour = recreationHour;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public static class RecreationHour {


        private String details;

        private String event;

        public RecreationHour(String details, String event) {
            this.details = details;
            this.event = event;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

    }

}
