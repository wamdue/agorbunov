package ru.job4j.mapping.carshop.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Pic;
import ru.job4j.mapping.carshop.entity.User;
import ru.job4j.mapping.carshop.model.Connect;
import ru.job4j.mapping.carshop.model.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 23.01.18.
 * Add new car to db.
 * @author Wamdue
 * @version 1.0
 */
public class NewCarController extends HttpServlet {
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
     * Connection to db.
     */
    private final DB db = Connect.INSTANCE.getConnection();
    /**
     * Map with actions.
     */
    private final Map<String, Function<String, Boolean>> map = new HashMap<>();

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("brands", this.db.getBrands());
        req.setAttribute("engines", this.db.getEngines());
        req.setAttribute("axles", this.db.getAxles());
        req.setAttribute("bodies", this.db.getBodies());
        req.setAttribute("gearboxes", this.db.getGearboxes());
        req.getRequestDispatcher("/WEB-INF/add_car.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = this.db.getUserById(Integer.valueOf((String)req.getSession().getAttribute("user")));
        this.car = new Car();
        this.car.setUser(user);
        this.car.setPost(new Timestamp(System.currentTimeMillis()));
        List<String> list = this.saveFile(req, user.getName());
        List<Pic> pics = new ArrayList<>();
        this.db.addNewCar(car);
        for (String s : list) {
            Pic pic = new Pic();
            pic.setId(this.car.getId());
            pic.setPath(s);
            pics.add(pic);
        }
        this.db.savePics(pics);
        resp.sendRedirect(String.format("%s/welcome.html", req.getContextPath()));
    }

    /**
     * Parse request for fields, and save files to dist.
     * @param request - request.
     * @param userName - username.
     * @return - list of path files.
     */
    private List<String> saveFile(HttpServletRequest request, String userName) {
        List<String> files = new ArrayList<>();
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            StringBuilder path = new StringBuilder();
            path.append("/home/alexey/Java");
//            path.append(File.separator);
//            path.append("Upload");
//            path.append(File.separator);
//            path.append(userName);
//            path.append(File.separator);
//            path.append(System.currentTimeMillis());

            File uploadDir = new File(path.toString());
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                if (fileItems != null && fileItems.size() > 0) {
                    for (FileItem item : fileItems) {
                        if (item.isFormField()) {
                            this.map.get(item.getFieldName()).apply(item.getString());
                        } else {
                            boolean result = true;
                            if (!uploadDir.exists()) {
                                result = uploadDir.mkdir();
                            }
                            if (result) {
                                String fileName = new File(item.getName()).getName();
                                String filePath = path.toString() + File.separator + fileName;
                                File storeFile = new File(filePath);
                                item.write(storeFile);
                                files.add(storeFile.getAbsolutePath());
                            }
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
     * @return result.
     */
    private Function<String, Boolean> getBrand() {
        return msg -> {
            this.car.setBrand(this.db.getBrandById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign axle to the car.
     * @return result.
     */
    private Function<String, Boolean> getAxle() {
        return msg -> {
            this.car.setAxle(this.db.getAxleById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign engine to the car.
     * @return result.
     */
    private Function<String, Boolean> getEngine() {
        return msg -> {
            this.car.setEngine(this.db.getEngineById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign gearbox to the car.
     * @return result.
     */
    private Function<String, Boolean> getGearbox() {
        return msg -> {
            this.car.setGearbox(this.db.getGearboxById(Integer.valueOf(msg)));
            return true;
        };
    }
    /**
     * Assign body to the car.
     * @return result.
     */
    private Function<String, Boolean> getBody() {
        return msg -> {
            this.car.setBody(this.db.getBodyById(Integer.valueOf(msg)));
            return true;
        };
    }

    /**
     * Assign price to the car.
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
     * @return result.
     */
    private Function<String, Boolean> getDate() {
        return msg -> {
            this.car.setCarCreated(Integer.valueOf(msg));
            return true;
        };
    }
}
