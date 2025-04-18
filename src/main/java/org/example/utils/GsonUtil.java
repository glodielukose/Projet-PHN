package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static <T> List<T> readFromFile(String filePath, Type typeOfT) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, typeOfT);
        }
    }

    public static <T> void writeToFile(String filePath, List<T> data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        }
    }

    public static <T> void addToFile(String filePath, T newItem, Type typeOfT) throws IOException {
        List<T> items = readFromFile(filePath, typeOfT);
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(newItem);
        writeToFile(filePath, items);
    }

    public static <T> void removeFromFile(String filePath, int id, Type typeOfT) throws IOException {
        List<T> items = readFromFile(filePath, typeOfT);
        if (items != null) {
            items.removeIf(item -> {
                try {
                    return item.getClass().getMethod("getId").invoke(item).equals(id);
                } catch (Exception e) {
                    return false;
                }
            });
            writeToFile(filePath, items);
        }
    }

    public static <T> void updateInFile(String filePath, T updatedItem, int id, Type typeOfT) throws IOException {
        List<T> items = readFromFile(filePath, typeOfT);
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                try {
                    if (items.get(i).getClass().getMethod("getId").invoke(items.get(i)).equals(id)) {
                        items.set(i, updatedItem);
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            writeToFile(filePath, items);
        }
    }
}