package com.example.capstone2022.api.user;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberData {

    private UUID uuid;
    private String name;
    private String phoneNo;
    private String address;

    private MemberCoronaInfo coronaInfo;

    @Data
    @AllArgsConstructor
    public static class MemberCoronaInfo {
        private LocalDateTime confirmationDate;
        private LocalDateTime finalVaccineDate;
        private LocalDateTime quarantineReleaseDate;
    }

    @NonNull
    public static MemberData parseMember(@NonNull JsonObject jsonObject) {
        UUID uuid = UUID.fromString(jsonObject.get("id").getAsString());
        String name = jsonObject.get("name").getAsString();
        String phoneNo = jsonObject.get("phoneNo").getAsString();
        String address = jsonObject.get("address").getAsString();

        JsonObject coronaInfo = jsonObject.getAsJsonObject("coronaInfo");

        LocalDateTime confirmationDate = LocalDateTime.parse(coronaInfo.get("confirmationDate").getAsString());
        LocalDateTime finalVaccineDate = LocalDateTime.parse(coronaInfo.get("finalVaccineDate").getAsString());
        LocalDateTime quarantineReleaseDate = LocalDateTime.parse(coronaInfo.get("quarantineReleaseDate").getAsString());

        return new MemberData(uuid, name, phoneNo, address,
                new MemberData.MemberCoronaInfo(confirmationDate, finalVaccineDate, quarantineReleaseDate));
    }

}
