package com.example.trackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackSystem {
    private String id;
    private String title;
    private String description;
    private boolean status;

}
