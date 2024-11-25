package service;

import entity.Profile;

import java.util.List;

public class ProfileService {
    public void validateProfile(Profile profile) {
        if (profile.getBio().length() > 200) {
            throw new IllegalArgumentException("Bio exceeds maximum length");
        }
        // More validation rules
    }
}