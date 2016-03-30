/*
 * ACDDExtension Project
 * file BarCode.java  is  part of ACDDExtension
 * The MIT License (MIT)  Copyright (c) 2015 Bunny Blue.
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.acdd.qrcode;

import android.graphics.Bitmap;

public class BarCode {

    static {
        try {
            System.loadLibrary("barcode");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码方法
     *
     * @param data   图片数据 NV21格式！！！调用Bitmap2Bytes获取NV21 bytes
     * @param width  原始宽度
     * @param height 原始高度
     * @return
     */
    public static native String decodeRaw(byte[] data, int width, int height);

    /**
     * 解码方法(需要裁剪图片)
     *
     * @param data    图片数据
     * @param width   原始宽度
     * @param height  原始高度
     * @param x       截取的x坐标
     * @param y       截取的y坐标
     * @param cwidth  截取的区域宽度
     * @param cheight 截取的区域高度
     * @return
     */
    public static native String decodeCrop(byte[] data, int width, int height, int x, int y, int cwidth, int cheight);

    public static byte[] getNV21FromBitmap(Bitmap scaled) {

        int[] argb = new int[scaled.getWidth() * scaled.getHeight()];

        scaled.getPixels(argb, 0, scaled.getWidth(), 0, 0, scaled.getWidth(), scaled.getHeight());

        byte[] yuv = new byte[scaled.getWidth() * scaled.getHeight() * 3 / 2];
        encodeYUV420SP(yuv, argb, scaled.getWidth(), scaled.getHeight());

        scaled.recycle();

        return yuv;
    }


    private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        final int frameSize = width * height;

        int yIndex = 0;
        int uvIndex = frameSize;

        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {

                a = (argb[index] & 0xff000000) >> 24; // a is not used obviously
                R = (argb[index] & 0xff0000) >> 16;
                G = (argb[index] & 0xff00) >> 8;
                B = (argb[index] & 0xff) >> 0;

                // well known RGB to YUV algorithm
                Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
                U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
                V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;

                // NV21 has a plane of Y and interleaved planes of VU each sampled by a factor of 2
                //    meaning for every 4 Y pixels there are 1 V and 1 U.  Note the sampling is every other
                //    pixel AND every other scanline.
                yuv420sp[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));
                if (j % 2 == 0 && index % 2 == 0) {
                    yuv420sp[uvIndex++] = (byte) ((V < 0) ? 0 : ((V > 255) ? 255 : V));
                    yuv420sp[uvIndex++] = (byte) ((U < 0) ? 0 : ((U > 255) ? 255 : U));
                }

                index++;
            }
        }
    }
}


