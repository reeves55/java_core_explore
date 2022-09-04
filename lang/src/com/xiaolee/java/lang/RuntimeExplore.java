package com.xiaolee.java.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RuntimeExplore {
    private static final Long GBitsMask = 1024 * 1024 * 1024L;

    private static final Runtime runtime = Runtime.getRuntime();


    public static void main(String[] args) {
        System.out.printf("runtime info: availableProcessors=%d, freeMemory=%.2fG, maxMemory=%.2fG, totalMemory=%.2fG\n",
                runtime.availableProcessors(), runtime.freeMemory() * 1.0 / GBitsMask,
                runtime.maxMemory() * 1.0 / GBitsMask, runtime.totalMemory() * 1.0 / GBitsMask);

        // 测试availableProcessors方法
        // testAvailableProcessors();

        // 测试内存相关方法
        testMemory();
    }

    private static void testMemory() {
        System.out.printf("before testMemory, freeMemory=%.2fG, maxMemory=%.2fG, totalMemory=%.2fG\n",
                runtime.freeMemory() * 1.0 / GBitsMask,
                runtime.maxMemory() * 1.0 / GBitsMask, runtime.totalMemory() * 1.0 / GBitsMask);

        List<String> strObjects = new ArrayList<>(1024);

        // 创建占用内存空间的对象
        for (int i = 0; i < 100 * 1024; i++) {
            strObjects.add(new String(new char[1024]));
        }

        System.out.printf("after testMemory, freeMemory=%.2fG, maxMemory=%.2fG, totalMemory=%.2fG\n",
                runtime.freeMemory() * 1.0 / GBitsMask,
                runtime.maxMemory() * 1.0 / GBitsMask, runtime.totalMemory() * 1.0 / GBitsMask);
    }


    private static void testAvailableProcessors() {
        int testTimes = 1000_000;
        Set<Integer> processorNumberSet = new HashSet<>();

        for (int i = 0; i < testTimes; i++) {
            int availableProcessors = runtime.availableProcessors();
            if (!processorNumberSet.contains(availableProcessors)) {
                System.out.printf("available processors: %d\n", availableProcessors);
                processorNumberSet.add(availableProcessors);
            }
        }

        System.out.println("testAvailableProcessors over");
    }
}
