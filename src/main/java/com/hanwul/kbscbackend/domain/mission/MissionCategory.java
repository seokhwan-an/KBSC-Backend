package com.hanwul.kbscbackend.domain.mission;

public enum MissionCategory {
    INSOMNIA("불면증"),
    FEAR("불안감"),
    ANOREXIA("식욕 부진"),
    CONFIDENCE("자신감 부족"),
    HELPLESS("무력감"),
    REMIND("반복적 회상"),
    ANGRY("분노");

    private String korean;

    MissionCategory(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
