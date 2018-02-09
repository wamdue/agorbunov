package ru.job4j.mapping.carshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.model.dao.BrandDao;

import java.util.List;

/**
 * Created on 09.02.18.
 * Get list of brands.
 * @author Wamdue
 * @version 1.0
 */
@Controller
public class MvcBrandJsonController {
    /**
     * Link to brand dao.
     */
    private final BrandDao brands;

    /**
     * Main constructor.
     * @param brands - brand dao.
     */
    @Autowired
    public MvcBrandJsonController(BrandDao brands) {
        this.brands = brands;
    }

    /**
     * Get brands in json format.
     * @return - brands.
     */
    @RequestMapping(value = "/brand.do", method = RequestMethod.GET)
    @ResponseBody
    public List<Brand> getBrands() {
        return this.brands.getList();
    }
}
