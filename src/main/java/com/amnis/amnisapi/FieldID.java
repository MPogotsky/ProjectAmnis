package com.amnis.amnisapi;

enum FieldID {
    TIME_IN_STATUS("customfield_10025"),
    WORK_RATIO("workratio"),
    TIME_ORIGINAL_ESTIMATE("timeoriginalestimate"),
    TIME_ESTIMATE("timeestimate");

    final String id;

    FieldID(String id){
        this.id = id;
    }

}