package com.myshoppingcart.persistence;

import com.myshoppingcart.model.Producto;
import com.myshoppingcart.properties.PropertyValues;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class ProductoDBRepository implements IProductoRepository {
    private static Logger logger = Logger.getLogger("ProductosDBRepository");

    private final static String connUrl;

    static {
        PropertyValues propsVals = new PropertyValues();
        Properties props = null;
        try {
            props = propsVals.getPropValues();
            connUrl = props.getProperty("db_url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Producto> getProducts() throws Exception {
        List<Producto> listADevolver = new ArrayList<>();

        Connection conn = DriverManager.getConnection(connUrl);

        // ordenes sql
        String sql = "SELECT p.* FROM producto p WHERE 1";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            listADevolver.add(new Producto(rs.getInt("mid"), rs.getString("codigo"), rs.getString("marca"),
                    rs.getString("tipo"), rs.getInt("precio"), rs.getInt("existencias")));
        }

        pstm.close();

        conn.close();

        return listADevolver;
    }

    @Override
    public List<Producto> getUserProducts(int uid) throws Exception {
        List<Producto> listADevolver = new ArrayList<>();
        Connection conn = DriverManager.getConnection(connUrl);

        // ordenes sql
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT p.* FROM producto p INNER JOIN compra c ON c.producto=p.pid WHERE c.usuario=" + uid);

        while (rs.next()) {
            listADevolver.add(new Producto(
                    rs.getInt("pid"),
                    rs.getString("codigo"),
                    rs.getString("marca"),
                    rs.getString("tipo"),
                    rs.getInt("precio"),
                    rs.getInt("existencias")
            ));
        }

        stmt.close();
        conn.close();

        return listADevolver;
    }

}
