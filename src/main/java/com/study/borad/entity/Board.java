package com.study.borad.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 객체
@Data // 데이터를 받을 수 있게 해줌
public class Board {

    @Id // 프라이머리 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략 패턴 설정 IDENTITY는 mariaDB에서 사용
    // 테이블 내 필드
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

}
