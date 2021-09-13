package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICurvePointService {

    public List<CurvePoint> findAll();

    public CurvePoint save (CurvePoint curvePoint);

    public CurvePoint findById(Integer id);

    public void delete(Integer id);
}
