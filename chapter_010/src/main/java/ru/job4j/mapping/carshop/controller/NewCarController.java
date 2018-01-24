package ru.job4j.mapping.carshop.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.mapping.carshop.entity.Axle;
import ru.job4j.mapping.carshop.entity.Body;
import ru.job4j.mapping.carshop.entity.Brand;
import ru.job4j.mapping.carshop.entity.Car;
import ru.job4j.mapping.carshop.entity.Engine;
import ru.job4j.mapping.carshop.entity.Gearbox;
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
import java.util.List;

/**
 * Created on 23.01.18.
 *
 * @author Wamdue
 * @version 1.0
 */
public class NewCarController extends HttpServlet {
    /**
     * Location directory.
     */
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = Connect.INSTANCE.getConnection();
        Brand brand = db.getBrandById(Integer.valueOf(req.getParameter("brand_id")));
        Engine engine = db.getEngineById(Integer.valueOf(req.getParameter("engine_id")));
        Axle axle = db.getAxleById(Integer.valueOf(req.getParameter("axle_id")));
        Body body = db.getBodyById(Integer.valueOf(req.getParameter("body_id")));
        Gearbox gearbox = db.getGearboxById(Integer.valueOf(req.getParameter("gearbox_id")));
        String name = req.getParameter("car_name");
        String description = req.getParameter("car_description");
        User user = db.getUserById(Integer.valueOf((String)req.getSession().getAttribute("user")));
        Car car = new Car();
        car.setAxle(axle);
        car.setBody(body);
        car.setBrand(brand);
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setUser(user);
        car.setName(name);
        car.setDescription(description);
        car.setPost(new Timestamp(System.currentTimeMillis()));
        car.setPrice(Integer.valueOf(req.getParameter("price")));
        db.addNewCar(car);
        List<Pic> pics = new ArrayList<>();
        for (String line : saveFile(req, user.getName())) {
            Pic pic = new Pic();
            pic.setId(car.getId());
            pic.setPath(line);
            pics.add(pic);
        }
        car.setPics(pics);
    }

    private List<String> saveFile(HttpServletRequest request, String userName) {
        List<String> files = new ArrayList<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        StringBuilder path = new StringBuilder();
        path.append(getServletContext().getRealPath(""));
        path.append(File.separator);
        path.append(userName);
        path.append(File.separator);
        path.append(System.currentTimeMillis());

        File uploadDir = new File(path.toString());
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            if (fileItems != null && fileItems.size() > 0) {
                for (FileItem item : fileItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = path.toString() + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        files.add(storeFile.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return files;
    }
}
