package com.vow.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author: vow
 * @date: 2023/8/30 16:06
 * @description:
 */
public class Resources {

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    public static InputStream getResourceAsStream(String resource) throws IOException {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (inputStream != null) {
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    public static ClassLoader[] getClassLoaders() {
        return new ClassLoader[] {
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }
}
