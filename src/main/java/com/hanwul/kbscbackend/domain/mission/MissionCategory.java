package com.hanwul.kbscbackend.domain.mission;

public enum MissionCategory {
    INSOMNIA("불면증"),
    FEAR("불안감"),
    ANOREXIA("식욕 부진");

    private String korean;

    MissionCategory(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
