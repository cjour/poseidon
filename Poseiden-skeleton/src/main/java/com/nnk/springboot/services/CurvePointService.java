package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * Find all curvePoints present in database.
     * @return List<CurvePoint>.
     **/
    @Override
    public List<CurvePoint> findAll() { return curvePointRepository.findAll(); }

    /**
     * Save a curvePoint in database.
     * @param curvePoint to save in database.
     * @return curvePoint if saved successfully.
     **/
    @Override
    public CurvePoint save(CurvePoint curvePoint) { return curvePointRepository.save(curvePoint); }

    /**
     * Find a specific curvePoint with its id.
     * @param id corresponding to the curvePoint to retrieve.
     * @return CurvePoint if found.
     **/
    @Override
    public CurvePoint findById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePoint.orElse(null);
    }

    /**
     * Allows to delete a curvePoint in database.
     * @param id corresponding to the curvePoint to delete.
     **/
    @Override
    public void delete(Integer id) {
        CurvePoint curvePoint = this.findById(id);
        if (curvePoint != null){
            curvePointRepository.delete(curvePoint);
        }
    }
}
