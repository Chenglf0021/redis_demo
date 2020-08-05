package com.lomgfei.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class TestWriteBlob {
    public static void main(String[] args) throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JdbcUtil jdbcUtil=new JdbcUtil();
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from tf_contract_templet where templet_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            rs = stmt.executeQuery();
            while (rs.next()) {

                //读取blob字段
                Blob blob = rs.getBlob("templet_info");
                //通过blob字段获取输入字节流
                InputStream in = blob.getBinaryStream();

                //把输入字节流写出文件中
                FileOutputStream out = new FileOutputStream("e:/1.pdf");
                byte[] buf = new byte[1024];
                int len = 0;
                //边读边写
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                //关闭流
                out.close();
                in.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
