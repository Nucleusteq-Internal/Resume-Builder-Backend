package com.project.ResumeBuilder.service;

import com.project.ResumeBuilder.exception.ResourceInvalidException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private Map<String, OtpData> otpStorage = new HashMap<>();
    private final Random random = new Random();
    private final int OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

    public String generateOtp(String email) {
        String otp = String.format("%04d", random.nextInt(10000)); // Generate 4-digit OTP
        OtpData otpData = new OtpData(otp, System.currentTimeMillis());
        otpStorage.put(email, otpData); // Store OTP with email and timestamp
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        if (!otpStorage.containsKey(email)) {
            throw new ResourceInvalidException("Invalid OTP.");
        }

        OtpData otpData = otpStorage.get(email);
        if (isOtpExpired(otpData.getTimestamp())) {
            otpStorage.remove(email);
            throw new ResourceInvalidException("OTP has expired.");
        }

        return otpData.getOtp().equals(otp);
    }

    public void clearOtp(String email) {
        otpStorage.remove(email); // Clear OTP after successful validation
    }

    private boolean isOtpExpired(long timestamp) {
        return (System.currentTimeMillis() - timestamp) > OTP_VALID_DURATION;
    }

    private static class OtpData {
        private final String otp;
        private final long timestamp;

        public OtpData(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}

