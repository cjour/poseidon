package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Digits(integer = Integer.MAX_VALUE, fraction = 0)
    @Min(value = 1)
    @NotNull
    private Integer curveId;

    @Digits(integer = 10, fraction = 2)
    @Min(value = 1)
    @NotNull
    private Double term;

    @Digits(integer = 10, fraction = 2)
    @Min(value = 1)
    @NotNull
    private Double value;

    public CurvePoint() {
    }

    public CurvePoint(Integer id, Integer curveId, Double term, Double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
