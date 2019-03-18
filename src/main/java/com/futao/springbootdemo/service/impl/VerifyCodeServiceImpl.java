package com.futao.springbootdemo.service.impl;

import com.futao.springbootdemo.service.VerifyCodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by jerry on 16-7-12.
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private Random random = new Random();
    private String randString = "123456789abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";//随机产生的字符串
    private Font font = new Font("Fixedsys", Font.BOLD, 18);

    private int width = 80;//图片宽
    private int height = 26;//图片高
    private int lineSize = 40;//干扰线数量
    private int stringNum = 4;//随机产生字符数量

    @Resource
//    private JedisService jedisService;
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public byte[] updateVerifyCodeBytes() {

        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = null;
        byte[] ret = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String randomString = "";

        try {
            g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
            g.fillRect(0, 0, width, height);
//            g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
            g.setColor(getRandColor(110, 133));
            //绘制干扰线
            for (int i = 0; i <= lineSize; i++) {
                drowLine(g);
            }
            //绘制随机字符
            for (int i = 1; i <= stringNum; i++) {
                randomString = drowString(g, randomString, i);
            }
            System.out.println(randomString);

            ImageIO.write(image, "JPEG", out);
            ret = out.toByteArray();

            String finalRandomString = randomString;

//            ResourceKt.autoRelease(res -> {
//                Jedis jedis = res.register(jedisService);
//                jedis.setex("java.verifycode." + sessionId, 60, finalRandomString);
//                return null;
//            });
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(out);
            if (null != g) {
                g.dispose();
            }
        }
        return ret;
    }

//    @Override
//    public String getVerifyCode(String sessionId) {
//        return ResourceKt.autoRelease(res -> {
//            Jedis jedis = res.register(jedisService);
//            String key = "java.verifycode." + sessionId;
//            String redisCode = jedis.get(key);
//            jedis.del(key);
//            return redisCode;
//        });
//    }

//    @Override
//    public void verify(String sessionId, String code) {
//        if (StringUtils.isBlank(code))
//            throw ServiceKt.le(this.getClass(), CoreMsgs.YanZhengMaBuZhengQue);
//
//        String input = StringUtils.trimToEmpty(code)
//                .toLowerCase();
//        String redisCode = StringUtils.trimToEmpty(getVerifyCode(sessionId))
//                .toLowerCase();
////        System.out.println("redisCode:"+redisCode);
//        if (!Objects.equal(input, redisCode))
//            throw ServiceKt.le(this.getClass(), CoreMsgs.YanZhengMaBuZhengQue);
//    }

    /*
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(font);
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = getRandomString(random.nextInt(randString.length()));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
     * 获取随机的字符
     */
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }


}
