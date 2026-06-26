package com.harsh.BloodDonor.service;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BloodCompatibilityMatrix {
    private static final Map<String, List<String>> COMPATIBILITY_MAP = new HashMap<>();

    static {
        COMPATIBILITY_MAP.put("O-",  Arrays.asList("O-"));
        COMPATIBILITY_MAP.put("O+",  Arrays.asList("O-", "O+"));
        COMPATIBILITY_MAP.put("A-",  Arrays.asList("O-", "A-"));
        COMPATIBILITY_MAP.put("A+",  Arrays.asList("O-", "O+", "A-", "A+"));
        COMPATIBILITY_MAP.put("B-",  Arrays.asList("O-", "B-"));
        COMPATIBILITY_MAP.put("B+",  Arrays.asList("O-", "O+", "B-", "B+"));
        COMPATIBILITY_MAP.put("AB-", Arrays.asList("O-", "A-", "B-", "AB-"));
        COMPATIBILITY_MAP.put("AB+", Arrays.asList("O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"));
    }

    /**
     * Given a patient's/hospital's requested blood group,
     * returns a list of compatible donor blood groups.
     */
    public List<String> getCompatibleDonorGroups(String requestedGroup) {
        return COMPATIBILITY_MAP.getOrDefault(requestedGroup.toUpperCase(), Collections.emptyList());
    }
}
