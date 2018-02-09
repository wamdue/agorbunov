package ru.job4j.mapping.carshop.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Pic;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.dao.AxleDao;
import ru.job4j.mapping.carshop.model.dao.BodyDao;
import ru.job4j.mapping.carshop.model.dao.BrandDao;
import ru.job4j.mapping.carshop.model.dao.CarDao;
import ru.job4j.mapping.carshop.model.dao.EngineDao;
import ru.job4j.mapping.carshop.model.dao.GearboxDao;
import ru.job4j.mapping.carshop.model.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 09.02.18.
 *
 * @author Wamdue
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/newcar.do")
public class MvcNewCarController {
    /**
     * Memory threshold.
     */
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    /**
     * Maximum file size.
     */
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    /**
     * Maximum requested file size.
     */
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    /**
     * Brand.
     */
    private static final String BRAND = "brand_id";
    /**
     * Body.
     */
    private static final String BODY = "body_id";
    /**
     * Axle.
     */
    private static final String AXLE = "axle_id";
    /**
     * Engine.
     */
    private static final String ENGINE = "engine_id";
    /**
     * Gearbox.
     */
    private static final String GEARBOX = "gearbox_id";
    /**
     * Car name.
     */
    private static final String NAME = "car_name";
    /**
     * Car description.
     */
    private static final String DESCRIPTION = "car_description";
    /**
     * Car created year.
     */
    private static final String DATE = "car_date";
    /**
     * Car price.
     */
    private static final String PRICE = "car_price";

    /**
     * Map with actions.
     */
    private final Map<String, Function<String, Boolean>> map = new HashMap<>();
    /**
     * Engine dao link.
     */
    private final EngineDao engineDao;
    /**
     * Body dao link.
     */
    private final BodyDao bodyDao;
    /**
     * Brand dao link.
     */
    private final BrandDao brandDao;
    /**
     * Gearbox bao link.
     */
    private final GearboxDao gearboxDao;
    /**
     * Axle dao link.
     */
    private final AxleDao axleDao;
    /**
     * User dao link.
     */
    private final UserDao userDao;
    /**
     * Car dao link.
     */
    private final CarDao carDao;

    {
        this.map.put(BRAND, this.getBrand());
        this.map.put(BODY, this.getBody());
        this.map.put(AXLE, this.getAxle());
        this.map.put(ENGINE, this.getEngine());
        this.map.put(GEARBOX, this.getGearbox());
        this.map.put(NAME, this.getName());
        this.map.put(DESCRIPTION, this.getDescription());
        this.map.put(DATE, this.getDate());
        this.map.put(PRICE, this.getPrice());
    }

    /**
     * Car link.
     */
    private Car car;

    /**
     * Main constructor.
     * @param engineDao - engine dao.
     * @param bodyDao - body dao.
     * @param brandDao - brand dao.
     * @param gearboxDao - gearbox dao.
     * @param axleDao - axle dao.
     * @param userDao - user dao.
     * @param carDao - car dao.
     */
    @Autowired
    public MvcNewCarController(EngineDao engineDao, BodyDao bodyDao, BrandDao brandDao, GearboxDao gearboxDao, AxleDao axleDao, UserDao userDao, CarDao carDao) {
        this.engineDao = engineDao;
        this.bodyDao = bodyDao;
        this.brandDao = brandDao;
        this.gearboxDao = gearboxDao;
        this.axleDao = axleDao;
        this.userDao = userDao;
        this.carDao = carDao;
    }

    /**
     * Fill comboboxes of page.
     * @param req - servlet request.
     * @return - page to view.
     * @throws UnsupportedEncodingException - encoding exception.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getPage(HttpServletRequest req) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("brands", this.brandDao.getList());
        req.setAttribute("engines", this.engineDao.getList());
        req.setAttribute("axles", this.axleDao.getList());
        req.setAttribute("bodies", this.bodyDao.getList());
        req.setAttribute("gearboxes", this.gearboxDao.getList());
        return "add_car";
    }

    /**
     * Writing new car to db.
     * @param req  - request.
     * @return - redirect page.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String writeToDb(HttpServletRequest req) {
        User user = this.userDao.getById(Integer.valueOf((String) req.getSession().getAttribute("user")));
        this.car = new Car();
        this.car.setUser(user);
        this.car.setPost(new Timestamp(System.currentTimeMillis()));
        for (Pic p : this.saveFile(req)) {
            p.setId(this.car.getId());
            this.car.addPic(p);
        }
        this.carDao.create(this.car);
        return "redirect:/index.do";
    }

    /**
     * Parse request for fields, and save files to dist.
     * @param request  - http servlet request.
     * @return - list of path files.
     */
    private List<Pic> saveFile(HttpServletRequest request) {
        List<Pic> files = new ArrayList<>();
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                if (fileItems != null && fileItems.size() > 0) {
                    for (FileItem item : fileItems) {
                        if (item.isFormField()) {
                            this.map.get(item.getFieldName()).apply(item.getString());
                        } else if (item.getSize() > 0) {
                            Pic pic = new Pic();
                            pic.setPath(item.get());
                            files.add(pic);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    /**
     * Assign brand to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getBrand() {
        return msg -> {
            this.car.setBrand(this.brandDao.getById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign axle to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getAxle() {
        return msg -> {
            this.car.setAxle(this.axleDao.getById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign engine to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getEngine() {
        return msg -> {
            this.car.setEngine(this.engineDao.getById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign gearbox to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getGearbox() {
        return msg -> {
            this.car.setGearbox(this.gearboxDao.getById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign body to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getBody() {
        return msg -> {
            this.car.setBody(this.bodyDao.getById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign price to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getPrice() {
        return msg -> {
            this.car.setPrice(Integer.valueOf(msg));
            return true;
        };
    }

    /**
     * Assign name to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getName() {
        return msg -> {
            this.car.setName(msg);
            return true;
        };
    }

    /**
     * Assign description to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getDescription() {
        return msg -> {
            this.car.setDescription(msg);
            return true;
        };
    }

    /**
     * Assign date to the car.
     *
     * @return result.
     */
    private Function<String, Boolean> getDate() {
        return msg -> {
            this.car.setCarCreated(Integer.valueOf(msg));
            return true;
        };
    }

}